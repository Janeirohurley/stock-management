package com.mycompany.stock_managment.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.stock_managment.model.Product;
import com.mycompany.stock_managment.model.Vente;
import com.mycompany.stock_managment.util.DBConnectionUtil;
    // Méthode pour ajouter un produit dans la base de données
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
public class ProductService {



public Map<String, String> addProduct(Product product) {
    Map<String, String> response = new HashMap<>();
    // Code pour ajouter le produit dans la base de données
    String query = "INSERT INTO produit (nom, description, prix, quantite) VALUES (?, ?, ?, ?)";

    try (Connection connection = DBConnectionUtil.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        // Paramétrage de la requête
        statement.setString(1, product.getNom());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrix());
        statement.setInt(4, product.getQuantite());

        // Exécution de la requête
        int rowsAffected = statement.executeUpdate();

        // Vérifier si l'insertion a réussi
        if (rowsAffected > 0) {
            response.put("success", "Product added successfully");
        } else {
            response.put("error", "Error while processing product");
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.put("error", "Error while adding product: " + e.getMessage());
    }

    return response;
}


 public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM produit";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("description"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("quantite")
                );
                products.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public Number getNumberOFdette() {
        String query = "SELECT SUM(reste_a_payer) FROM vente";
        double dette = 0;
    
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
    
            if (resultSet.next()) {
                dette = resultSet.getDouble(1); // Récupère la valeur de SUM(reste_a_payer)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return dette; // Retourne la dette totale
    }
    
public Map<String, String> sellProduct(Vente product) {
    Map<String, String> response = new HashMap<>();
    String venteQuery = "INSERT INTO vente (produit_id, quantite, montant_total, paiement, reste_a_payer) VALUES (?, ?, ?, ?, ?)";
    String detteQuery = "INSERT INTO dette (vente_id, montant_dette) VALUES (?, ?)";

    try (Connection connection = DBConnectionUtil.getConnection();
         PreparedStatement venteStatement = connection.prepareStatement(venteQuery, Statement.RETURN_GENERATED_KEYS)) {

        // 1️⃣ Ajout de la vente
        venteStatement.setInt(1, product.getProduit_id());
        venteStatement.setInt(2, product.getQuantite());
        venteStatement.setDouble(3, product.getMontant_total());
        venteStatement.setDouble(4, product.getPaiement());
        venteStatement.setDouble(5, product.getReste_a_payer());

        int rowsAffected = venteStatement.executeUpdate();

        if (rowsAffected > 0) {
            // 2️⃣ Récupération de l'ID de la vente
            try (ResultSet generatedKeys = venteStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int venteId = generatedKeys.getInt(1); // L'ID généré

                    // 3️⃣ Si reste à payer > 0, on enregistre une dette
                    if (product.getReste_a_payer() > 0) {
                        try (PreparedStatement detteStatement = connection.prepareStatement(detteQuery)) {
                            detteStatement.setInt(1, venteId);
                            detteStatement.setDouble(2, product.getReste_a_payer());
                            detteStatement.executeUpdate();
                        }
                    }

                    response.put("success", "Vente enregistrée avec succès. ID: " + venteId);
                } else {
                    response.put("error", "Erreur : ID de vente non récupéré.");
                }
            }
        } else {
            response.put("error", "Échec de l'enregistrement de la vente.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.put("error", "Erreur lors de l'insertion : " + e.getMessage());
    }

    return response;
}


public List<Vente> getAllVentesHistory() {
    List<Vente> ventes = new ArrayList<>();
    String query = "SELECT vente.id AS vente_id, produit.id AS produit_id, produit.nom, produit.prix,vente.quantite, vente.montant_total, vente.paiement, vente.reste_a_payer, vente.date_vente FROM vente JOIN produit ON vente.produit_id = produit.id";

    try (Connection connection = DBConnectionUtil.getConnection();
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Vente vente = new Vente(
                    resultSet.getInt("vente_id"),  // ID de la vente
                    resultSet.getInt("quantite"),
                    resultSet.getInt("produit_id"),
                    resultSet.getDate("date_vente"),
                    resultSet.getDouble("montant_total"),
                    resultSet.getDouble("paiement")
                );
            
                // Assigner les valeurs spécifiques du produit
                vente.setProduct_name(resultSet.getString("nom"));
                vente.setPrix(resultSet.getDouble("prix"));
                ventes.add(vente);
            }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return ventes;
}


public List<Vente> searchVentes(String query) {
    List<Vente> ventes = new ArrayList<>();
    String searchQuery = "SELECT vente.id AS vente_id, produit.id AS produit_id, produit.nom, produit.prix, vente.quantite, vente.montant_total, vente.paiement, vente.reste_a_payer, vente.date_vente FROM vente JOIN produit ON vente.produit_id = produit.id WHERE produit.nom LIKE ?";
    
    try (Connection connection = DBConnectionUtil.getConnection();
         PreparedStatement statement = connection.prepareStatement(searchQuery)) {
        
        statement.setString(1, "%" + query + "%"); // Définir le paramètre
        try (ResultSet resultSet = statement.executeQuery()) { // Exécuter après la définition
            while (resultSet.next()) {
                Vente vente = new Vente(
                    resultSet.getInt("vente_id"),
                    resultSet.getInt("quantite"),
                    resultSet.getInt("produit_id"),
                    resultSet.getDate("date_vente"),
                    resultSet.getDouble("montant_total"),
                    resultSet.getDouble("paiement")// Ajout du champ manquant
                );
                vente.setProduct_name(resultSet.getString("nom"));
                vente.setPrix(resultSet.getDouble("prix"));
                ventes.add(vente); // Ajouter l'objet à la liste
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ventes;
}

  
}

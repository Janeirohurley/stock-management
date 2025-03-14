package com.mycompany.stock_managment.service;

import com.mycompany.stock_managment.model.Login;
import com.mycompany.stock_managment.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    // Méthode pour vérifier les identifiants de l'utilisateur dans la base de données
    public boolean validateUser(Login login) {
        boolean isValid = false;
        String query = "SELECT * FROM admin WHERE username = ? AND password_admin = ?"; // Requête SQL pour vérifier les utilisateurs
        
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // Remplir la requête préparée avec les informations de l'utilisateur
            statement.setString(1, login.getUsername());
            statement.setString(2, login.getPassword());

            // Exécuter la requête
            ResultSet resultSet = statement.executeQuery();
            
            // Si un utilisateur est trouvé, il est valide
            if (resultSet.next()) {
                isValid = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return isValid;
    }

    // Méthode pour récupérer le rôle de l'utilisateur depuis la base de données
    public String getUserRole(Login login) {
        String query = "SELECT role FROM admin WHERE username = ?"; // Requête SQL pour obtenir le rôle

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // Remplir la requête avec le nom d'utilisateur
            statement.setString(1, login.getUsername());

            // Exécuter la requête
            ResultSet resultSet = statement.executeQuery();

            // Si un utilisateur est trouvé, récupérer son rôle
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Par défaut, si l'utilisateur n'est pas trouvé, renvoyer "admin"
        return "admin";
    }
}

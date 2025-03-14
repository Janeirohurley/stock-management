package com.mycompany.stock_managment.model;

import java.sql.Date;

public class Vente {
    private int id;
    private int quantite;
    private int produit_id;  // Stocke directement l'ID du produit
    private Date date_vente;
    private double montant_total;
    private double paiement;
    private double reste_a_payer;
    private String product_name;
    private double prix;

    // Constructeurs
    public Vente(int id, int quantite, int produit_id, Date date_vente, double montant_total, double paiement) {
        this.id = id;
        this.quantite = quantite;
        this.produit_id = produit_id;
        this.date_vente = date_vente;
        this.montant_total = montant_total;
        this.paiement = paiement;
        this.reste_a_payer = calculateReste();
    }

    public Vente(int quantite, int produit_id, double montant_total, double paiement) {
        this.quantite = quantite;
        this.produit_id = produit_id;
        this.montant_total = montant_total;
        this.paiement = paiement;
        this.reste_a_payer = calculateReste();
    }

 public Vente(String product_name, int product_id,int quantite, int initale_price, double reste_a_payer,double montant_total) {
        this.product_name = product_name;
        this.produit_id = product_id;
        this.quantite = quantite;
        this.prix = initale_price;
        this.reste_a_payer = reste_a_payer;
        this.montant_total = montant_total;

    }
    // Getters et Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getProduit_id() {
        return produit_id;
    }
    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public Date getDate_vente() {
        return date_vente;
    }
    public void setDate_vente(Date date_vente) {
        this.date_vente = date_vente;
    }

    public double getMontant_total() {
        return montant_total;
    }
    public void setMontant_total(double montant_total) {
        this.montant_total = montant_total;
        this.reste_a_payer = calculateReste(); // Recalculer le reste
    }

    public double getPaiement() {
        return paiement;
    }
    public void setPaiement(double paiement) {
        this.paiement = paiement;
        this.reste_a_payer = calculateReste(); // Recalculer le reste
    }

    public double getReste_a_payer() {
        return reste_a_payer;
    }

    // Méthode pour calculer le reste à payer
    public double calculateReste() {
        return montant_total - paiement;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Vente{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", produit_id=" + produit_id +
                ", date_vente=" + date_vente +
                ", montant_total=" + montant_total +
                ", paiement=" + paiement +
                ", reste_a_payer=" + reste_a_payer +
                ", product_name='" + product_name + '\'' +
                ", prix=" + prix +
                '}';
    }
}

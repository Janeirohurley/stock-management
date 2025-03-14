package com.mycompany.stock_managment.model;

import java.sql.Date;
import java.util.Objects;

public class Dette {
    private int id;
    private String vente_id;
    private double montant_dette;
    private Date date_echeance ;
    private String status_dette;

    // Constructors
    public Dette(int id, String vente_id, double montant_dette, Date date_echeance, String status_dette) {
        this.id = id;
        this.vente_id = vente_id;
        this.montant_dette = montant_dette;
        this.date_echeance = date_echeance;
        this.status_dette = status_dette;
    }

    public Dette( String vente_id, double montant_dette, Date date_echeance) {
        this.vente_id = vente_id;
        this.montant_dette = montant_dette;
        this.date_echeance = date_echeance;
    }

    public Dette( String vente_id, double montant_dette) {
        this.vente_id = vente_id;
        this.montant_dette = montant_dette;
    }

    // Getters and Setters

    int getId(){
        return this.id;
    }

    void setId(int id){
        this.id = id;
    }
    String getVenteId(){
        return this.vente_id;
    }
    void setVenteId(String vente_id){
        this.vente_id = vente_id;
    }
    double getMontantDette(){
        return this.montant_dette;
    }
    void setMontantDette(double montant_dette){
        this.montant_dette = montant_dette;
    }
    Date getDateEcheance(){
        return this.date_echeance;
    }
    void setDateEcheance(Date date_echeance){
        this.date_echeance = date_echeance;
    }
    String getStatusDette(){
        return this.status_dette;
    }
    void setStatusDette(String status_dette){
        this.status_dette = status_dette;
    }
    // toString method
    @Override
    public String toString(){
        return "Dette{" +
                "id=" + id +
                ", vente_id='" + vente_id + '\'' +
                ", montant_dette=" + montant_dette +
                ", date_echeance=" + date_echeance +
                ", status_dette='" + status_dette + '\'' +
                '}';
    }
    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        Dette dette = (Dette) o;
        return id == dette.id &&
                Double.compare(dette.montant_dette, montant_dette) == 0 &&
                Objects.equals(vente_id, dette.vente_id) &&
                Objects.equals(date_echeance, dette.date_echeance) &&
                Objects.equals(status_dette, dette.status_dette);
    }
}

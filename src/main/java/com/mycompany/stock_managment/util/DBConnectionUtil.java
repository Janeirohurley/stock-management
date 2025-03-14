package com.mycompany.stock_managment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    // Informations de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/produit_manager"; // Remplacez par l'URL de votre base
    private static final String USER = "root"; // Remplacez par votre nom d'utilisateur
    private static final String PASSWORD = ""; // Remplacez par votre mot de passe

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver"); // Pour MySQL
            // Établir la connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found", e);
        }
    }
}

package com.mycompany.stock_managment.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import com.mycompany.stock_managment.model.SalesByDay;
import com.mycompany.stock_managment.util.DBConnectionUtil;

@WebServlet("/getSalesByDay")
public class SalesByDayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<SalesByDay> salesData = getSalesByDayFromDB(); // Récupérer les données des ventes, dettes et paiements

        // Convertir les données en JSON
        Gson gson = new Gson();
        String json = gson.toJson(salesData);

        response.getWriter().write(json);
    }

    private List<SalesByDay> getSalesByDayFromDB() {
        List<SalesByDay> salesData = new ArrayList<>();
        String query = "SELECT DATE(vente.date_vente) AS sale_date, " +
                       "SUM(vente.quantite) AS total_sold, " +
                       "SUM(vente.montant_total - vente.paiement) AS total_debt, " +
                       "SUM(vente.paiement) AS total_payment " +
                       "FROM vente GROUP BY sale_date ORDER BY sale_date DESC";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Ajouter une nouvelle entrée pour chaque jour
                salesData.add(new SalesByDay(rs.getDate("sale_date"), 
                                              rs.getInt("total_sold"), 
                                              rs.getDouble("total_debt"),
                                              rs.getDouble("total_payment"))); // Ajout du paiement total
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }
}

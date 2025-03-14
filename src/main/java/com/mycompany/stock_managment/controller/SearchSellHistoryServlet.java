package com.mycompany.stock_managment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mycompany.stock_managment.model.Vente;
import com.mycompany.stock_managment.service.ProductService;

@WebServlet("/searchSellHistory")
public class SearchSellHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        ProductService productService = new ProductService();
        // Récupérer les résultats en fonction de la recherche
        List<Vente> results = productService.searchVentes(query);

        // Convertir en JSON avec Gson
        Gson gson = new Gson();
        String json = gson.toJson(results);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

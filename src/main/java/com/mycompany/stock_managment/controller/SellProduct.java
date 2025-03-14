package com.mycompany.stock_managment.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.stock_managment.model.Product;
import com.mycompany.stock_managment.model.Vente;
import com.mycompany.stock_managment.service.ProductService;
import com.mycompany.stock_managment.util.FormField;

@WebServlet(name = "SellProduct", urlPatterns = {"/admin/sellproduct"})
public class SellProduct extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Product> products = productService.getAllProducts(); // Recharger les produits

        List<List<FormField>> formGroups = List.of(
            List.of(
                new FormField("Products", "product_id", true, products),
                new FormField("Product quantity", "number", "Add product quantity", "product_quantity", true)
            ),
            List.of(
                new FormField("Full money", "number", "Add money", "product_money", true),
                new FormField("Paiement", "number", "Paiement", "product_paiement", true)
            )
        );

        request.setAttribute("formGroups", formGroups);
        request.getRequestDispatcher("/views/sellproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int quantity = Integer.parseInt(request.getParameter("product_quantity"));
            double totalMoney = Double.parseDouble(request.getParameter("product_money"));
            double payment = Double.parseDouble(request.getParameter("product_paiement"));

            Vente vente = new Vente(quantity, productId, totalMoney, payment);

            Map<String, String> message = productService.sellProduct(vente);

            if (message.containsKey("error")) {
                request.setAttribute("errorMessage", message.get("error"));
            } else {
                request.setAttribute("successMessage", message.get("success"));
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Veuillez entrer des valeurs valides !");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Une erreur est survenue : " + e.getMessage());
        }

        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour vendre un produit";
    }
}

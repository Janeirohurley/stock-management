package com.mycompany.stock_managment.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.stock_managment.model.Product;
import com.mycompany.stock_managment.model.Vente;
import com.mycompany.stock_managment.service.ProductService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin") // Spécifie l'URL de la servlet
public class AdminServlet extends HttpServlet {
    public int calculerTotalSold(List<Vente> ventes) {  // "public" doit être avant "double"
    return ventes.stream()
                 .mapToInt(Vente::getQuantite)
                 .sum();
}

public Map<String, Double> calculePourcentage(List<Product> products, int totalSold) {
    int productCount = products.stream().mapToInt(Product::getQuantite).sum();

    // Vérifier pour éviter une division par zéro
    double pourcentage = (productCount == 0) ? 0 : (totalSold * 100.0) / productCount;

    // Création d'une Map pour stocker les résultats
    Map<String, Double> result = new HashMap<>();
    result.put("pourcentage", pourcentage);
    result.put("quantite", (double) productCount);

    return result;
}



    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traitement des requêtes GET
        // Vous pouvez ajouter des attributs à la requête avant de transférer
        request.setAttribute("message", "Bienvenue sur la page d'administration");
    ProductService productService = new ProductService();
    List<Product> products = productService.getAllProducts();
    Number dette = productService.getNumberOFdette();
    List<Vente> sellhistory = productService.getAllVentesHistory();
    int totalMontant = calculerTotalSold(sellhistory);

    Map<String, Double> stats = calculePourcentage(products, totalMontant);
    request.setAttribute("products", products);
    request.setAttribute("dette", dette);
    request.setAttribute("sellhistory", sellhistory);
    request.setAttribute("totalProducts", products.size());
    request.setAttribute("totalMontant", totalMontant);
    request.setAttribute("pourcentage", stats.get("pourcentage"));
    request.setAttribute("quantiteProduct", stats.get("quantite"));

        // Transférer la requête vers le fichier JSP
        request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traitement des requêtes POST
        // Vous pouvez également gérer des formulaires POST ici et transférer ensuite
        String postMessage = "Formulaire envoyé avec succès";
        request.setAttribute("message", postMessage);

        // Transférer la requête vers le fichier JSP
        request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Page d'administration";
    }
}

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
import com.mycompany.stock_managment.service.ProductService;
import com.mycompany.stock_managment.util.FormField;

@WebServlet(name = "AddProduct", urlPatterns = {"/admin/addproduct"})
public class AddProduct extends HttpServlet {
    private  ProductService addedProductservice = new ProductService();

    List<List<FormField>> formGroups = List.of(
        List.of(
            new FormField("Product Name", "text", "Add product name", "product_name",true),
            new FormField("Product Price", "number", "Add product price", "product_price",true)
        ),
        List.of(
            new FormField("Product Description", "text", "Enter description", "product_description",false),
            new FormField("Product quantity", "number", "Enter product quantity", "product_quantity",true)
        )
    );
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Rediriger vers la page JSP pour afficher le formulaire d'ajout de produit

         
        request.setAttribute("formGroups", formGroups);
        request.getRequestDispatcher("/views/addproduct.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération des données envoyées depuis le formulaire
        String productName = request.getParameter("product_name");
        double productPrice =  Double.parseDouble(request.getParameter("product_price"));
        String productDescription = request.getParameter("product_description");
        int productQuantity =  Integer.parseInt(request.getParameter("product_quantity"));
    
        // Appel du service pour ajouter le produit
        Map<String, String> message = addedProductservice.addProduct(new Product(productName, productDescription, productPrice, productQuantity));
    
        // Vérification du message retourné par le service
        if (message.containsKey("error")) {
            // Si une erreur est présente, tu peux gérer l'erreur ici, par exemple en affichant un message
            request.setAttribute("errorMessage", message.get("error"));
            request.getRequestDispatcher("/views/addproduct.jsp").forward(request, response); // Redirige vers une page d'erreur
        } else {
            // Si l'ajout a réussi, redirection vers la liste des produits
            request.setAttribute("successMessage", message.get("success"));
            request.setAttribute("formGroups", formGroups);
            request.getRequestDispatcher("/views/addproduct.jsp").forward(request, response);
        }
    }
    
}

package com.mycompany.stock_managment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet pour gérer la déconnexion de l'utilisateur.
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/admin/logout"})
public class LogoutServlet extends HttpServlet {

    /**
     * Gère les requêtes GET pour la déconnexion.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer la session et la fermer
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Détruit la session
        }

        // Rediriger vers la page d'administration (ou login si nécessaire)
        response.sendRedirect(request.getContextPath() + "/admin");
    }

    /**
     * Gère les requêtes POST pour la déconnexion (optionnel).
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Redirige le POST vers GET
    }
}

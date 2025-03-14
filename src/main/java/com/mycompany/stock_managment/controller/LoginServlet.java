package com.mycompany.stock_managment.controller;

import com.mycompany.stock_managment.model.Login;
import com.mycompany.stock_managment.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Login login = new Login(username, password);

        boolean isValidUser = loginService.validateUser(login);

        if (isValidUser) {
            // Récupérer le rôle de l'utilisateur
            String role = loginService.getUserRole(login);

            // Créer une session et stocker l'utilisateur et son rôle
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            session.setAttribute("role", role);
            response.sendRedirect(request.getContextPath() + "/admin");
        } else {
            request.setAttribute("errorMessage", "Nom d'utilisateur ou mot de passe incorrect.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            // response.sendRedirect(request.getContextPath() + "/login?error=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}

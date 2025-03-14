package com.mycompany.stock_managment.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*") // Appliquer ce filtre à toutes les pages
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation si nécessaire
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Ne pas créer de session

        String path = req.getRequestURI();
        String role = (session != null) ? (String) session.getAttribute("role") : null;

        // Si l'utilisateur n'est pas connecté et essaie d'accéder à une page protégée
        if (session == null || session.getAttribute("user") == null) {
            if (path.startsWith(req.getContextPath() + "/admin") || path.startsWith(req.getContextPath() + "/user")) {
                res.sendRedirect(req.getContextPath() + "/login");
                return;
            }
        }

        // Protection par rôle
        if (role != null) {
            if (path.startsWith(req.getContextPath() + "/admin") && !"admin".equals(role)) {
                res.sendRedirect(req.getContextPath() + "/user");
                return;
            }
            if (path.startsWith(req.getContextPath() + "/user") && !"user".equals(role)) {
                res.sendRedirect(req.getContextPath() + "/admin/dashboard");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Nettoyage si nécessaire
    }
}

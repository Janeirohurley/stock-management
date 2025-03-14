package com.mycompany.stock_managment.model;

public class Login {
    private String username;
    private String password;
    private String role; // Ajout de l'attribut role

    // Constructeur avec paramètre pour username et password, rôle par défaut "admin"
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "admin"; // Rôle par défaut
    }

    // Getters et Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Méthode toString() pour afficher l'objet
    @Override
    public String toString() {
        return "Login [username=" + username + ", password=" + password + ", role=" + role + "]";
    }

    // Validation simple pour le mot de passe (par exemple)
    public boolean isValidPassword() {
        return password != null && password.length() >= 6; // Exemple de validation basique
    }

    // Redéfinir equals et hashCode pour la comparaison d'objets si nécessaire
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Login login = (Login) obj;
        return username.equals(login.username) && password.equals(login.password) && role.equals(login.role);
    }

    @Override
    public int hashCode() {
        return 31 * username.hashCode() + password.hashCode() + role.hashCode();
    }
}

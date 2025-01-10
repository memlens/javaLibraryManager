package com.bibliotheque.models;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    private String nom;
    private String prenom;
    private String email;
    private List<Livre> emprunts;

    public Utilisateur(String nom, String prenom, String email) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide.");
        }
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être vide.");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("L'email n'est pas valide.");
        }
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.emprunts = new ArrayList<>();
    }

    //******** Accesseurs *******/
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMail() { return email; }
    public List<Livre> getEmprunts() { return new ArrayList<>(emprunts); }


    public void ajouterEmprunt(Livre livre) {
        emprunts.add(livre);
    }

    public void retournerLivre(Livre livre) {
    	emprunts.remove(livre);
    }

    @Override
    public String toString() {
        return "Utilisateur: " + nom + " " + prenom + ", Emprunts: " + emprunts;
    }
}

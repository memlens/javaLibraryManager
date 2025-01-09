package com.bibliotheque.models;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    private String nom;
    private List<Livre> emprunts;

    public Utilisateur(String nom) {
        this.nom = nom;
        this.emprunts = new ArrayList<>();
    }

    public String getNom() { return nom; }
    public List<Livre> getEmprunts() { return emprunts; }

    public void ajouterEmprunt(Livre livre) {
        emprunts.add(livre);
    }

    public void retournerLivre(Livre livre) {
        emprunts.remove(livre);
    }

    @Override
    public String toString() {
        return "Utilisateur: " + nom + ", Emprunts: " + emprunts.size();
    }
}

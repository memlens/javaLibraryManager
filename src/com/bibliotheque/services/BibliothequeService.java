package com.bibliotheque.services;

import com.bibliotheque.models.Livre;
import com.bibliotheque.models.Utilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BibliothequeService {
    private List<Livre> livres = new ArrayList<>();
    private HashMap<String, Utilisateur> utilisateurs = new HashMap<>();

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public List<Livre> rechercherLivre(String critere, String valeur) {
        List<Livre> resultats = new ArrayList<>();
        for (Livre livre : livres) {
            if ((critere.equals("titre") && livre.getTitre().equalsIgnoreCase(valeur)) ||
                (critere.equals("auteur") && livre.getAuteur().equalsIgnoreCase(valeur)) ||
                (critere.equals("genre") && livre.getGenre().equalsIgnoreCase(valeur))) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    public boolean emprunterLivre(String nomUtilisateur, Livre livre) {
        if (!livre.isDisponible()) return false;

        utilisateurs.putIfAbsent(nomUtilisateur, new Utilisateur(nomUtilisateur));
        Utilisateur utilisateur = utilisateurs.get(nomUtilisateur);
        utilisateur.ajouterEmprunt(livre);
        livre.setDisponible(false);
        return true;
    }

    public boolean retournerLivre(String nomUtilisateur, Livre livre) {
        Utilisateur utilisateur = utilisateurs.get(nomUtilisateur);
        if (utilisateur != null && utilisateur.getEmprunts().contains(livre)) {
            utilisateur.retournerLivre(livre);
            livre.setDisponible(true);
            return true;
        }
        return false;
    }

    public List<Livre> getLivresDisponibles() {
        List<Livre> disponibles = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.isDisponible()) disponibles.add(livre);
        }
        return disponibles;
    }

    public List<Livre> getLivresEmpruntes() {
        List<Livre> empruntes = new ArrayList<>();
        for (Livre livre : livres) {
            if (!livre.isDisponible()) empruntes.add(livre);
        }
        return empruntes;
    }
}

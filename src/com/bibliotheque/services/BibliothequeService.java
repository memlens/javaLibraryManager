package com.bibliotheque.services;

import com.bibliotheque.models.Livre;
import com.bibliotheque.models.Utilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliothequeService {
    private List<Livre> livres = new ArrayList<>();
    private Map<String, Utilisateur> utilisateurs = new HashMap<>(); //pour associer un mail à un utilisateur
    private Map<Livre, Utilisateur> emprunts = new HashMap<>();//pour associer un livre à un utilisateur 

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public List<Livre> rechercherLivre(String critere, String valeur) {
        List<Livre> resultats = new ArrayList<>();
        for (Livre livre : livres) {
            switch (critere.toLowerCase()) {
                case "titre" -> {
                    if (livre.getTitre().toLowerCase().contains(valeur.toLowerCase())) {
                        resultats.add(livre);
                    }
                }
                case "auteur" -> {
                    if (livre.getAuteur().toLowerCase().contains(valeur.toLowerCase())) {
                        resultats.add(livre);
                    }
                }
                case "genre" -> {
                    if (livre.getGenre().toLowerCase().contains(valeur.toLowerCase())) {
                        resultats.add(livre);
                    }
                }
                default -> throw new IllegalArgumentException("Critère de recherche invalide : " + critere);
            }
        }
        return resultats;
    }

    public boolean emprunterLivre(String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, Livre livreUtilisateur) {
        if (!livreUtilisateur.isDisponible()) return false;
        utilisateurs.putIfAbsent(nomUtilisateur, new Utilisateur(nomUtilisateur, prenomUtilisateur, emailUtilisateur));
        Utilisateur utilisateur = utilisateurs.get(emailUtilisateur);
        if(!utilisateur.getEmprunts().contains(livreUtilisateur)) {
        	utilisateur.ajouterEmprunt(livreUtilisateur);
        	emprunts.put(livreUtilisateur, utilisateur);
        	livreUtilisateur.setDisponible(false);
        	return true;
        }
        return false;
    }
    
    public boolean retournerLivre(String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, Livre livreUtilisateur) {
        Utilisateur utilisateur = utilisateurs.get(emailUtilisateur);
        if (utilisateur != null && emprunts.containsKey(livreUtilisateur) && emprunts.get(livreUtilisateur).equals(utilisateur)) {
            emprunts.remove(livreUtilisateur);
            utilisateur.retournerLivre(livreUtilisateur);
            livreUtilisateur.setDisponible(true);
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
        return new ArrayList<>(emprunts.keySet());
    }

    public List<Utilisateur> getUtilisateurs() {
        return new ArrayList<>(utilisateurs.values());
    }
    
    public Map<Livre, Utilisateur> getEmprunts(){
    	return new HashMap<>(emprunts);
    }

    public Utilisateur getUtilisateurParEmail(String email) {
        return utilisateurs.get(email);
    }
    
    public void initialiserDonnees() {
        // Ajout de livres
        ajouterLivre(new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "Conte"));
        ajouterLivre(new Livre("1984", "George Orwell", 1949, "Science-Fiction"));
        ajouterLivre(new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", 1954, "Fantasy"));
        ajouterLivre(new Livre("Harry Potter à l'école des sorciers", "J.K. Rowling", 1997, "Fantasy"));
        ajouterLivre(new Livre("Les Misérables", "Victor Hugo", 1862, "Roman"));
        ajouterLivre(new Livre("L'arrestation d'Arsène Lupin", "Maurice Leblanc", 1862, "Roman"));

        // Ajout d'utilisateurs
        utilisateurs.put("memlens@example.com", new Utilisateur("mem", "lens", "memlens@example.com"));
        utilisateurs.put("alice@example.com", new Utilisateur("Alice", "Dupont", "alice@example.com"));
        utilisateurs.put("bob@example.com", new Utilisateur("Bob", "Martin", "bob@example.com"));
    }
}

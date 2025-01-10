package com.bibliotheque.main;

import com.bibliotheque.models.Livre;
import com.bibliotheque.services.BibliothequeService;
import com.bibliotheque.models.Utilisateur;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliothequeService service = new BibliothequeService();
        service.initialiserDonnees();

        while (true) {
            System.out.println("\n=== Bibliothèque Virtuelle ===");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre");
            System.out.println("3. Emprunter un livre");
            System.out.println("4. Retourner un livre");
            System.out.println("5. Afficher les statistiques");
            System.out.println("6. Quitter");
            System.out.print("Choix : ");
            int choix = 0;
            try {
                choix = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
                continue;
            }

            switch (choix) {
                case 1 -> {
                    System.out.print("Titre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur : ");
                    String auteur = scanner.nextLine();
                    System.out.print("Année : ");
                    int annee = 0;
                    try {
                        annee = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Année invalide. Veuillez entrer un nombre.");
                        continue;
                    }
                    System.out.print("Genre : ");
                    String genre = scanner.nextLine();
                    service.ajouterLivre(new Livre(titre, auteur, annee, genre));
                    System.out.println("Livre ajouté !");
                }
                case 2 -> {
                    List<String> criteres = List.of("titre", "auteur", "genre");
                    String critere;
                    while (true) {
                        System.out.print("Rechercher par (titre/auteur/genre) : ");
                        critere = scanner.nextLine().toLowerCase();
                        if (criteres.contains(critere)) {
                            break;
                        } else {
                            System.out.println("Critère invalide. Veuillez choisir parmi : titre, auteur, genre.");
                        }
                    }
                    System.out.print("Valeur : ");
                    String valeur = scanner.nextLine();
                    List<Livre> resultats = service.rechercherLivre(critere, valeur);
                    if (resultats.isEmpty()) {
                        System.out.println("Aucun livre trouvé avec le critère '" + critere + "' et la valeur '" + valeur + "'.");
                    } else {
                        resultats.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("Nom utilisateur : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom utilisateur : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email utilisateur : ");
                    String email = scanner.nextLine();
                    System.out.print("Titre du livre : ");
                    String titre = scanner.nextLine();
                    List<Livre> livres = service.rechercherLivre("titre", titre);
                    if (!livres.isEmpty() && service.emprunterLivre(nom, prenom, email, livres.get(0))) {
                        System.out.println("Livre emprunté avec succès !");
                    } else {
                        System.out.println("Livre indisponible ou introuvable.");
                    }
                }
                case 4 -> {
                    System.out.print("Nom utilisateur : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom utilisateur : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email utilisateur : ");
                    String email = scanner.nextLine();
                    System.out.print("Titre du livre : ");
                    String titre = scanner.nextLine();
                    List<Livre> livres = service.rechercherLivre("titre", titre);
                    if (!livres.isEmpty() && service.retournerLivre(nom, prenom, email,livres.get(0))) {
                        System.out.println("Livre retourné avec succès !");
                    } else {
                        System.out.println("Retour impossible.");
                    }
                }
                case 5 -> {
                    afficherLivres(service.getLivresDisponibles(), service.getEmprunts(), "Livres disponibles", VERT);
                    afficherLivres(service.getLivresEmpruntes(), service.getEmprunts(), "Livres empruntés", ROUGE);
                }
                case 6 -> {
                    System.out.println("Au revoir !");
                    return;
                }
                default -> System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 6.");
            }
        }
    }
    
    public static final String RESET = "\u001B[0m";
    public static final String ROUGE = "\u001B[31m";
    public static final String VERT = "\u001B[32m";
    public static final String JAUNE = "\u001B[33m";
    public static final String BLEU = "\u001B[34m";

    public static void afficherLivres(List<Livre> livres, Map<Livre, Utilisateur> emprunts, String titreSection, String couleur) {
    	System.out.println(couleur + "\n=========================================" + RESET);
        System.out.println(couleur + "\n==========  " + titreSection + " ==========" + RESET);
        System.out.println(couleur + "\n=========================================" + RESET);
        if (livres.isEmpty()) {
            System.out.println(ROUGE + "Aucun livre à afficher." + RESET);
            return;
        }

        // Calculer la largeur maximale pour chaque colonne
        int largeurTitre = Math.max(calculerLargeurMax(livres, Livre::getTitre), 5);
        int largeurAuteur = Math.max(calculerLargeurMax(livres, Livre::getAuteur), 6);
        int largeurGenre = Math.max(calculerLargeurMax(livres, Livre::getGenre), 5);
        int largeurNom = Math.max(calculerLargeurMaxUtilisateurs(new ArrayList<>(emprunts.values()), Utilisateur::getNom), 6);
        int largeurEmail = Math.max(calculerLargeurMaxUtilisateurs(new ArrayList<>(emprunts.values()), Utilisateur::getMail), 6);

        // En-tête du tableau
        System.out.println("+-" + "-".repeat(largeurTitre) + "-+-" + "-".repeat(largeurAuteur) + "-+------+-" + "-".repeat(largeurGenre) + "-+-" + "-".repeat(largeurNom) + "-+-" + "-".repeat(largeurEmail) + "-+" + RESET);
        System.out.printf(
            "| %-" + largeurTitre + "s | %-" + largeurAuteur + "s | Année | %-" + largeurGenre + "s | %-" + largeurNom + "s | %-" + largeurEmail + "s |\n",
            "Titre", "Auteur", "Genre", "Emprunteur", "Email"
        );
        System.out.println("+-" + "-".repeat(largeurTitre) + "-+-" + "-".repeat(largeurAuteur) + "-+------+-" + "-".repeat(largeurGenre) + "-+-" + "-".repeat(largeurNom) + "-+-" + "-".repeat(largeurEmail) + "-+" + RESET);

        // Contenu du tableau
        for (Livre livre : livres) {
            Utilisateur emprunteur = emprunts.get(livre);
            String nomEmprunteur = (emprunteur != null) ? emprunteur.getNom() : "";
            String emailEmprunteur = (emprunteur != null) ? emprunteur.getMail() : "";

            System.out.printf(
                "| %-" + largeurTitre + "s | %-" + largeurAuteur + "s | %-4d | %-" + largeurGenre + "s | %-" + largeurNom + "s | %-" + largeurEmail + "s |\n" + RESET,
                tronquerTexte(livre.getTitre(), largeurTitre),
                tronquerTexte(livre.getAuteur(), largeurAuteur),
                livre.getAnneePublication(),
                tronquerTexte(livre.getGenre(), largeurGenre),
                tronquerTexte(nomEmprunteur, largeurNom),
                tronquerTexte(emailEmprunteur, largeurEmail)
            );
        }

        // Pied du tableau
        System.out.println("+-" + "-".repeat(largeurTitre) + "-+-" + "-".repeat(largeurAuteur) + "-+------+-" + "-".repeat(largeurGenre) + "-+-" + "-".repeat(largeurNom) + "-+-" + "-".repeat(largeurEmail) + "-+" + RESET);
    }
    
 // Méthode pour calculer la largeur maximale des colonnes (Livre)
    public static int calculerLargeurMax(List<Livre> livres, Function<Livre, String> getter) {
        return livres.stream()
                     .mapToInt(livre -> getter.apply(livre).length())
                     .max()
                     .orElse(0);
    }
 // Méthode pour calculer la largeur maximale des colonnes (Utilisateur)
    public static int calculerLargeurMaxUtilisateurs(List<Utilisateur> utilisateurs, Function<Utilisateur, String> getter) {
        return utilisateurs.stream()
                           .mapToInt(utilisateur -> getter.apply(utilisateur).length())
                           .max()
                           .orElse(0);
    }
    

 // Méthode pour tronquer les textes trop longs
    public static String tronquerTexte(String texte, int longueurMax) {
    	if (texte.length() <= longueurMax) {
    		return texte;
    	}
    	return texte.substring(0, longueurMax - 3) + "...";
    }
}
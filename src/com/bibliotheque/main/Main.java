package com.bibliotheque.main;

import com.bibliotheque.models.Livre;
import com.bibliotheque.services.BibliothequeService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliothequeService service = new BibliothequeService();

        while (true) {
            System.out.println("\n=== Bibliothèque Virtuelle ===");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre");
            System.out.println("3. Emprunter un livre");
            System.out.println("4. Retourner un livre");
            System.out.println("5. Afficher les statistiques");
            System.out.println("6. Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la ligne

            switch (choix) {
                case 1 -> {
                    System.out.print("Titre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur : ");
                    String auteur = scanner.nextLine();
                    System.out.print("Année : ");
                    int annee = scanner.nextInt();
                    scanner.nextLine(); // Consomme la ligne
                    System.out.print("Genre : ");
                    String genre = scanner.nextLine();
                    service.ajouterLivre(new Livre(titre, auteur, annee, genre));
                    System.out.println("Livre ajouté !");
                }
                case 2 -> {
                    System.out.print("Rechercher par (titre/auteur/genre) : ");
                    String critere = scanner.nextLine();
                    System.out.print("Valeur : ");
                    String valeur = scanner.nextLine();
                    List<Livre> resultats = service.rechercherLivre(critere, valeur);
                    if (resultats.isEmpty()) {
                        System.out.println("Aucun livre trouvé.");
                    } else {
                        resultats.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("Nom utilisateur : ");
                    String nom = scanner.nextLine();
                    System.out.print("Titre du livre : ");
                    String titre = scanner.nextLine();
                    List<Livre> livres = service.rechercherLivre("titre", titre);
                    if (!livres.isEmpty() && service.emprunterLivre(nom, livres.get(0))) {
                        System.out.println("Livre emprunté avec succès !");
                    } else {
                        System.out.println("Livre indisponible ou introuvable.");
                    }
                }
                case 4 -> {
                    System.out.print("Nom utilisateur : ");
                    String nom = scanner.nextLine();
                    System.out.print("Titre du livre : ");
                    String titre = scanner.nextLine();
                    List<Livre> livres = service.rechercherLivre("titre", titre);
                    if (!livres.isEmpty() && service.retournerLivre(nom, livres.get(0))) {
                        System.out.println("Livre retourné avec succès !");
                    } else {
                        System.out.println("Retour impossible.");
                    }
                }
                case 5 -> {
                    System.out.println("\nLivres disponibles :");
                    service.getLivresDisponibles().forEach(System.out::println);
                    System.out.println("\nLivres empruntés :");
                    service.getLivresEmpruntes().forEach(System.out::println);
                }
                case 6 -> {
                    System.out.println("Au revoir !");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }
}

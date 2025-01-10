package com.bibliotheque.models;

import java.util.Objects;

public class Livre {
    private final String titre;
    private final String auteur;
    private final int anneePublication;
    private final String genre;
    private boolean estDisponible = true;

    public Livre(String titre, String auteur, int anneePublication, String genre) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide.");
        }
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("L'auteur ne peut pas être vide.");
        }
        if (anneePublication < 1800 || anneePublication > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("L'année de publication n'est pas valide.");
        }
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le genre ne peut pas être vide.");
        }
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
    }

    //****** Accesseurs ******/
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public int getAnneePublication() { return anneePublication; }
    public String getGenre() { return genre; }
    public boolean isDisponible() { return estDisponible; }
    
    //****** Mutateur ******/
    public void setDisponible(boolean disponible) { estDisponible = disponible; }
    
    @Override
    public boolean equals(Object o) {
    	if(this == o) return true;
    	if(o == null || getClass() != o.getClass()) return false;
    	Livre livre = (Livre) o;
    	return anneePublication == livre.anneePublication && genre.equals(livre.genre) && auteur.equals(livre.auteur) && titre.equals(livre.titre);
    }

    @Override
    public int hashCode() {
    	return Objects.hash(titre, auteur, anneePublication, genre);
    }
    @Override
    public String toString() {
        return titre + " - " + auteur + " (" + anneePublication + ") [" + genre + "]";
    }
}

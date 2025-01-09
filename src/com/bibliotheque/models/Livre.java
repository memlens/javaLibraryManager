package com.bibliotheque.models;

public class Livre {
    private String titre;
    private String auteur;
    private int anneePublication;
    private String genre;
    private boolean estDisponible = true;

    public Livre(String titre, String auteur, int anneePublication, String genre) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
    }

    // Getters et Setters
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public int getAnneePublication() { return anneePublication; }
    public String getGenre() { return genre; }
    public boolean isDisponible() { return estDisponible; }
    public void setDisponible(boolean disponible) { estDisponible = disponible; }

    @Override
    public String toString() {
        return titre + " - " + auteur + " (" + anneePublication + ") [" + genre + "]";
    }
}

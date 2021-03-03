/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;

/**
Fichier dans lequel les cartes utilisées dans le jeu sont crées.
 */
public class Carte {

    private String couleur;         //Couleur de la carte (trefle, pique, coeur, carreau)
    private String numero;              //numéro de la carte
    private int pointCarte;             //valeur numérique de la carte
    

    public Carte(String couleur, String numero, int pointCarte) {
        this.couleur = couleur;
        this.numero = numero;
        this.pointCarte = pointCarte;
    }

    @Override
    public String toString() {
        return "Carte{" + "couleur=" + couleur + ", numero=" + numero + '}' + "\n";
    }

    public String getCouleur() {
        return couleur;
    }

    public int getPointCarte() {
        return pointCarte;
    }



}

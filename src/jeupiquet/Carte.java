/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;
import java.util.Comparator;

/**
Fichier dans lequel les cartes utilisées dans le jeu sont crées.
 */
public class Carte {

    private Couleur couleur;         //Couleur de la carte (trefle, pique, coeur, carreau)
    private Numero numero;              //numéro de la carte
    private int pointCarte;             //valeur numérique de la carte
    

    public Carte(Couleur couleur, Numero numero, int pointCarte) {
        this.couleur = couleur;
        this.numero = numero;
        this.pointCarte = pointCarte;
    }

    @Override
    public String toString() {
        return numero + " de " + couleur + ";";
    }
    
    //Ici, nous créons un comparateur de numéro de carte. Il sert a trier une liste de cartes en fonction des numéros de chaque carte.
    public static Comparator<Carte> ComparatorNumeroCarte = new Comparator<Carte>(){
        
        @Override
        public int compare(Carte carte1, Carte carte2){
            return(int)(carte1.getNumero().ordinal() - carte2.getNumero().ordinal());
        }
    };

    public Couleur getCouleur() {   
        return couleur;
    }

    public Numero getNumero() {
        return numero;
    }

    public int getPointCarte() {
        return pointCarte;
    }



}

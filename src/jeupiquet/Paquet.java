/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;
import java.util.Collections;

/*
Fichier dans lequel un jeu de carte est créé en chaque début de partie.
 */

/*

*/
public class Paquet {
    private final String[] _couleurs = {"trefle", "pique", "carreau", "coeur"};
    private final String[] _numeros = {"7", "8", "9", "10", "Valet", "Dame", "Roi", "As"};
    private final int[] _pointsCarte = {1, 2, 3, 4, 5, 6, 7, 8};
    private ArrayList<Carte> _listeCartes = new ArrayList(32);
    
    public Paquet(){
        creerPaquet();
    }
    
    //creerPaquet() permet d'ajouter à la liste qui servira de paquet, toutes les cartes nécessaire au jeu.
    public void creerPaquet() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                String couleur = _couleurs[j];
                String numero = _numeros[i];
                int pointCarte = _pointsCarte[i];

                _listeCartes.add(new Carte(couleur, numero, pointCarte));
            }
        }
    }
    //melangerPaquet() permet de mélanger la liste aléatoirement.
    public void melangerPaquet(){
        Collections.shuffle(_listeCartes);
    }
    
    public ArrayList<Carte> getListeCartes(){
        return _listeCartes;
    }
}

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
    private ArrayList<Carte> _listeCartes = new ArrayList(32);
    
    public Paquet(){
        creerPaquet();
    }
    
    //creerPaquet() permet d'ajouter à la liste qui servira de paquet, toutes les cartes nécessaires au jeu.
    public void creerPaquet() {
        for (Numero numero : Numero.values()){
            for (Couleur couleur : Couleur.values()) {
                _listeCartes.add(new Carte(couleur, numero, numero.getPointsCarte()));
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

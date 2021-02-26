/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;

/**
 *
 * @author theobaptiste
 */
public class JeuPiquet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        ArrayList mains = jeu.initialiserManche();
        int role = RandomUtils.nextInt(2);
        if (role == 0){
        }
        
        //On crée deux listes qui contiennent l'index de la carte dans la main du joueur. Ces cartes vont être ajoutés dans la liste des cartes écartées.
        ArrayList <Integer> ecarteJ1 = new ArrayList();
        ArrayList <Integer> ecarteJ2 = new ArrayList();
        ecarteJ1.set(0, 1);
        ecarteJ1.set(1, 5);
        ecarteJ1.set(2, 11);
        ecarteJ2.set(0, 3);
        ecarteJ2.set(1, 8);
        jeu.getJoueur1().ecarterCartes(ecarteJ1, jeu.getTalon());
        jeu.getJoueur2().ecarterCartes(ecarteJ2, jeu.getTalon());
    }
}
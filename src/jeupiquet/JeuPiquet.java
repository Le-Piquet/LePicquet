/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;
import java.util.Random;

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
        jeu.initialiserManche();
        //demande s'il y a CB. Si oui demander qui.
        jeu.annonceCB(jeu.getJoueur1());
        Random random = new Random();
        int role = random.nextInt(2);
        if (role == 0){
        }
        
        //On crée deux listes qui contiennent l'index de la carte dans la main du joueur. Ces cartes vont être ajoutés dans la liste des cartes écartées.
        ArrayList <Integer> ecarteJ1 = new ArrayList();
        ArrayList <Integer> ecarteJ2 = new ArrayList();
        ecarteJ1.add(0, 1);
        ecarteJ1.add(1, 5);
        ecarteJ1.add(2, 11);
        ecarteJ2.add(0, 3);
        ecarteJ2.add(1, 8);
        jeu.getJoueur1().ecarterCartes(ecarteJ1, jeu.getTalon());
        //On supprime les cartes du talon qui ont été pioché par les joueurs.
        //On crée une nouvelle liste pour pouvoir modifier le talon de la classe Jeu.
        ArrayList talon = new ArrayList();
        talon = jeu.getTalon();
        for (int k = 0; k<ecarteJ1.size(); k++){
            talon.remove(0);     //remove 0 car les cases sont supprimées lorsque l'on remove. Donc on remove a chaque fois la nouvelle première carte du talon
            jeu.setTalon(talon);
        }
        
        jeu.voirTalon();
        
        //Le deuxième joueur écarte ses cartes et pioche dans le talon.
        jeu.getJoueur2().ecarterCartes(ecarteJ2, jeu.getTalon());
        for (int k = 0; k<ecarteJ2.size(); k++){
            talon.remove(0);     //remove 0 car les cases sont supprimées lorsque l'on remove. Donc on remove a chaque fois la nouvelle première carte du talon
            jeu.setTalon(talon);
        }
    }
}
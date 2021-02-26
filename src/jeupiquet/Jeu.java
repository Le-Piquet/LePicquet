/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Object;

/**
 *
 * @author theobaptiste
 */
public class Jeu {

    private Joueur _joueur1 = new Joueur("Dupont", 0);
    private Joueur _joueur2 = new Joueur("Dupuit", 0);
    private ArrayList _talon;
    private Paquet _paquet = new Paquet();

    public Jeu() {

    }

    public ArrayList initialiserManche() {
//        On mélange le jeu de carte créé auparavant dans la classe Paquet à l'aide de la méthode shuffle.
       
        _paquet.melangerPaquet();

        ArrayList mainJ1 = _joueur1.getMain();
        ArrayList mainJ2 = _joueur2.getMain();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                mainJ1.set((i * 3) + j, _paquet.get(j));
                _joueur1.setMain(mainJ1);
                _paquet.remove(j);
            }
            for (int k = 0; k < 3; k++) {
                mainJ2.set((i * 3) + k, _paquet.get(k));
                _joueur2.setMain(mainJ2);
                _paquet.remove(k);
            }
        }

        for (int i = 0; i < 8; i++) {
            talon.set(i, paquetmelange.get(i));
        }
    }

    public void tirageRoles() {

    }

    public void setTalon(ArrayList talon) {
        this.talon = talon;
    }

    public ArrayList getTalon() {
        return talon;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    
    
    
}

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
public class Joueur {

    private ArrayList main;
    private String pseudo;
    private int score;
    private ArrayList ecartees;    //On stoque les dernières cartes écartées.

    public Joueur(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
    }
    
    //ecarterCartes permet au joueur d'écarter les cartes dont il n'a pas besoin et de les remplacer par autant de cartes du talon.
    public void ecarterCartes(ArrayList ecarte, ArrayList talon){
        int idCarte1 = ecarte.get(0);
        
        ecartees.set(0, main.get(idCarte1)));
        ecartees.set(1, main.get(idCarte2));
        ecartees.set(2, main.get(idCarte3));
        main.remove(idCarte1);
        main.remove(idCarte2);
        main.remove(idCarte3);
        main.add(talon.get(0));
        main.add(talon.get(1));
        main.add(talon.get(2));
    }
    
    
    
    
    
    
    
    
    
    

    public ArrayList getMain() {
        return main;
    }

    public void setMain(ArrayList main) {
        this.main = main;
    }

}

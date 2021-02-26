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

    private ArrayList main = new ArrayList(12);
    private String pseudo;
    private int score;

    public Joueur(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
    }
    
    //ecarterCartes permet au joueur d'écarter les cartes dont il n'a pas besoin et de les remplacer par autant de cartes du talon.
    public void ecarterCartes(ArrayList ecarte, ArrayList talon){
        for (int i = 0; i<ecarte.size(); i++){
            main.remove(ecarte.get(i));
            //Problème : les cartes ecartées ne sortent pas de la main
        }
        for (int j = 0; j<ecarte.size(); j++){
            main.add(talon.get(j));
        }
        
    }
    
    
    
    
    
    
    
    
    
    

    public ArrayList getMain() {
        return main;
    }

    public void setMain(ArrayList main) {
        this.main = main;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    
    

}

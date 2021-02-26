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
public class Ordinateur extends Joueur {
    
    private ArrayList main;
    private String pseudo;
    private int score;

    public Ordinateur(String pseudo, int score) {
        super(pseudo, score);
    }
    
}

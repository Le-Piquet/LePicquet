/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;

/**
 *
 * @author abour
 */
public class Paquet {
    private final String[] couleurs = {"trefle", "pique", "carreau", "coeur"};
    private final String[] numeros = {"7", "8", "9", "10", "Valet", "Dame", "Roi", "As"};
    private final int[] pointsCarte = {1, 2, 3, 4, 5, 6, 7, 8};
    ArrayList Paquet = new ArrayList(31);
    
    public void creationPaquet() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                String couleur = couleurs[j];
                String numero = numeros[i];
                int pointCarte = pointsCarte[i];

                Paquet.add(new Carte(couleur, numero, pointCarte));
            }
        }
    }
    
    public ArrayList getPaquet(){
        return Paquet;
    }
}

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
public class Carte {

    private String couleur;
    private String numero;
    private int pointCarte;
    ArrayList Talon = new ArrayList();

    public Carte(String couleur, String numero, int pointCarte) {
        this.couleur = couleur;
        this.numero = numero;
        this.pointCarte = pointCarte;
    }

    private final String[] couleurs = {"trefle", "pique", "carreau", "coeur"};
    private final String[] numeros = {"7", "8", "9", "10", "Valets", "Dames", "Rois","As"};
    private final int[] pointsCarte = {1,2,3,4,5,6,7,8};  
    
  
     
       for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                couleur = couleurs[j];
                numero = numeros[i]; 
                pointCarte = pointsCarte[i];
 
                Talon.add(new Carte(couleur,numero,pointCarte)); }}
    
    


}

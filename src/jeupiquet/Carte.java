/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

/**
 *
 * @author theobaptiste
 */
public class Carte {

    private String couleur;
    private int numero;
    private int pointsCarte;

    public Carte(String couleur, int numero, int pointsCarte) {
        this.couleur = couleur;
        this.numero = numero;
        this.pointsCarte = pointsCarte;
    }

}

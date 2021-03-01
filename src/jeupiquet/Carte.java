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
    

    public Carte(String couleur, String numero, int pointCarte) {
        this.couleur = couleur;
        this.numero = numero;
        this.pointCarte = pointCarte;
    }

    @Override
    public String toString() {
        return "Carte{" + "couleur=" + couleur + ", numero=" + numero + ", pointCarte=" + pointCarte + '}' + "\n";
    }

    public String getCouleur() {
        return couleur;
    }

    public int getPointCarte() {
        return pointCarte;
    }



}


import java.util.ArrayList;
import jeupiquet.Carte;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author theobaptiste
 */
public class Talon  {

    private ArrayList Talon;
    private final String[] couleurs = {"trefle", "pique", "carreau", "coeur"};
    private final String[] numeros = {"7", "8", "9", "10", "Valets", "Dames", "Rois", "As"};
    private final int[] pointsCarte = {1, 2, 3, 4, 5, 6, 7, 8};

    public void creationTalon() {
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                Carte.couleur = couleurs[j];
                Carte.numero = numeros[i];
                Carte.pointCarte = pointsCarte[i];

                Talon.add(new Carte(Carte.couleur, Carte.numero, Carte.pointCarte));
            }
        }
    }

}

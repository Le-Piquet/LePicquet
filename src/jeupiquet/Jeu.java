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
import java.util.Scanner;

/**
 *
 * @author theobaptiste
 */
public class Jeu {

    private Joueur _joueur1 = new Joueur("Dupont", 0);
    private Joueur _joueur2 = new Joueur("Dupuit", 0);
    private ArrayList<Carte> _talon = new ArrayList(8);
    private Paquet _paquet = new Paquet();

    public Jeu() {

    }

    

    public void initialiserManche() {
        /*        
    On distribue les cartes 3 par 3. On prend 3 cartes du paquet que l'on mets dans la main d'un joueur puis nous supprimons ces 3 cartes du paquet.
    On fait la même chose pour le second joueur. Et nous répettons cette opération 4 fois.
    Le talon est composé des 8 cartes restantes.
         */
        _paquet.melangerPaquet();

        ArrayList mainJ1 = _joueur1.getMain();
        ArrayList mainJ2 = _joueur2.getMain();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                mainJ1.add((i * 3) + j, _paquet.getListeCartes().get(j));
                _joueur1.setMain(mainJ1);
            }
            for (int m = 0; m < 3; m++) {
                _paquet.getListeCartes().remove(0);
            }

            for (int k = 0; k < 3; k++) {
                mainJ2.add((i * 3) + k, _paquet.getListeCartes().get(k));
                _joueur2.setMain(mainJ2);
            }

            for (int n = 0; n < 3; n++) {
                _paquet.getListeCartes().remove(0);
            }
        }

        for (int i = 0; i < 8; i++) {
            _talon.add(i, _paquet.getListeCartes().get(i));
        }
    }

    public void voirTalon() {
        for (int i = 0; i < _talon.size(); i++) {
            _talon.get(i).toString();
        }
    }

    public void annonceCB(Joueur joueur) {
        joueur.setScore(joueur.getScore() + 10);
    }

    public void calculerScorePoint() {
        if (getJoueur1().getNbCartePoint() > getJoueur2().getNbCartePoint()) {
            getJoueur1().setScore(getJoueur1().getScore() + getJoueur1().getNbCartePoint());
            System.out.println(_joueur1.getPseudo() + " gagne la manche des Points et remporte " + getJoueur1().getNbCartePoint() + " points.");
        }
        else if (getJoueur1().getNbCartePoint() < getJoueur2().getNbCartePoint()){
            getJoueur2().setScore(getJoueur2().getScore() + getJoueur2().getNbCartePoint());
            System.out.println(_joueur2.getPseudo() + " gagne la manche des Points et remporte " + getJoueur2().getNbCartePoint() + " points.");
        }
        else if (getJoueur1().getNbCartePoint() == getJoueur2().getNbCartePoint()){
            if (getJoueur1().getValeurPoint() > getJoueur2().getValeurPoint()){
                getJoueur1().setScore(getJoueur1().getScore() + getJoueur1().getNbCartePoint());
                System.out.println(_joueur1.getPseudo() + " gagne la manche des Points.");
            } else if (getJoueur1().getValeurPoint() < getJoueur2().getValeurPoint()) {
                getJoueur2().setScore(getJoueur2().getScore() + getJoueur2().getNbCartePoint());
                System.out.println(_joueur2.getPseudo() + " gagne la manche des Points.");
            } else if (getJoueur1().getValeurPoint() == getJoueur2().getValeurPoint()) {
            }
        }
    }
    
    
    public void levees() {
        Scanner sc = new Scanner(System.in);
        int refCarteJ1, refCarteJ2;

        Joueur gagnant = _joueur1;
        Joueur perdant = _joueur2;
        
        while (!_joueur1.getMain().isEmpty()) {
            //Mettre cette partie dans la methode prendre carte qu'une seule fois. Plus propre.
            System.out.println(gagnant.getPseudo() + ", quelle carte de votre main voullez vous donnez ?"); 
            refCarteJ1 = sc.nextInt();
            System.out.println("La carte posé par " + gagnant.getPseudo() + " est " + gagnant.prendreCarte(refCarteJ1));
            System.out.println(perdant.getPseudo() + ", quelle carte de votre main voullez vous donnez ?");
            refCarteJ2 = sc.nextInt();
            System.out.println("La carte posé par " + perdant.getPseudo() + " est " + perdant.prendreCarte(refCarteJ2));

            if (gagnant.prendreCarte(refCarteJ1).getCouleur() == perdant.prendreCarte(refCarteJ2).getCouleur()){
                if (gagnant.prendreCarte(refCarteJ1).getPointCarte() < perdant.prendreCarte(refCarteJ2).getPointCarte()) {
                    perdant.setScore(_joueur2.getScore() + 1);
                    System.out.println(perdant.getPseudo() + " remporte le pli, il commence. ");
                    gagnant = _joueur2;
                    perdant = _joueur1;
                }

                else if (gagnant.prendreCarte(refCarteJ1).getPointCarte() > perdant.prendreCarte(refCarteJ2).getPointCarte()) {
                    gagnant.setScore(_joueur1.getScore() + 1);
                    System.out.println(gagnant.getPseudo() + " remporte le pli, il commence ");
                    gagnant = _joueur1;
                    perdant = _joueur2;
                }
            }
            else{
                gagnant.setScore(_joueur1.getScore() + 1);
                System.out.println("Le joueur 1 remporte le pli, il commence. ");
                
            }
        }
    }

    public void setTalon(ArrayList<Carte> talon) {
        this._talon = talon;
    }

    public ArrayList<Carte> getTalon() {
        return _talon;
    }

    public Joueur getJoueur1() {
        return _joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this._joueur1 = joueur1;
    }

    public Joueur getJoueur2() {
        return _joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this._joueur2 = joueur2;
    }

}

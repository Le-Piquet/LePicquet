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

/*
Fichier dans lequel la classe Jeu est définie. 
 */
public class Jeu {

    private Joueur _joueur1;                //On déclare un premier joueur
    private Joueur _joueur2;                //On déclare un second joueur
    private ArrayList<Carte> _talon = new ArrayList(8);             //Le talon est construit sous forme d'une liste de cartes
    private Paquet _paquet = new Paquet();                  //On crée un paquet de cartes

    public Jeu(String pseudo1, String pseudo2) {
        _joueur1 = new Joueur(pseudo1, 0);
        _joueur2 = new Joueur(pseudo2, 0);
    }

    public void initialiserManche() {
        /*        
        On distribue les cartes 3 par 3. On prend 3 cartes du paquet que l'on mets dans la main d'un joueur puis nous supprimons ces 3 cartes du paquet.
        On fait la même chose pour le second joueur. Et nous répettons cette opération 4 fois.
        
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
        
        //Le talon est composé des 8 cartes restantes.
        for (int i = 0; i < 8; i++) {
            _talon.add(i, _paquet.getListeCartes().get(i));
        }
    }
    
    //voirTalon() permet d'afficher le contenu du talon dans la console
    public void voirTalon() {
        System.out.println("Voici les cartes restantes dans le talon : " + "\n");
        for (int i = 0; i < _talon.size(); i++) {
            System.out.println(_talon.get(i).toString());
        }
    }

    //annonceCB() ajoute 10 points au joueur qui fait "carte blanche".
    public void annonceCB(Joueur joueur) {
        joueur.setScore(joueur.getScore() + 10);
    }
    
    //calculerScorePoint() détermine qui gagne la phase des points en fonction de la taille du plus grand point de chaque joueur.
    public void calculerScorePoint() {
        if (getJoueur1().getNbCartePoint() > getJoueur2().getNbCartePoint()) {
            getJoueur1().setScore(getJoueur1().getScore() + getJoueur1().getNbCartePoint());
            System.out.println(_joueur1.getPseudo() + " gagne la manche des points et remporte " + getJoueur1().getNbCartePoint() + " points.");
        }
        else if (getJoueur1().getNbCartePoint() < getJoueur2().getNbCartePoint()){
            getJoueur2().setScore(getJoueur2().getScore() + getJoueur2().getNbCartePoint());
            System.out.println(_joueur2.getPseudo() + " gagne la manche des points et remporte " + getJoueur2().getNbCartePoint() + " points.");
        }
        ////Si les tailles des points des 2 joueurs sont égales, on compare leurs valeurs.
        else if (getJoueur1().getNbCartePoint() == getJoueur2().getNbCartePoint()){
            if (getJoueur1().getValeurPoint() > getJoueur2().getValeurPoint()){
                getJoueur1().setScore(getJoueur1().getScore() + getJoueur1().getNbCartePoint());
                System.out.println(_joueur1.getPseudo() + " gagne la manche des points et remporte " + getJoueur1().getNbCartePoint() + " points.");
            } else if (getJoueur1().getValeurPoint() < getJoueur2().getValeurPoint()) {
                getJoueur2().setScore(getJoueur2().getScore() + getJoueur2().getNbCartePoint());
                System.out.println(_joueur2.getPseudo() + " gagne la manche des points et remporte " + getJoueur2().getNbCartePoint() + " points.");
            } else if (getJoueur1().getValeurPoint() == getJoueur2().getValeurPoint()) {
            }
        }
    }
    
    //calculerScoreSequence() détermine qui gagne la phase des séquences en fonction de la taille de la plus grande séquence de chaque joueur.
    public void calculerScoreSequence() {
        if (getJoueur1().getTailleSequence() > getJoueur2().getTailleSequence()) {
            int pointsSequence = 0;
            if (getJoueur1().getTailleSequence() == 3 || getJoueur1().getTailleSequence() == 4){
                pointsSequence = getJoueur1().getTailleSequence();
            }
            else if ((4 < getJoueur1().getTailleSequence()) && (getJoueur1().getTailleSequence()< 8)){
                pointsSequence = getJoueur1().getTailleSequence() + 10;
            }
            System.out.println(_joueur1.getPseudo() + " gagne la manche des séquences et remporte " + pointsSequence + " points.");
            getJoueur1().setScore(getJoueur1().getScore() + pointsSequence);
        }
        else if (getJoueur2().getTailleSequence() < getJoueur1().getTailleSequence()){
            int pointsSequence = 0;
            if (getJoueur2().getTailleSequence() == 3 || getJoueur2().getTailleSequence() == 4){
                pointsSequence = getJoueur2().getTailleSequence();
            }
            else if ((4 < getJoueur2().getTailleSequence()) && (getJoueur2().getTailleSequence()< 8)){
                pointsSequence = getJoueur2().getTailleSequence() + 10;
            }
            System.out.println(_joueur2.getPseudo() + " gagne la manche des séquences et remporte " + pointsSequence + " points.");
            getJoueur2().setScore(getJoueur2().getScore() + pointsSequence);
        }
        
        //Si les tailles des séquences des 2 joueurs sont égales, on compare leurs valeurs.
        else if (getJoueur1().getTailleSequence() == getJoueur2().getTailleSequence()){
            if (getJoueur1().getValeurSequence() > getJoueur2().getValeurSequence()){
                getJoueur1().setScore(getJoueur1().getScore() + getJoueur1().getTailleSequence());
                System.out.println(_joueur1.getPseudo() + " gagne la manche des séquences.");
            } 
            else if (getJoueur1().getValeurSequence() < getJoueur2().getValeurSequence()) {
                getJoueur2().setScore(getJoueur2().getScore() + getJoueur2().getTailleSequence());
                System.out.println(_joueur2.getPseudo() + " gagne la manche des séquences.");
            } 
            else if (getJoueur1().getValeurSequence() == getJoueur2().getValeurSequence()) {
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
                    System.out.println(gagnant.getPseudo() + " remporte le pli, il commence. ");
                    gagnant = _joueur1;
                    perdant = _joueur2;
                }
            }
            else{
                gagnant.setScore(gagnant.getScore() + 1);
                System.out.println(gagnant.getPseudo() + " remporte le pli, il commence. ");
                
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

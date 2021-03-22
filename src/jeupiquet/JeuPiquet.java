/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 Fichier main. Ici, nous appelons toutes les méthodes des autres classes afin de faire fonctionner le programme.
 */
public class JeuPiquet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //On crée une instance de la classe jeu lorsque le programme se lance.
        //On demande le pseudo des deux joueurs.
        //On admet qu'un joueur choisit le pseudo "Aurélien" et l'autre choisit "Théo".
        //On tire au sort le role des 2 joueurs.
        Random random = new Random();
        int role = random.nextInt(2);
        Jeu jeu;
        if (role == 0){
            jeu = new Jeu("Aurélien", "Théo");
        }
        else{
            jeu = new Jeu("Théo", "Aurélien");
        }
        jeu.initialiserManche();
        
        //On affiche les mains des deux joueurs.
        jeu.getJoueur1().afficherMain();
        jeu.getJoueur2().afficherMain();
        
        //demande s'il y a Carte Blanche. On admet que le joueur 1 possède carte blanche. Il gagne ainsi 10 points.
        jeu.annonceCB(jeu.getJoueur1());
        
        
        
        //On crée deux listes qui contiennent l'index de la carte dans la main du joueur. Ces cartes vont être ajoutés dans la liste des cartes écartées.
        ArrayList<Integer> ecarteJ1 = new ArrayList<Integer>();
        ArrayList<Integer> ecarteJ2 = new ArrayList<Integer>();
        
        //On admet pour le moment que le joueur 1 choisit d'écarter sa 2nde, 6ème et 12ème carte. Le joueur 2 2carte sa 4ème et 9ème carte.
        ecarteJ1.add(1);
        ecarteJ1.add(5);
        ecarteJ1.add(11);
        ecarteJ2.add(3);
        ecarteJ2.add(8);
        jeu.getJoueur1().ecarterCartes(ecarteJ1, jeu.getTalon());
        
        //On supprime les cartes du talon qui ont été pioché par les joueurs.
        //On crée une nouvelle liste pour pouvoir modifier le talon de la classe Jeu.
        ArrayList talon = new ArrayList();
        talon = jeu.getTalon();
        for (int k = 0; k<ecarteJ1.size(); k++){
            talon.remove(0);     //remove 0 car les cases sont supprimées lorsque l'on remove. Donc on remove a chaque fois la nouvelle première carte du talon.
            jeu.setTalon(talon);
        }
        
        //On admet que le joueur 1 souhaite voir les cartes qu'il a laissé dans le talon.
        jeu.voirTalon();
        
        //Le deuxième joueur écarte ses cartes et pioche dans le talon.
        jeu.getJoueur2().ecarterCartes(ecarteJ2, jeu.getTalon());
        for (int k = 0; k<ecarteJ2.size(); k++){
            talon.remove(0);     //remove 0 car les cases sont supprimées lorsque l'on remove. Donc on remove a chaque fois la nouvelle première carte du talon
            jeu.setTalon(talon);
        }
    
        //On demande au joueurs le plus grand point qu'ils possèdent.
        jeu.getJoueur1().demandePoint();
        jeu.getJoueur2().demandePoint();
        //On incrémente les scores des joueurs en fonction de celui qui a le meilleur point.
        jeu.calculerScorePoint();
        
        //On demande au joueurs la plus grande séquence qu'ils possèdent.
        jeu.getJoueur1().demandeSequence();
        jeu.getJoueur2().demandeSequence();
        //On incrémente les scores des joueurs en fonction de celui qui a la meilleure séquence.
        jeu.calculerScoreSequence();
        
        jeu.getJoueur1().demandeBrelan();
        jeu.getJoueur2().demandeBrelan();
        
        jeu.getJoueur1().demandeCarre();
        jeu.getJoueur2().demandeCarre();
        
        jeu.calculerScoreBrelanCarre();
        
        jeu.levees();
        
        //On déclare le vainqueur en fonction des points finaux des 2 joueurs.
        System.out.println("Score de " + jeu.getJoueur1().getPseudo() + " : " + jeu.getJoueur1().getScore());
        System.out.println("Score de " + jeu.getJoueur2().getPseudo() + " : " + jeu.getJoueur2().getScore());
        if (jeu.getJoueur1().getScore() > jeu.getJoueur2().getScore()){
            System.out.println(jeu.getJoueur1().getPseudo() + " gagne cette partie.");
        }
        else if (jeu.getJoueur1().getScore() < jeu.getJoueur2().getScore()){
            System.out.println(jeu.getJoueur2().getPseudo() + " gagne cette partie.");
        }
        else{
            System.out.println("Egalité !! Personne ne gagne cette fois-ci.");
        }  
    }
}
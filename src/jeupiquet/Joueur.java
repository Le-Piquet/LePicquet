/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author theobaptiste
 */
public class Joueur {

    private ArrayList<Carte> main;
    private final String pseudo;
    private int score;
    private int valeurPoint = 0;
    private int nbCartePoint = 0;
    
    public Joueur(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
        main = new ArrayList(12);
    }
    
    //ecarterCartes permet au joueur d'écarter les cartes dont il n'a pas besoin et de les remplacer par autant de cartes du talon.

    /**
     *
     * @param ecarte
     * @param talon
     */
    public void ecarterCartes(ArrayList<Integer> ecarte, ArrayList<Carte> talon){
        for (int i = 0; i<ecarte.size(); i++){
            main.remove(ecarte.get(i));
            //Problème : les cartes ecartées ne sortent pas de la main
        }
        for (int j = 0; j<ecarte.size(); j++){
            main.add(talon.get(j));
        }
        
    }
        public Carte prendreCarte(int w) {
            main.remove(w);
            return main.get(w); }
     /*
    demandePoint permet de vérifier si les informations fournis par le joueur concernant son point sont correctes ou non. De plus on y determine la couleur 
    dans laquelle le point se situe et le nombre de point que vaut le point.
    */
    public void demandePoint(Joueur joueur){
        Scanner sc = new Scanner(System.in);
        System.out.println("Avez vous un point ?");
        if (sc.nextLine().compareTo("oui") == 0){
            System.out.println("Quelle est la taille de votre point ?");
            int propositionPoint = sc.nextInt();            //proposition du joueur concernant la longueur de son plus grand point
            ArrayList<Carte> cartesTrefle = new ArrayList();            //Liste des cartes de couleur trèfle dans la main du joueur
            ArrayList<Carte> cartesPique = new ArrayList();
            ArrayList<Carte> cartesCoeur = new ArrayList();
            ArrayList<Carte> cartesCarreau = new ArrayList();
            int valeurPointTrefle = 0;              //Valeur du point de couleur trèfle du joueur
            int valeurPointPique = 0;
            int valeurPointCoeur = 0;
            int valeurPointCarreau = 0;
            
            for (int i = 0; i< joueur.getMain().size(); i++){
                if (joueur.getMain().get(i).getCouleur().compareTo("trefle") == 0){
                    cartesTrefle.add(joueur.getMain().get(i));
                    valeurPointTrefle += joueur.getMain().get(i).getPointCarte();
                }
                else if (joueur.getMain().get(i).getCouleur().compareTo("pique") == 0){
                    cartesPique.add(joueur.getMain().get(i));
                    valeurPointPique += joueur.getMain().get(i).getPointCarte();
                }
                else if (joueur.getMain().get(i).getCouleur().compareTo("coeur") == 0){
                    cartesCoeur.add(joueur.getMain().get(i));
                    valeurPointCoeur += joueur.getMain().get(i).getPointCarte();
                }
                else if (joueur.getMain().get(i).getCouleur().compareTo("carreau") == 0){
                    cartesCarreau.add(joueur.getMain().get(i));
                    valeurPointCarreau += joueur.getMain().get(i).getPointCarte();
                }
            }
            
            /*
            On crée une liste pour pouvoir y entrer la taille des différents points possibles. Ensuite, en triant cette liste, nous récupérons le nombre de cartes
            que contient le plus grand point.
            */
            ArrayList<Integer> points = new ArrayList(4);    //Liste des tailles des points pour chaque couleur.
            points.add(cartesTrefle.size());
            points.add(cartesPique.size());
            points.add(cartesCoeur.size());
            points.add(cartesCarreau.size());
            Collections.sort(points);               //On trie la liste
            nbCartePoint = points.get(3);
            
            /*
            Si plusieurs couleurs de la main possède le même nombre de carte, le point du joueur est celui qui possède la plus haute valeur.
            */
            if ((points.get(3) == nbCartePoint && points.get(2) == nbCartePoint)
                    ||(points.get(3) == nbCartePoint && points.get(2) == nbCartePoint && points.get(1) == nbCartePoint)
                    ||(points.get(3) == nbCartePoint && points.get(2) == nbCartePoint && points.get(1) == nbCartePoint && points.get(1) == nbCartePoint)){
                for (int i = 0; i<4; i++){
                    if (points.get(i) == nbCartePoint) {
                        
                    }
                }
            }
            /*
            Si il y a une unique couleur possédant le plus de carte, c'est cette couleur le point du joueur. 
            */
            else {
                if (cartesTrefle.size() == nbCartePoint){
                    valeurPoint = valeurPointTrefle;
                }
                else if (cartesPique.size() == nbCartePoint){
                    valeurPoint = valeurPointPique;
                }
                else if (cartesCoeur.size() == nbCartePoint){
                    valeurPoint = valeurPointCoeur;
                }
                else if (cartesCarreau.size() == nbCartePoint){
                    valeurPoint = valeurPointCarreau;
                }
            }
 
            while (propositionPoint != nbCartePoint){
                System.out.println("La taille du point renseignée est incorrecte. Veuillez recompter puis saisir la bonne taille de votre point.");
                propositionPoint = sc.nextInt();
            }
        }
    }
    
    
    
    
    
    
    
    
    

    public ArrayList<Carte> getMain() {
        return main;
    }

    public void setMain(ArrayList<Carte> main) {
        this.main = main;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getValeurPoint() {
        return valeurPoint;
    }

    public int getNbCartePoint() {
        return nbCartePoint;
    }

    

    public String getPseudo() {
        return pseudo;
    }
    

}

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
    private int valeurPoint = 0;        //La valeur du point permettant de comparer deux points avec le même nombre de cartes
    private int nbCartePoint = 0;           //Le nombre de cartes du plus grand point du joueur
    private int tailleSequence = 0;         //Le nombre de cartes de la plus grande suite de la meme couleur 
    
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
    
    
    public Carte prendreCarte(int indexCarte) {
        Carte carteJoueur = main.get(indexCarte);
        main.remove(indexCarte);
        return carteJoueur; 
    }
    
     /*
    demandePoint permet de vérifier si les informations fournis par le joueur concernant son point sont correctes ou non. De plus on y determine la couleur 
    dans laquelle le point se situe et le nombre de point que vaut le point.
    */
    public void demandePoint(Joueur joueur){
        Scanner sc = new Scanner(System.in);
        System.out.println(joueur.getPseudo() + " avez vous un point ?");
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
                ArrayList<Integer> triValeurPoints = new ArrayList(4);
                if (cartesTrefle.size() == nbCartePoint) {
                    triValeurPoints.add(valeurPointTrefle);
                }
                if (cartesPique.size() == nbCartePoint) {
                    triValeurPoints.add(valeurPointPique);
                }
                if (cartesCoeur.size() == nbCartePoint) {
                    triValeurPoints.add(valeurPointCoeur);
                }
                if (cartesCarreau.size() == nbCartePoint) {
                    triValeurPoints.add(valeurPointCarreau);
                }
                Collections.sort(triValeurPoints);  
                valeurPoint = triValeurPoints.get(triValeurPoints.size()-1);
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
    
    public void demandeSequence(Joueur joueur){
        Scanner sc = new Scanner(System.in);
        System.out.println(joueur.getPseudo() + ", avez vous une séquence ?");
        if (sc.nextLine().compareTo("oui") == 0){
            System.out.println("Quelle est la taille de votre séquence ?");
            int propositionSequence = sc.nextInt();            //proposition du joueur concernant la longueur de son plus grand point
            ArrayList<Carte> cartesTrefle = new ArrayList();            //Liste des cartes de couleur trèfle dans la main du joueur
            ArrayList<Carte> cartesPique = new ArrayList();
            ArrayList<Carte> cartesCoeur = new ArrayList();
            ArrayList<Carte> cartesCarreau = new ArrayList();
            ArrayList<Integer> valeursCTrefle = new ArrayList();            //Liste des valeurs des cartes de couleur trèfle dans la main du joueur
            ArrayList<Integer> valeursCPique = new ArrayList();
            ArrayList<Integer> valeursCCoeur = new ArrayList();
            ArrayList<Integer> valeursCCarreau = new ArrayList();
            
            //On trie les cartes de la main du joueur par couleurs. 
            for (int i = 0; i< joueur.getMain().size(); i++){
                if (joueur.getMain().get(i).getCouleur().compareTo("trefle") == 0){
                    cartesTrefle.add(joueur.getMain().get(i));
                    valeursCTrefle.add(joueur.getMain().get(i).getPointCarte());
                }
                else if (joueur.getMain().get(i).getCouleur().compareTo("pique") == 0){
                    cartesPique.add(joueur.getMain().get(i));
                    valeursCPique.add(joueur.getMain().get(i).getPointCarte());
                }
                else if (joueur.getMain().get(i).getCouleur().compareTo("coeur") == 0){
                    cartesCoeur.add(joueur.getMain().get(i));
                    valeursCCoeur.add(joueur.getMain().get(i).getPointCarte());
                }
                else if (joueur.getMain().get(i).getCouleur().compareTo("carreau") == 0){
                    cartesCarreau.add(joueur.getMain().get(i));
                    valeursCCarreau.add(joueur.getMain().get(i).getPointCarte());
                }
            }
            
            //On trie les listes de cartes de même couleur en fonction de la valeur des cartes. Cela nous permet ensuite de déterminer si des cartes se suivent.
            Collections.sort(valeursCTrefle); 
            Collections.sort(valeursCPique);  
            Collections.sort(valeursCCoeur);  
            Collections.sort(valeursCCarreau);  
            
            int tailleSequenceTrefle = 0;                 //Taille de la plus grande séquence de cartes de couleur trefle.  
            int tailleSequencePique = 0;
            int tailleSequenceCoeur = 0;
            int tailleSequenceCarreau = 0;
            /*
            Pour toute les cartes d'une même couleur, si 2 cartes se suivent au niveau des points de carte, la variable tailleTemporaire s'incrémente de 1.
            Si la carte d'après ne continue pas la séquence, tailleTemporaire se réinitialise. Si la séquence formée est plus grande que les précédentes,
            tailleSequence de la couleur prend cette valeur.
            */
            for (int i = 0; i<cartesTrefle.size(); i++){
                int tailleTemporaire = 0;
                if (valeursCTrefle.get(i+1) == valeursCTrefle.get(i) + 1){
                    tailleTemporaire += 1;
                    if(tailleTemporaire > tailleSequenceTrefle){
                        
                        tailleSequenceTrefle = tailleTemporaire;
                    }
                }
                else{
                    tailleTemporaire = 0;
                }
            }
            for (int i = 0; i<cartesPique.size(); i++){
                int tailleTemporaire = 0;
                if (valeursCPique.get(i+1) == valeursCPique.get(i) + 1){
                    tailleTemporaire += 1;
                    if(tailleTemporaire > tailleSequencePique){
                        tailleSequencePique = tailleTemporaire;
                    }
                }
                else{
                    tailleTemporaire = 0;
                }
            }
            for (int i = 0; i<cartesCoeur.size(); i++){
                int tailleTemporaire = 0;
                if (valeursCCoeur.get(i+1) == valeursCCoeur.get(i) + 1){
                    tailleTemporaire += 1;
                    if(tailleTemporaire > tailleSequenceCoeur){
                        tailleSequenceCoeur = tailleTemporaire;
                    }
                }
                else{
                    tailleTemporaire = 0;
                }
            }
            for (int i = 0; i<cartesCarreau.size(); i++){
                int tailleTemporaire = 0;
                if (valeursCCarreau.get(i+1) == valeursCCarreau.get(i) + 1){
                    tailleTemporaire += 1;
                    if(tailleTemporaire > tailleSequenceCarreau){
                        tailleSequenceCarreau = tailleTemporaire;
                    }
                }
                else{
                    tailleTemporaire = 0;
                }
            }
            
            ArrayList<Integer> tailles  = new ArrayList();          //Liste des tailles des plus grandes séquences de chaque couleur
            tailles.add(tailleSequenceTrefle);
            tailles.add(tailleSequencePique);
            tailles.add(tailleSequenceCoeur);
            tailles.add(tailleSequenceCarreau);
            Collections.sort(tailles);
            tailleSequence = tailles.get(3);
            
            
            
            /*
            Si plusieurs couleurs de la main possède le même nombre de carte, le point du joueur est celui qui possède la plus haute valeur.
            */
            if ((tailles.get(3) == tailleSequence && tailles.get(2) == tailleSequence)
                    ||(tailles.get(3) == tailleSequence && tailles.get(2) == tailleSequence && tailles.get(1) == tailleSequence)
                    ||(tailles.get(3) == tailleSequence && tailles.get(2) == tailleSequence && tailles.get(1) == tailleSequence && tailles.get(1) == tailleSequence)){
                ArrayList<Integer> triValeurSequences = new ArrayList(4);
                if (tailleSequenceTrefle == tailleSequence) {
                    for(int i = 0; i<cartesTrefle.size(); i++){
                        for(int j = 0; j<tailleSequence; j++){
                            if(cartesTrefle.get(i + 1).getPointCarte() == cartesTrefle.get(i).getPointCarte() + 1){
                                int valeurSequenceTrefle = 0;
                                valeurSequenceTrefle += cartesTrefle.get(j).getPointCarte();
                            }
                        }   
                    }
                    triValeurSequences.add(valeurPointTrefle);
                }
                if (tailleSequencePique == tailleSequence) {
                    triValeurSequences.add(valeurPointPique);
                }
                if (tailleSequenceCoeur == tailleSequence) {
                    triValeurSequences.add(valeurPointCoeur);
                }
                if (tailleSequenceCarreau == tailleSequence) {
                    triValeurSequences.add(valeurPointCarreau);
                }
                Collections.sort(triValeurSequences);  
                tailleSequence = triValeurSequences.get(triValeurSequences.size()-1);
            }
            
            /*
            Si il y a une unique couleur possédant le plus de carte, c'est cette couleur le point du joueur. 
            */
            else {
                if (cartesTrefle.size() == nbCartePoint){
                    tailleSequence = tailleSequenceTrefle;
                }
                else if (cartesPique.size() == nbCartePoint){
                    tailleSequence = tailleSequencePique;
                }
                else if (cartesCoeur.size() == nbCartePoint){
                    tailleSequence = tailleSequenceCoeur;
                }
                else if (cartesCarreau.size() == nbCartePoint){
                    tailleSequence = tailleSequenceCarreau;
                }
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

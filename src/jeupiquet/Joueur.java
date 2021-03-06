/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
Fichier dans lequel toutes les méthodes en lien avec les joueurs sont définies.
 */
public class Joueur {

    private ArrayList<Carte> main;
    private final String pseudo;
    private int score;
    private int valeurPoint = 0;        //La valeur du point permettant de comparer deux points avec le même nombre de cartes
    private int nbCartePoint = 0;           //Le nombre de cartes du plus grand point du joueur
    private int tailleSequence = 0;         //Le nombre de cartes de la plus grande suite de la meme couleur 
    private int valeurSequence = 0;         //La valeur de la séquence 
    private int valeurBrelan = 0;
    private int valeurCarre = 0;
    
    
    public Joueur(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
        main = new ArrayList(12);
    }
    
    //afficherMain() permet d'afficher les mains des deux joueurs dans la console.
    public void afficherMain(){
        System.out.println("Main de " + pseudo + " :" + "\n");
        for (int i = 0; i < main.size(); i++) {
            System.out.println(main.get(i).toString());
        }
    }
    
    //ecarterCartes() permet au joueur d'écarter les cartes dont il n'a pas besoin et de les remplacer par autant de cartes du talon.
    public void ecarterCartes(ArrayList<Integer> ecarte, ArrayList<Carte> talon){
        for (int i = 0; i<ecarte.size(); i++){
            main.remove(main.get(ecarte.get(i)-i));     //On enleve la carte à la place demandée - i car à chaque carte enlevée, la taille de la liste diminue.
        }
        for (int j = 0; j<ecarte.size(); j++){
            main.add(talon.get(j));
        }
    }
    
    //prendreCarte() permet au joueur de prendre une carte qu'il choisit dans sa main. Cette carte est ensuite supprimée de sa main.
    public Carte prendreCarte(int indexCarte) {
        Carte carteJoueur = main.get(indexCarte);
        main.remove(indexCarte);
        return carteJoueur; 
    }
    
    /*
    demandePoint() permet de vérifier si les informations fournis par le joueur concernant son point sont correctes ou non. De plus, on y détermine la valeur du point.
    */
    public void demandePoint(){
        Scanner sc = new Scanner(System.in);
        System.out.println(pseudo + " avez vous un point ?");
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
            
            //On trie les cartes par couleurs. On compte également les valeurs des points des différentes couleurs.
            for (int i = 0; i< main.size(); i++){
                if (main.get(i).getCouleur().compareTo("trefle") == 0){
                    cartesTrefle.add(main.get(i));
                    valeurPointTrefle += main.get(i).getPointCarte();
                }
                else if (main.get(i).getCouleur().compareTo("pique") == 0){
                    cartesPique.add(main.get(i));
                    valeurPointPique += main.get(i).getPointCarte();
                }
                else if (main.get(i).getCouleur().compareTo("coeur") == 0){
                    cartesCoeur.add(main.get(i));
                    valeurPointCoeur += main.get(i).getPointCarte();
                }
                else if (main.get(i).getCouleur().compareTo("carreau") == 0){
                    cartesCarreau.add(main.get(i));
                    valeurPointCarreau += main.get(i).getPointCarte();
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
            
            //Tant que le joueur n'a pas entré la bonne valeur de son point, nous lui reposons la question. La triche n'est pas envisageable.
            while (propositionPoint != nbCartePoint){
                System.out.println("La taille du point renseignée est incorrecte. Veuillez recompter puis saisir la bonne taille de votre point.");
                propositionPoint = sc.nextInt();
            }
        }
    }
    
    /*
    demandeSequence() permet de vérifier si les informations fournis par le joueur concernant sa séquence sont correctes ou non. De plus, on y détermine la valeur de la séquence.
    */
    public void demandeSequence(){
        Scanner sc = new Scanner(System.in);
        System.out.println(pseudo + ", avez vous une séquence ?");
        if (sc.nextLine().compareTo("oui") == 0){
            System.out.println("Quelle est la taille de votre séquence ?");
            int propositionSequence = sc.nextInt();            //proposition du joueur concernant la longueur de sa plus grande séquence
            ArrayList<Carte> cartesTrefle = new ArrayList();            //Liste des cartes de couleur trèfle dans la main du joueur
            ArrayList<Carte> cartesPique = new ArrayList();
            ArrayList<Carte> cartesCoeur = new ArrayList();
            ArrayList<Carte> cartesCarreau = new ArrayList();
            ArrayList<Integer> valeursCTrefle = new ArrayList();            //Liste des valeurs des cartes de couleur trèfle dans la main du joueur
            ArrayList<Integer> valeursCPique = new ArrayList();
            ArrayList<Integer> valeursCCoeur = new ArrayList();
            ArrayList<Integer> valeursCCarreau = new ArrayList();
            
            //On trie les cartes de la main du joueur par couleurs. 
            for (int i = 0; i < main.size(); i++){
                if (main.get(i).getCouleur().compareTo("trefle") == 0){
                    cartesTrefle.add(main.get(i));
                    valeursCTrefle.add(main.get(i).getPointCarte());
                }
                else if (main.get(i).getCouleur().compareTo("pique") == 0){
                    cartesPique.add(main.get(i));
                    valeursCPique.add(main.get(i).getPointCarte());
                }
                else if (main.get(i).getCouleur().compareTo("coeur") == 0){
                    cartesCoeur.add(main.get(i));
                    valeursCCoeur.add(main.get(i).getPointCarte());
                }
                else if (main.get(i).getCouleur().compareTo("carreau") == 0){
                    cartesCarreau.add(main.get(i));
                    valeursCCarreau.add(main.get(i).getPointCarte());
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
            int tailleTemporaire = 1;
            for (int i = 0; i<cartesTrefle.size() - 1; i++){
                if (valeursCTrefle.get(i+1) == valeursCTrefle.get(i) + 1){
                    tailleTemporaire += 1;
                    if(tailleTemporaire >= tailleSequenceTrefle){
                        tailleSequenceTrefle = tailleTemporaire;
                    }
                }
                else{
                    tailleTemporaire = 1;
                }
            }
            
            tailleTemporaire = 1;
            for (int i = 0; i<cartesPique.size() - 1; i++){
                if (valeursCPique.get(i+1) == valeursCPique.get(i) + 1){
                    tailleTemporaire += 1;
                    if(tailleTemporaire >= tailleSequencePique){
                        tailleSequencePique = tailleTemporaire;
                    }
                }
                else{
                    tailleTemporaire = 1;
                }
            }
            
            tailleTemporaire = 1;
            for (int i = 0; i<cartesCoeur.size() - 1; i++){
                if (valeursCCoeur.get(i+1) == valeursCCoeur.get(i) + 1){
                    tailleTemporaire += 1;
                    if(tailleTemporaire >= tailleSequenceCoeur){
                        tailleSequenceCoeur = tailleTemporaire;
                    }
                }
                else{
                    tailleTemporaire = 1;
                }
            }
            
            tailleTemporaire = 1;
            for (int i = 0; i<cartesCarreau.size() - 1; i++){
                if (valeursCCarreau.get(i+1) == valeursCCarreau.get(i) + 1){
                    tailleTemporaire += 1;
                    if(tailleTemporaire >= tailleSequenceCarreau){
                        tailleSequenceCarreau = tailleTemporaire;
                    }
                }
                else{
                    tailleTemporaire = 1;
                }
            }
            
            ArrayList<Integer> tailles  = new ArrayList();          //Liste des tailles des plus grandes séquences de chaque couleur
            tailles.add(tailleSequenceTrefle);
            tailles.add(tailleSequencePique);
            tailles.add(tailleSequenceCoeur);
            tailles.add(tailleSequenceCarreau);
            //On trie les tailles des plus grandes séquences de chaque couleur pour obtenir la plus grande séquence du joueur.
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
                    int valeurSequenceTrefle = 0;
                    //Pour chaque carte de couleur trèfle, si un nombre de cartes égal à tailleSequence se suivent, on relève leurs valeurs et on les additionnent.
                    for(int i = 0; i<cartesTrefle.size(); i++){
                        int valeurTemporaire = 0;
                        for(int j = 0; j<tailleSequence; j++){
                            if(cartesTrefle.get(i + j + 1).getPointCarte() == cartesTrefle.get(i + j).getPointCarte() + 1){
                                valeurTemporaire += cartesTrefle.get(i).getPointCarte();
                                if(valeurTemporaire > valeurSequenceTrefle){
                                    valeurSequenceTrefle = valeurTemporaire;
                                }
                            }
                            else{
                                valeurTemporaire = 0;
                            }
                        }   
                    }
                    triValeurSequences.add(valeurSequenceTrefle);
                }
                if (tailleSequencePique == tailleSequence) {
                    int valeurSequencePique = 0;
                    for(int i = 0; i<cartesPique.size(); i++){
                        int valeurTemporaire = 0;
                        for(int j = 0; j<tailleSequence; j++){
                            if(cartesPique.get(i + j + 1).getPointCarte() == cartesPique.get(i + j).getPointCarte() + 1){
                                valeurTemporaire += cartesPique.get(i).getPointCarte();
                                if(valeurTemporaire > valeurSequencePique){
                                    valeurSequencePique = valeurTemporaire;
                                }
                            }
                            else{
                                valeurTemporaire = 0;
                            }
                        }   
                    }
                    triValeurSequences.add(valeurSequencePique);
                }
                if (tailleSequenceCoeur == tailleSequence) {
                    int valeurSequenceCoeur = 0;
                    for(int i = 0; i<cartesCoeur.size(); i++){
                        int valeurTemporaire = 0;
                        for(int j = 0; j<tailleSequence; j++){
                            if(cartesCoeur.get(i + j + 1).getPointCarte() == cartesCoeur.get(i + j).getPointCarte() + 1){
                                valeurTemporaire += cartesCoeur.get(i).getPointCarte();
                                if(valeurTemporaire > valeurSequenceCoeur){
                                    valeurSequenceCoeur = valeurTemporaire;
                                }
                            }
                            else{
                                valeurTemporaire = 0;
                            }
                        }   
                    }
                    triValeurSequences.add(valeurSequenceCoeur);
                }
                if (tailleSequenceCarreau == tailleSequence) {
                    int valeurSequenceCarreau = 0;
                    for(int i = 0; i<cartesCarreau.size(); i++){
                        int valeurTemporaire = 0;
                        for(int j = 0; j<tailleSequence; j++){
                            if(cartesCarreau.get(i + j + 1).getPointCarte() == cartesCarreau.get(i + j).getPointCarte() + 1){
                                valeurTemporaire += cartesCarreau.get(i).getPointCarte();
                                if(valeurTemporaire > valeurSequenceCarreau){
                                    valeurSequenceCarreau = valeurTemporaire;
                                }
                            }
                            else{
                                valeurTemporaire = 0;
                            }
                        }   
                    }
                    triValeurSequences.add(valeurSequenceCarreau);
                }
                //On trie la liste triValeurSequences pour obtenir la séquence possédant la plus grande valeur.
                Collections.sort(triValeurSequences);  
                valeurSequence = triValeurSequences.get(triValeurSequences.size()-1);
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
            
            //Tant que le joueur n'a pas proposé le bon nombre de cartes de sa main qui se suivent et de même couleur, on lui repose la question.
            while (propositionSequence != tailleSequence){
                System.out.println("La taille de la séquence renseignée est incorrecte. Veuillez recompter puis saisir la bonne taille de votre séquence.");
                propositionSequence = sc.nextInt();
            }
        }
    }
    
    public void demandeBrelan() {
        Scanner sc = new Scanner(System.in);
        System.out.println(pseudo + ", avez vous un brelan de cartes supérieures à 9 ?");
        if (sc.nextLine().compareTo("oui") == 0) {
            System.out.println("Quelle est la valeur de la carte de votre brelan (10, Valet, Dame, Roi, As)?");
            int propositionBrelan = sc.nextInt();            //proposition du joueur concernant la longueur de son plus grand brelan

            ArrayList listeValeurCartes = new ArrayList();
            for (int i = 0; i< main.size(); i++){
                if (main.get(i).getPointCarte() > 9){
                    listeValeurCartes.add(main.get(i).getPointCarte());
                }
            }
            Collections.sort(listeValeurCartes);
            
            int valeurTemporaire = 0;
            
            for (int i = 0; i<listeValeurCartes.size() - 2; i++){
                if ((listeValeurCartes.get(i) == listeValeurCartes.get(i+1)) && (listeValeurCartes.get(i) == listeValeurCartes.get(i+2))){
                    valeurTemporaire = (int) listeValeurCartes.get(i);
                    if(valeurTemporaire >= valeurBrelan){
                        valeurBrelan = valeurTemporaire;
                    }
                }
            }
            
            //Tant que le joueur n'a pas proposé le bon nombre de cartes de sa main qui ont la même valeur, on lui repose la question.
            while (propositionBrelan != valeurBrelan){
                System.out.println("La taille du brelan renseigné est incorrecte. Veuillez recompter puis saisir la bonne taille de votre brelan.");
                propositionBrelan = sc.nextInt();
            }
        }
    }
    
    
    public void demandeCarre() {

        Scanner sc = new Scanner(System.in);
        System.out.println(pseudo + ", avez vous un carré de cartes supérieures à 9 ?");
        if (sc.nextLine().compareTo("oui") == 0) {
            System.out.println("Quelle est la valeur de la carte de votre carré (10, Valet, Dame, Roi, As)?");
            int propositionCarre = sc.nextInt();            //proposition du joueur concernant la longueur de son plus grand carré

            ArrayList listeValeurCartes = new ArrayList();
            for (int i = 0; i< main.size(); i++){
                if (main.get(i).getPointCarte() > 9){
                    listeValeurCartes.add(main.get(i).getPointCarte());
                }
            }
            Collections.sort(listeValeurCartes);
            
            int valeurTemporaire = 0;
            
            for (int i = 0; i<listeValeurCartes.size() - 3; i++){
                if ((listeValeurCartes.get(i) == listeValeurCartes.get(i+1)) 
                        && (listeValeurCartes.get(i) == listeValeurCartes.get(i+2)) 
                        && (listeValeurCartes.get(i) == listeValeurCartes.get(i+3))){
                    valeurTemporaire = (int) listeValeurCartes.get(i);
                    if(valeurTemporaire >= valeurCarre){
                        valeurCarre = valeurTemporaire;
                    }
                }
            }
            
            //Tant que le joueur n'a pas proposé le bon nombre de cartes de sa main qui ont la même valeur, on lui repose la question.
            while (propositionCarre != valeurCarre){
                System.out.println("La taille du carré renseigné est incorrecte. Veuillez recompter puis saisir la bonne taille de votre carré.");
                propositionCarre = sc.nextInt();
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
    
    public String getPseudo() {
        return pseudo;
    }

    public int getValeurPoint() {
        return valeurPoint;
    }

    public int getNbCartePoint() {
        return nbCartePoint;
    }

    public int getTailleSequence() {
        return tailleSequence;
    }

    public int getValeurSequence() {
        return valeurSequence;
    }

    public int getValeurBrelan() {
        return valeurBrelan;
    }

    public int getValeurCarre() {
        return valeurCarre;
    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.*;

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
        System.out.println("\n" + "Main de " + pseudo + " :");
        for (int i = 0; i < main.size(); i++) {
            System.out.println(i + ":" + main.get(i).toString());
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
        System.out.println("\n" + pseudo + " quelle est la taille de votre point ?");
        boolean continueInput = true;
        int propositionPoint = 0;
        do {
            try{
                propositionPoint = sc.nextInt();            //proposition du joueur concernant la longueur de son plus grand point
                continueInput = false;
            }
            catch (InputMismatchException ex) {
                System.out.println("Veuillez resaisir votre réponse en donnant un chiffre.");
                sc.nextLine();
            }
            }
            while (continueInput);
    
        
        ArrayList<Carte> cartesMemeCouleur = new ArrayList();            //Liste des cartes de même couleur dans la main du joueur
        
        int valeurPointTemporaire = 0;

        //On trie les cartes par couleurs. On compte également les valeurs des points des différentes couleurs.
        for (Couleur couleur : Couleur.values()){
            for (int i = 0; i < main.size(); i++){
                if (main.get(i).getCouleur() == couleur){
                    cartesMemeCouleur.add(main.get(i));
                }
            }
            if (cartesMemeCouleur.size() > nbCartePoint){
                nbCartePoint = cartesMemeCouleur.size();
            }
            for (int j = 0; j < cartesMemeCouleur.size(); j++){
                valeurPointTemporaire += cartesMemeCouleur.get(j).getPointCarte();
            }
            if (valeurPointTemporaire > valeurPoint){
                valeurPoint = valeurPointTemporaire;
            }
            int l = cartesMemeCouleur.size();
            for (int k = 0; k < l; k++){
                cartesMemeCouleur.remove(0);
            }
            valeurPointTemporaire = 0;
        }

        //Tant que le joueur n'a pas entré la bonne valeur de son point, nous lui reposons la question. La triche n'est pas envisageable.
        while (propositionPoint != nbCartePoint){
            System.out.println("La taille du point renseignée est incorrecte. Veuillez recompter puis saisir la bonne taille de votre point.");
            continueInput = true;
            do {
                try{
                    propositionPoint = sc.nextInt();            
                    continueInput = false;
                }
                catch (InputMismatchException ex) {
                    System.out.println("Veuillez resaisir votre réponse en donnant un chiffre.");
                    sc.nextLine();
                }
                }
                while (continueInput);
        }
    }
    
    /*
    demandeSequence() permet de vérifier si les informations fournis par le joueur concernant sa séquence sont correctes ou non. De plus, on y détermine la valeur de la séquence.
    */
    public void demandeSequence(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + pseudo + ", avez vous une séquence ? (Répondez par oui/non)");
        
        if (sc.nextLine().compareTo("oui") == 0){
            System.out.println("Quelle est la taille de votre séquence ?");
            int propositionSequence = 0;
            boolean continueInput = true;
            do {
                try{
                    propositionSequence = sc.nextInt();              //proposition du joueur concernant la longueur de sa plus grande séquence
                    continueInput = false;
                }
                catch (InputMismatchException ex) {
                    System.out.println("Veuillez resaisir votre réponse en donnant un chiffre.");
                    sc.nextLine();
                }
                }
                while (continueInput);
                      
            
            ArrayList<Carte> cartesMemeCouleur = new ArrayList();            //Liste des cartes de même couleur dans la main du joueur
            
            
            
            
            //On répartit les cartes de la main du joueur par couleurs. 
            for (Couleur couleur : Couleur.values()){
                for (int i = 0; i < main.size(); i++){
                    if (main.get(i).getCouleur() == couleur){
                        cartesMemeCouleur.add(main.get(i));
                    }
                }
                Collections.sort(cartesMemeCouleur, Carte.ComparatorNumeroCarte);            //On trie les cartes de meme couleur en fonction de leurs numéros
                int tailleTemporaire = 1;
                int valeurTemporaire = 0;
                int size = cartesMemeCouleur.size();
                
                //On détermine si des cartes se suivent par numéros dans les différentes couleurs.
                if (cartesMemeCouleur.size() > 2){
                    //on determine la première carte de la liste (de valeur la plus basse) comme étant la première carte de la séquence.
                    valeurTemporaire = cartesMemeCouleur.get(0).getNumero().ordinal();
                    
                    //numRef est une variable qui va prendre le muméro de la 2ème carte de la liste à chaque tour de boucle. Elle permet de comparer les numéros des cartes de la liste.
                    int numRef = cartesMemeCouleur.get(0).getNumero().ordinal();            
                   
                    for (int j = 0; j < size -1; j++){
                        //Si le numéro de la 2ème carte de la liste est égal à numRef + 1, alors les deux cartes se suivent.
                        if (cartesMemeCouleur.get(1).getNumero().ordinal() == numRef + 1){
                            tailleTemporaire += 1;
                            valeurTemporaire += cartesMemeCouleur.get(1).getNumero().ordinal();
                            if(tailleTemporaire > tailleSequence){
                                    tailleSequence = tailleTemporaire;
                                    valeurSequence = valeurTemporaire;
                                }
                            else if (tailleTemporaire == tailleSequence){
                                if (valeurTemporaire > valeurSequence){
                                    valeurSequence = valeurTemporaire;
                                }
                            }
                        }
                        //Sinon les 2 cartes ne se suivent pas et on réunitialise les compteurs.
                        else{
                            tailleTemporaire = 1;
                            valeurTemporaire = cartesMemeCouleur.get(1).getNumero().ordinal();
                        }
                        //Lorsque l'on a comparer un numéro de carte avec numRef, ce numéro prend la place de numRef et on supprime la carte de la liste.
                        numRef = cartesMemeCouleur.get(1).getNumero().ordinal();
                        cartesMemeCouleur.remove(1);
                    }
                }
                cartesMemeCouleur.remove(0);
                
            }
            
            //Tant que le joueur n'a pas proposé le bon nombre de cartes de sa main qui se suivent et de même couleur, on lui repose la question.
            while (propositionSequence != tailleSequence){
                System.out.println("La taille de la séquence renseignée est incorrecte. Veuillez recompter puis saisir la bonne taille de votre séquence.");
                propositionSequence = sc.nextInt();
            }
        }
        else if (sc.nextLine().compareTo("non") == 0){
        }
    }
    
    public void demandeBrelan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + pseudo + ", avez vous un brelan de cartes supérieures à 9 ? (Répondez par oui/non)");
        if (sc.nextLine().compareTo("oui") == 0) {
            System.out.println("Quelle est la valeur de la carte de votre brelan ?" + "\n" + "(Pour 10 tapez 3, pour Valet : 4, Dame : 5, Roi : 6, As : 7)");
            int propositionBrelan = 0;
            boolean continueInput = true;
            do {
                try{
                    propositionBrelan = sc.nextInt();            //proposition du joueur concernant la valeur de son plus grand brelan 
                    continueInput = false;
                }
                catch (InputMismatchException ex) {
                    System.out.println("Veuillez resaisir votre réponse en donnant un chiffre.");
                    sc.nextLine();
                }
                }
                while (continueInput);
            

            int tailleTemporaire = 0;
            int valeurTemporaire = 0;
            for (Numero numero : Numero.values()){
                for (int i = 0; i < main.size(); i++){
                    if (main.get(i).getNumero() == numero){
                        tailleTemporaire += 1;
                        valeurTemporaire = main.get(i).getNumero().ordinal();
                    }
                }
                if (valeurTemporaire > 2){
                    if (tailleTemporaire == 3){
                        if (valeurTemporaire > valeurBrelan){
                            valeurBrelan = valeurTemporaire;
                        }
                    }
                    if (tailleTemporaire == 4){
                        if (valeurTemporaire > valeurCarre){
                            valeurCarre = valeurTemporaire;
                        }
                    }
                }
                tailleTemporaire = 0;
                valeurTemporaire = 0;
            }
            
            //Tant que le joueur n'a pas proposé le bon nombre de cartes de sa main qui ont la même valeur, on lui repose la question.
            while (propositionBrelan != valeurBrelan){
                System.out.println("La taille du brelan renseigné est incorrecte. Veuillez recompter puis saisir la bonne taille de votre brelan.");
                propositionBrelan = sc.nextInt();
            }
        }
        else{
        }
    }
  
    public void demandeCarre(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + pseudo + ", avez vous un carré de cartes supérieures à 9 ? (Répondez par oui/non)");
        if (sc.nextLine().compareTo("oui") == 0) {
            System.out.println("Quelle est la valeur de la carte de votre carré ?" + "\n" + "(Pour 10 tapez 3, pour Valet : 4, Dame : 5, Roi : 6, As : 7)");
            int propositionCarre = sc.nextInt();            //proposition du joueur concernant la longueur de son plus grand carré
            
            //Tant que le joueur n'a pas proposé le bon nombre de cartes de sa main qui ont la même valeur, on lui repose la question.
            while (propositionCarre != valeurCarre){
                System.out.println("La taille du carré renseigné est incorrecte. Veuillez recompter puis saisir la bonne taille de votre carré.");
                propositionCarre = sc.nextInt();
            }
        }
        else{
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

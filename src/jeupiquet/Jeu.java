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

/**
 *
 * @author theobaptiste
 */
public class Jeu {
    private boolean typeJ1;
    private boolean typeJ2;
    private ArrayList Talon;
    ArrayList paquetmelange = new ArrayList(31);
    
    public Jeu(){
        
    }
    public void initialiserManche(){
        //On mélange le jeu de carte créé auparavant dans la classe Paquet à l'aide de la méthode shuffle.
        Paquet paquet = new Paquet();
        Collections.shuffle(paquet.getPaquet()); 
        paquetmelange = paquet.getPaquet();
        ArrayList mainJ1 = new ArrayList(11);
        ArrayList mainJ2 = new ArrayList(11);
        for (int i = 0; i<4; i++){
            for(int j = 0; j<3; j++){
                mainJ1.set((i*3)+j, paquetmelange.get(j));
                paquetmelange.remove(j);
            }
            for(int k = 0; k<3; k++){
                mainJ2.set((i*3)+k, paquetmelange.get(k));
                paquetmelange.remove(k);
            }
        }
        ArrayList talon = new ArrayList(7);
        for (int i = 0; i<8; i++){
            talon.set(i, paquetmelange.get(i));
        }
        
    }
    
    public void tirageRoles(){
        int role = RandomUtils.nextInt(2);
        if (role == 0){
        }
    }  
    
}

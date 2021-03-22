/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeupiquet;

/**
 *
 * @author abour
 */
public enum Numero {
    SEPT(7), 
    HUIT(8), 
    NEUF(9), 
    DIX(10), 
    VALET(10), 
    DAME(10), 
    ROI(10), 
    AS(11);

    private int pointsCarte;
    
    Numero(int pointsCarte){
        this.pointsCarte = pointsCarte;
    }

    public int getPointsCarte() {
        return pointsCarte;
    }
    
    
}
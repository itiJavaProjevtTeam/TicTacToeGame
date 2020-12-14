/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;


/**
 *
 * @author Laptop
 */
public class PlayerData {
    
    String name;
    String score;
    ArrayList<String> Games = new ArrayList<String>();
    void  PrintPlayer(){
        System.out.println("name="+this.name);
        System.out.println("score="+this.score);
        System.out.println("Games="+this.Games);
    
    
    }
    
}

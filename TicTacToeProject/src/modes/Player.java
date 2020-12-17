/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modes;

/**
 *
 * @author nermeen
 */
public class Player {
    String name;
    String score;
    public Player( String name,String score)
    {
         this.name = name; 
         this.score = score;
    }
     public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
     public void setScore(String score) {
        this.score = score;
    }

    public String getscore() {
        return score;
    }
    
}

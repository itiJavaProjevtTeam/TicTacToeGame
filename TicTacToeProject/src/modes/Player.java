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
   private String name;
   private String pScore;
    public Player( String name,String pScore)
    {
         this.name = name; 
         this.pScore = pScore;
    }
     public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
     public void setpScore(String pScore) {
        this.pScore = pScore;
    }

    public String getScore() {
        return pScore;
    }
    
}

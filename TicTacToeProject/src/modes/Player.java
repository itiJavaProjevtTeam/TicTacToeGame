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
   private String playerScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(String playerScore) {
        this.playerScore = playerScore;
    }
   
    public Player( String name,String pScore)
    {
         this.name = name; 
         this.playerScore = pScore;
    }
   
    
}

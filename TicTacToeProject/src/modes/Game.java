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
public class Game {
    String gameDate;
    String player1;
    String scoreP1;
    String player2;
    String scoreP2;
    String winner;
    public Game( String gameDate,String player1,String scoreP1,String player2,String scoreP2, String winner)
    {
        this.gameDate=gameDate;
        this.player1=player1;
        this.player2=player2;
        this.scoreP1=scoreP1;
        this.scoreP2=scoreP2;
        this.winner=winner;
    }
    
}

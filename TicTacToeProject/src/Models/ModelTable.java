/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


public class ModelTable {

    int GameId;
    String Player1;
    String Player2;
    String Winner;
    String p1Score;
    String p2Score;

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int GameId) {
        this.GameId = GameId;
    }

    public String getPlayer1() {
        return Player1;
    }

    public void setPlayer1(String Player1) {
        this.Player1 = Player1;
    }

    public String getPlayer2() {
        return Player2;
    }

    public void setPlayer2(String Player2) {
        this.Player2 = Player2;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String Winner) {
        this.Winner = Winner;
    }

    public String getP1Score() {
        return p1Score;
    }

    public void setP1Score(String p1Score) {
        this.p1Score = p1Score;
    }

    public String getP2Score() {
        return p2Score;
    }

    public void setP2Score(String p2Score) {
        this.p2Score = p2Score;
    }
    
  
 public ModelTable(int GameId,String player1,String p1Score,String player2,String p2Score,String Winner)
    {
        this.GameId=GameId;
        this.Player1=player1;
        this.p1Score=p1Score;
        this.Player2=player2;
        this.p2Score=p2Score;
        this.Winner=Winner;
        
        
    }

   
 
}

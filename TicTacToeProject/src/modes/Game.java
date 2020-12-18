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

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getScoreP1() {
        return scoreP1;
    }

    public void setScoreP1(String scoreP1) {
        this.scoreP1 = scoreP1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getScoreP2() {
        return scoreP2;
    }

    public void setScoreP2(String scoreP2) {
        this.scoreP2 = scoreP2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
    public Game( String gameDate, String player1, String scoreP1, String player2, String scoreP2, String winner)
    {
        this.gameDate=gameDate;
        this.player1=player1;
        this.scoreP1=scoreP1;
        this.player2=player2;
        this.scoreP2=scoreP2;
        this.winner=winner;
    }
    
}

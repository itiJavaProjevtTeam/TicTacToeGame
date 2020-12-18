/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author marwazabara
 */
public class ModelTable {

    int GameId;
    String Player1;
    String Player2;
    String Winner;

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

    public ModelTable(int GameId, String Player1, String Player2, String Winner) {
        this.GameId = GameId;
        this.Player1 = Player1;
        this.Player2 = Player2;
        this.Winner = Winner;
    }

}

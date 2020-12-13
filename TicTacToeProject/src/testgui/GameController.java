/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modes.MinMax;
import modes.Mode;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class GameController extends Mode implements Initializable {

    int comInd = -1;
    boolean is_loss = false, is_win = false, is_full = false;
    MinMax mnmx;
    String level;
    ArrayList<Integer> gameMoves = new ArrayList<>();
    @FXML
    private GridPane gridView;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn4;
    @FXML
    private Button btn3;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Label scoreLable;
    @FXML
    private RadioButton btnRecord;
    @FXML
    private TextField XTxt;
    @FXML
    private Button Recorded;
    @FXML
    private Label XLabel;
    @FXML
    private Label OLabel;
    @FXML
    private Label XScore;
    @FXML
    private Label OScore;
    @FXML
    private Label TieLabel;
    @FXML
    private Label TieScore;
//*******************************************************************************************************

    @FXML
    private void handlerecoredGamesAction(ActionEvent event) throws IOException {
        Parent scen1viewer = FXMLLoader.load(getClass().getResource("History.fxml"));
        Scene s1 = new Scene(scen1viewer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(s1);
        window.show();
    }
//*************************************************************************************************

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        level = "Easy";
        // level="Hard";
        mnmx = new MinMax();
        newGame();
    }
//*******************************************************************************************

    public void endGame() {
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
        btn5.setDisable(true);
        btn6.setDisable(true);
        btn7.setDisable(true);
        btn8.setDisable(true);
        btn9.setDisable(true);
        int comInd = -1;
        boolean is_loss = false, is_win = false, is_full = false;
        gameMoves.clear();
         for (int i = 0; i < 9; i++) {
            xo[i] = "0";
        }
    }
    //*************************************************************************************************************

    public void newGame() {
        for (int i = 0; i < 9; i++) {
            xo[i] = "0";
        }
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);
        btn5.setDisable(false);
        btn6.setDisable(false);
        btn7.setDisable(false);
        btn8.setDisable(false);
        btn9.setDisable(false);
        comInd = -1;
        is_loss = false;
        is_win = false;
        is_full = false;
    }
//***************************************************************************************************************

    @FXML
    private void playOnclick(ActionEvent event) {
        Button source = (Button) event.getSource();

        if (source.getText().equals("")) {
            source.setText(sgm);
            source.setDisable(true);
        }
        if (source.getId().equals(btn1.getId())) {
            System.out.println("1"+source.getText());
            xo[0] = source.getText();
            gameMoves.add(0);
        } else if (source.getId().equals(btn2.getId())) {
            xo[1] = source.getText();
            System.out.println("2"+source.getText());
            gameMoves.add(1);
        } else if (source.getId().equals(btn3.getId())) {
            xo[2] = source.getText();
            System.out.println("3"+source.getText());
            gameMoves.add(2);
        } else if (source.getId().equals(btn4.getId())) {
            xo[3] = source.getText();
            System.out.println("4"+source.getText());
            gameMoves.add(3);
        } else if (source.getId().equals(btn5.getId())) {
            xo[4] = source.getText();
            System.out.println("5"+source.getText());
            gameMoves.add(4);
        } else if (source.getId().equals(btn6.getId())) {
            xo[5] = source.getText();
            System.out.println("6"+source.getText());
            gameMoves.add(5);
        } else if (source.getId().equals(btn7.getId())) {
            xo[6] = source.getText();
            System.out.println("7"+source.getText());
            gameMoves.add(6);
        } else if (source.getId().equals(btn8.getId())) {
            xo[7] = source.getText();
            System.out.println("8"+source.getText());
            gameMoves.add(7);
        } else if (source.getId().equals(btn9.getId())) {
            xo[8] = source.getText();
            System.out.println("9"+source.getText());
            gameMoves.add(8);
        }
        if (isWin()) {
            is_win = true;
            endGame();
            System.out.println("Good luck Ai is win ,You loss the game ");
        }
        if (isLoss()) {
            is_loss = true;
            endGame();
            System.out.println("Congratulations,You wine the game!");
        }
        if (isFull()) {
            is_full = true;
            endGame();
            System.out.println("You and your opponent are tied ");
        }
        if (!is_full && !is_loss && !is_win) {
            if (level.equals("Easy")) {
                    System.out.println("Easy");
                comInd = generateRand();
            } else if (level.equals("Hard")) {
                        System.out.println("Hard");
                comInd = mnmx.minimax(xo);
            }
        System.out.println(comInd+"");
            xo[comInd] = oppSgm;
            if (comInd == 0) {
                btn1.setText(oppSgm);
                btn1.setDisable(true);
                gameMoves.add(0);
            }
            if (comInd == 1) {
                btn2.setText(oppSgm);
                btn2.setDisable(true);
                gameMoves.add(1);
            }
            if (comInd == 2) {
                btn3.setText(oppSgm);
                btn3.setDisable(true);
                gameMoves.add(2);
            }
            if (comInd == 3) {
                btn4.setText(oppSgm);
                btn4.setDisable(true);
                gameMoves.add(3);
            }
            if (comInd == 4) {
                btn5.setText(oppSgm);
                btn5.setDisable(true);
                gameMoves.add(4);
            }
            if (comInd == 5) {
                btn6.setText(oppSgm);
                btn6.setDisable(true);
                gameMoves.add(5);
            }
            if (comInd == 6) {
                btn7.setText(oppSgm);
                btn7.setDisable(true);
                gameMoves.add(6);
            }
            if (comInd == 7) {
                btn8.setText(oppSgm);
                btn8.setDisable(true);
                gameMoves.add(7);
            }
            if (comInd == 8) {
                btn9.setText(oppSgm);
                btn9.setDisable(true);
                gameMoves.add(8);
            }
            if (isWin()) {
                is_win = true;
                endGame();
                System.out.println("Good luck computer is win ,You loss the game ");
            }
        }
    }
    //*****************************************************************************************************

    public int generateRand() {
        Random rand = new Random(); //instance of random class
        int upperbound = 9;
        //generate random values from 0-9
        int ind = -1;
        ind = rand.nextInt(upperbound);
        while (gameMoves.contains(ind)) {
            ind = rand.nextInt(upperbound);
        }
        return ind;
    }

}

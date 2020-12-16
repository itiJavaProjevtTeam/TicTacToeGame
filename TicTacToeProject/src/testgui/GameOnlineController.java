/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modes.Mode;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class GameOnlineController extends Mode implements Initializable {
    boolean Player2_is_play;
    boolean is_record;
    @FXML
    private GridPane gridView;
    @FXML
    private Button btn11;
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
    private Button Recorded;
    @FXML
    private Button OnlinePlayers;
    @FXML
    private Label scoreLable1;
    @FXML
    private Button rec_btn;
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

    @FXML
    private void handleGamesHistoryAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("History.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
    }
    
    private void handleOnlinePlayersAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("OnlinePlayers.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Player2_is_play=false;
        is_record=false;
        btnRecord.setDisable(false);
         newGame();
    }    

    @FXML
    private void OnClickPlay(ActionEvent event) {
         Button source = (Button) event.getSource();
    if(Player2_is_play)
    {
        if (source.getText().equals("")) {
            source.setText(sgm);
            source.setDisable(true);
        }
        if (source.getId().equals(btn11.getId())) {
            System.out.println("1"+source.getText());
            xo[0] = source.getText();
          
        } else if (source.getId().equals(btn2.getId())) {
            xo[1] = source.getText();
            System.out.println("2"+source.getText());
        } else if (source.getId().equals(btn3.getId())) {
            xo[2] = source.getText();
            System.out.println("3"+source.getText());
        } else if (source.getId().equals(btn4.getId())) {
            xo[3] = source.getText();
            System.out.println("4"+source.getText());
        } else if (source.getId().equals(btn5.getId())) {
            xo[4] = source.getText();
            System.out.println("5"+source.getText());
        } else if (source.getId().equals(btn6.getId())) {
            xo[5] = source.getText();
            System.out.println("6"+source.getText());
        } else if (source.getId().equals(btn7.getId())) {
            xo[6] = source.getText();
            System.out.println("7"+source.getText());
        } else if (source.getId().equals(btn8.getId())) {
            xo[7] = source.getText();
            System.out.println("8"+source.getText());
        } else if (source.getId().equals(btn9.getId())) {
            xo[8] = source.getText();
            System.out.println("9"+source.getText());
        }
    }
 if (isWin()) {
            oppScore++;
          if(is_record)
        {
            is_record=false;
            btnRecord.setSelected(false);
        }
            OScore.setText(oppScore+"");
            endGame();
            System.out.println("Good luck Ai is win ,You loss the game "); 
        }
        if (isLoss()) {
            Score+=1;
           if(is_record)
        {
            is_record=false;
            btnRecord.setSelected(false);
            
        }
            XScore.setText(Score+"");  
            endGame();
            System.out.println("Congratulations,You wine the game!");
        }
        if (isFull()) {
            tieScore++;
                if(is_record)
        {
            is_record=false;
            btnRecord.setSelected(false);
        }
            TieScore.setText(tieScore+"");
            endGame();
            System.out.println("You and your opponent are tied ");
        }
    }
    @FXML
    private void OnClickRec(ActionEvent event) {
        is_record=true;
        btnRecord.setSelected(true);
    }
     public void endGame() {
        btn11.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
        btn5.setDisable(true);
        btn6.setDisable(true);
        btn7.setDisable(true);
        btn8.setDisable(true);
        btn9.setDisable(true);
         for (int i = 0; i < 9; i++) {
            xo[i] = "0";
        }
    }
    //*************************************************************************************************************

    public void newGame() {
        for (int i = 0; i < 9; i++) {
            xo[i] = "0";
        }
        btn11.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        btn11.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);
        btn5.setDisable(false);
        btn6.setDisable(false);
        btn7.setDisable(false);
        btn8.setDisable(false);
        btn9.setDisable(false);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
    boolean can_play;
    boolean is_record;
    String userName,oppUserName;
    int score,oppScore;
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
     FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource("OnlineHistory.fxml"));
      Loader.load();
        OnlinePlayersController onlnC = Loader.getController();
        onlnC.setUserNameScore(userName,score+"");
             Parent p =Loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
    }
    
    @FXML
    private void handleOnlinePlayersAction(ActionEvent event) throws IOException {
      FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource("OnlinePlayers.fxml"));
      Loader.load();
        OnlinePlayersController onlnC = Loader.getController();
        onlnC.setUserNameScore(userName,score+"");
             Parent p =Loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        can_play=false;
        is_record=false;
        userName="";oppUserName="";
        score=0;oppScore=0;
        btnRecord.setDisable(false);
         newGame();
    }    

    @FXML
    private void OnClickPlay(ActionEvent event) {
         Button source = (Button) event.getSource();
    if(can_play)
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
            System.out.println("Hard luck opponent wins ,You lose the game "); 
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
            System.out.println("Congratulations,You win the game!");
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
    public void getPlayer2Name(String player2Name,String player2Score)
    {
        oppScore=Integer.parseInt(player2Score);
        oppUserName=player2Name;
        OLabel.setText(player2Name);
    }
    
     void replayGame(LinkedHashMap<Integer, String> replay) {

        btn8.setDisable(true);
        btn9.setDisable(true);
        btn11.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
        btn5.setDisable(true);
        btn6.setDisable(true);
        btn7.setDisable(true);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int key : replay.keySet()) {

                    if (key == 1) {
                        Platform.runLater(() -> btn11.setText(replay.get(key)));

                    }

                    if (key == 2) {
                        Platform.runLater(() -> btn2.setText(replay.get(key)));
                    }

                    if (key == 3) {

                        Platform.runLater(() -> btn3.setText(replay.get(key)));

                    }

                    if (key == 4) {
                        Platform.runLater(() -> btn4.setText(replay.get(key)));
                    }

                    if (key == 5) {
                        Platform.runLater(() -> btn5.setText(replay.get(key)));
                    }

                    if (key == 6) {
                        Platform.runLater(() -> btn6.setText(replay.get(key)));
                    }

                    if (key == 7) {
                        Platform.runLater(() -> btn7.setText(replay.get(key)));
                    }

                    if (key == 8) {
                        Platform.runLater(() -> btn8.setText(replay.get(key)));
                    }

                    if (key == 9) {
                        Platform.runLater(() -> btn9.setText(replay.get(key)));
                    }

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameLocalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t.start();

    }
    
}

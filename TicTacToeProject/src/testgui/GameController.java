/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import FileAccess.FileDBSingle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    int comInd;
    boolean record;
    boolean is_loss,is_win, is_full;
    MinMax mnmx;
    String level;
    LinkedHashMap <Integer, String> steps;
    FileDBSingle fDBS;
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
    private Label TotalScore;
    @FXML
    private Button rcordBtnId;

    @FXML
    private void handlerecoredGamesAction(ActionEvent event) throws IOException {
    FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource("History.fxml"));
      Loader.load();
         System.out.println(fDBS.readSingleFile());
      
        HistoryController hc = Loader.getController();
        String fileData = fDBS.readSingleFile();
        
        String[] DateArray = fDBS.readSingleGameDateTime();
        hc.setSingleData(fileData, DateArray);
      
              
             Parent p =Loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
            
    }
//*************************************************************************************************

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        steps=new LinkedHashMap <Integer, String>();
        fDBS=new FileDBSingle();
        mnmx = new MinMax();
        is_loss = false; is_win = false; is_full = false;
        record=false;
        comInd = -1;
        level = "Easy";
        // level="Hard";
        
        newGame();
        OLabel.setText("PC");
        
    }
//*******************************************************************************************

    public void setUserName(String UserName){
    String s=UserName;
    
    }
    
    
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
        //gameMoves.clear();
       steps.clear();
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
           // gameMoves.add(0);
            steps.put(0,sgm);
        } else if (source.getId().equals(btn2.getId())) {
            xo[1] = source.getText();
            System.out.println("2"+source.getText());
           // gameMoves.add(1);
             steps.put(1,sgm);
        } else if (source.getId().equals(btn3.getId())) {
            xo[2] = source.getText();
            System.out.println("3"+source.getText());
           // gameMoves.add(2);
             steps.put(2,sgm);
        } else if (source.getId().equals(btn4.getId())) {
            xo[3] = source.getText();
            System.out.println("4"+source.getText());
            //gameMoves.add(3);
             steps.put(3,sgm);
        } else if (source.getId().equals(btn5.getId())) {
            xo[4] = source.getText();
            System.out.println("5"+source.getText());
           // gameMoves.add(4);
             steps.put(4,sgm);
        } else if (source.getId().equals(btn6.getId())) {
            xo[5] = source.getText();
            System.out.println("6"+source.getText());
           // gameMoves.add(5);
             steps.put(5,sgm);
        } else if (source.getId().equals(btn7.getId())) {
            xo[6] = source.getText();
            System.out.println("7"+source.getText());
           // gameMoves.add(6);
             steps.put(6,sgm);
        } else if (source.getId().equals(btn8.getId())) {
            xo[7] = source.getText();
            System.out.println("8"+source.getText());
           // gameMoves.add(7);
             steps.put(7,sgm);
        } else if (source.getId().equals(btn9.getId())) {
            xo[8] = source.getText();
            System.out.println("9"+source.getText());
           // gameMoves.add(8);
             steps.put(8,sgm);
        }
        if (isWin()) {
            oppScore++;
                if(record)
        {
            fDBS.WriteSingleGameSteps(XLabel.getText(), Score, oppScore, steps, "pc");
            record=false;
            btnRecord.setSelected(false);
        }
            OScore.setText(oppScore+"");
            is_win = true;
            endGame();
            System.out.println("Good luck Ai is win ,You loss the game ");
            
        }
        if (isLoss()) {
            Score+=1;
           if(record)
        {
            fDBS.WriteSingleGameSteps(XLabel.getText(), Score, oppScore, steps, XLabel.getText());
            record=false;
            btnRecord.setSelected(false);
            
        }
            XScore.setText(Score+"");  
            is_loss = true;
            endGame();
            System.out.println("Congratulations,You wine the game!");
        }
        if (isFull()) {
            tieScore++;
                if(record)
        {
            fDBS.WriteSingleGameSteps(XLabel.getText(), Score, oppScore, steps,"tied");
            record=false;
            btnRecord.setSelected(false);
        }
            TieScore.setText(tieScore+"");
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
               // gameMoves.add(0);
                 steps.put(0,oppSgm);
            }
            if (comInd == 1) {
                btn2.setText(oppSgm);
                btn2.setDisable(true);
                //gameMoves.add(1);
                 steps.put(1,oppSgm);
            }
            if (comInd == 2) {
                btn3.setText(oppSgm);
                btn3.setDisable(true);
               // gameMoves.add(2);
                 steps.put(2,oppSgm);
            }
            if (comInd == 3) {
                btn4.setText(oppSgm);
                btn4.setDisable(true);
               // gameMoves.add(3);
                 steps.put(3,oppSgm);
            }
            if (comInd == 4) {
                btn5.setText(oppSgm);
                btn5.setDisable(true);
               // gameMoves.add(4);
                 steps.put(4,oppSgm);
            }
            if (comInd == 5) {
                btn6.setText(oppSgm);
                btn6.setDisable(true);
               // gameMoves.add(5);
                 steps.put(5,oppSgm);
            }
            if (comInd == 6) {
                btn7.setText(oppSgm);
                btn7.setDisable(true);
               // gameMoves.add(6);
                 steps.put(6,oppSgm);
            }
            if (comInd == 7) {
                btn8.setText(oppSgm);
                btn8.setDisable(true);
               // gameMoves.add(7);
                 steps.put(7,oppSgm);
            }
            if (comInd == 8) {
                btn9.setText(oppSgm);
                btn9.setDisable(true);
               // gameMoves.add(8);
                 steps.put(8,oppSgm);
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
        while (steps.containsKey(ind)) {
            ind = rand.nextInt(upperbound);
        }
        return ind;
    }

    @FXML
    private void StartRecord(ActionEvent event) {//radio
        
        
    }

    @FXML
    private void recordGame(ActionEvent event) { //button
      record=true;  
      btnRecord.setSelected(true);
    }
  public void playerName(String pName)
  {
             XLabel.setText(pName);
  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import FileAccess.FileDBLocal;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;  
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField; 
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modes.Mode;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class GameLocalController extends Mode implements Initializable {
    //boolean is_loss = false, is_win = false, is_full = false;
    int turnFlag ;
    boolean record;
    String finalResult;
     LinkedHashMap <Integer, String> steps;
    FileDBLocal fDBL;
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
    private Button record_btn;
    @FXML
    private RadioButton redioRecord;
    @FXML
    private Button history_btn;

    @FXML
    private void handlerecoredAction(ActionEvent event) throws IOException {
         FXMLLoader Loader = new FXMLLoader();
         Loader.setLocation(getClass().getResource("LocalHistory.fxml"));
         Loader.load();
         System.out.println(fDBL.readLocalFile());
      
        LocalHistoryController hc = Loader.getController();
        String fileData = fDBL.readLocalFile();
        
        String[] DateArray = fDBL.readLocalGameDateTime();
        hc.setLocalData(fileData, DateArray);
      
              
             Parent p =Loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     resetGame();
     turnFlag = 1;
     record=false;
     steps=new LinkedHashMap <Integer, String>();
     fDBL=new FileDBLocal();
       redioRecord.setDisable(true);
    }

    @FXML
    private void playOnClick(ActionEvent event) {
        Button source = (Button) event.getSource();
        setTurn();
        if (source.getText().equals("")) {
            source.setText(getTurn());
            source.setDisable(true);
        }
        if (source.getId().equals(btn11.getId())) {
            xo[0] = source.getText();
            steps.put(0, getTurn());
        } else if (source.getId().equals(btn2.getId())) {
            xo[1] = source.getText();
             steps.put(1, getTurn());
        } else if (source.getId().equals(btn3.getId())) {
            xo[2] = source.getText();
             steps.put(2, getTurn());
        } else if (source.getId().equals(btn4.getId())) {
            xo[3] = source.getText();
             steps.put(3, getTurn());
        } else if (source.getId().equals(btn5.getId())) {
            xo[4] = source.getText();
             steps.put(4, getTurn());
        } else if (source.getId().equals(btn6.getId())) {
            xo[5] = source.getText();
             steps.put(5, getTurn());
        } else if (source.getId().equals(btn7.getId())) {
            xo[6] = source.getText();
             steps.put(6, getTurn());
        } else if (source.getId().equals(btn8.getId())) {
            xo[7] = source.getText();
             steps.put(7, getTurn());
        } else if (source.getId().equals(btn9.getId())) {
            xo[8] = source.getText();
             steps.put(8, getTurn());
        }
        if (isWin()) {
            Score++;
            if(record){
             fDBL.WriteLocalGameSteps(XLabel.getText(), Score,OLabel.getText(),oppScore, steps,OLabel.getText());
             System.out.println(fDBL.readLocalFile());
             record=false;
             redioRecord.setSelected(false);
            }
            finalResult="Player 2 is the winner - Cheers \n";
            endGame();
            System.out.println(finalResult);
        }
        if (isLoss()) {
            oppScore++;
              if(record){
              fDBL.WriteLocalGameSteps(XLabel.getText(), Score,OLabel.getText(),oppScore, steps,XLabel.getText());
              System.out.println(fDBL.readLocalFile());
               record=false;
             redioRecord.setSelected(false);
              }
            finalResult="Player 1 is the winner - Cheers \n";
             System.out.println(finalResult);
            endGame();
            
           
        }
        if (isFull()) {
            tieScore++;
              if(record){
            fDBL.WriteLocalGameSteps(XLabel.getText(), Score,OLabel.getText(),oppScore, steps,"tied");
            System.out.println(fDBL.readLocalFile());
             record=false;
             redioRecord.setSelected(false);
              }
            finalResult="Tough Draw \n";
            System.out.println(finalResult);
            endGame();
        }

    }

    public void resetGame() {
        btn11.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);
        btn5.setDisable(false);
        btn6.setDisable(false);
        btn7.setDisable(false);
        btn8.setDisable(false);
        btn9.setDisable(false);

        btn11.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        for (int i = 0; i < 9; i++) {
            xo[i] = "0";

        }
    
    }
    public void endGame() {
        for (int i = 0; i < 9; i++) {
            xo[i] = "0";
        }
            btn8.setDisable(true);
            btn9.setDisable(true);
            btn11.setDisable(true);
            btn2.setDisable(true);
            btn3.setDisable(true);
            btn4.setDisable(true);
            btn5.setDisable(true);
            btn6.setDisable(true);
            btn7.setDisable(true); 

            XScore.setText(Integer.toString(oppScore));
            OScore.setText(Integer.toString(Score));
            TieScore.setText(Integer.toString(tieScore));
            // GLB.tieScoreLabel.setText(Integer.toString(tieScore));

           

        

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                finalResult + "would you like to play again ?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //do stuff
            resetGame();
            turnFlag = 0;
        }

        if (alert.getResult() == ButtonType.NO) {
            //do stuff
            System.out.println("Exiting");
            // Scene s = new Scene(homeScene);
            // EntryPoint.myStage.setScene(s);
            Platform.exit();

        }

    }

    public void setTurn() {

        if (turnFlag == 0) {
            turnFlag = 1;
        } else {
            turnFlag = 0;
        }
    }

    public String getTurn() {
        if (turnFlag == 0) {
            return sgm;
        } else {
            return oppSgm;
        }

    }
    public void playersName(String nameP1,String nameP2)
    {
        XLabel.setText(nameP1);
        OLabel.setText(nameP2);
   
    }

    @FXML
    private void onClickRecord(ActionEvent event) {
        record=true;
        redioRecord.setSelected(true);
        
    }

}

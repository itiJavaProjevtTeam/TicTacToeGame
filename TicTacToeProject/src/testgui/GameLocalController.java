/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
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
    int turnFlag = 0;
    String finalResult;
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
    private TextField OTxt;
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

    @FXML
    private void handlerecoredAction(ActionEvent event) throws IOException {
        Parent scen1viewer = FXMLLoader.load(getClass().getResource("History.fxml"));
        Scene s1 = new Scene(scen1viewer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(s1);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     resetGame();
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
            System.out.println("7 "+source.getText());
        } else if (source.getId().equals(btn8.getId())) {
            xo[7] = source.getText();
            System.out.println("8"+source.getText());
        } else if (source.getId().equals(btn9.getId())) {
            xo[8] = source.getText();
            System.out.println("9"+source.getText());
        }
        if (isWin()) {
            Score++;
            finalResult="Player 2 is the winner - Cheers \n";
            endGame();
            System.out.println(finalResult);
        }
        if (isLoss()) {
            oppScore++;
            finalResult="Player 1 is the winner - Cheers \n";
             System.out.println(finalResult);
            endGame();
            
           
        }
        if (isFull()) {
            tieScore++;
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

}

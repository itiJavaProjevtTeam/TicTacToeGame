/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import FileAccess.FileDBLocal;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modes.Game;
import modes.Mode;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class GameLocalController extends Mode implements Initializable {

    //boolean is_loss = false, is_win = false, is_full = false;
    int turnFlag;
    boolean record;
    String finalResult;
    LinkedHashMap<Integer, String> steps;

    String readLocalFile;
    String GameDateFromTabel;
    LinkedHashMap<Integer, String> retrievedFromFile = new LinkedHashMap<Integer, String>();

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
    private Label TieScore;
    @FXML
    private ImageView record_btn;
    @FXML
    private RadioButton redioRecord;
    @FXML
    private Button history_btn;

    @FXML
    private void handlerecoredAction(ActionEvent event) throws IOException {

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("LocalHistory.fxml"));
        Loader.load();
        Parent p = Loader.getRoot();
       LocalHistoryController fd=Loader.getController();
       fd.assignplayername(XLabel.getText(),OLabel.getText());
        Stage s = (Stage) history_btn.getScene().getWindow();

        s.close();

        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        resetGame();
        turnFlag = 1;
        record = false;
        steps = new LinkedHashMap<Integer, String>();
        fDBL = new FileDBLocal();
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
            oppScore++;

            if (record) {
                fDBL.WriteLocalGameSteps(XLabel.getText(), Score, OLabel.getText(), oppScore, steps, OLabel.getText());
                System.out.println(fDBL.readLocalFile());
                record = false;
                redioRecord.setSelected(false);

            }
            finalResult = "Player 2 is the winner - Cheers \n";
            endGame(OLabel.getText());
            System.out.println(finalResult);
        }
        if (isLoss()) {
            Score++;
            if (record) {
                fDBL.WriteLocalGameSteps(XLabel.getText(), Score, OLabel.getText(), oppScore, steps, XLabel.getText());
                System.out.println(fDBL.readLocalFile());
                record = false;
                redioRecord.setSelected(false);

            }
            finalResult = "Player 1 is the winner - Cheers \n";
            System.out.println(XLabel.getText());
            endGame(XLabel.getText());

        }
        if (isFull()) {
            tieScore++;
            if (record) {
                fDBL.WriteLocalGameSteps(XLabel.getText(), Score, OLabel.getText(), oppScore, steps, "tied");
                System.out.println(fDBL.readLocalFile());
                record = false;
                redioRecord.setSelected(false);
            }
            finalResult = "Tough Draw \n";
            System.out.println(finalResult);
            endGame("tied");
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

    public void endGame(String w) {
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

        if (!(w.equals("tied"))) {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("Video.fxml"));
            Parent root = null;
            try {

                root = Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(GameLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            VideoController vc = Loader.getController();

            System.out.print("oooo");
            vc.setWinnerName(w, "GameLocal.fxml", XLabel.getText(), OLabel.getText());
            vc.assignLocalplayername(XLabel.getText(), OLabel.getText());

            Stage s = (Stage) history_btn.getScene().getWindow();

            s.close();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Winner Gift");
            stage.show();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    finalResult + "OOPS you are tied, would you like to play again ?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                //do stuff
                resetGame();
                turnFlag = 0;
                getLocalplayername(XLabel.getText(), OLabel.getText());
            }

            if (alert.getResult() == ButtonType.NO) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("Dashboard.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(GameLocalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Parent p = Loader.getRoot();

                Stage s = (Stage) history_btn.getScene().getWindow();

                s.close();

                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.showAndWait();

            }
        }

    }

    public void getLocalplayername(String PXName, String POName) {
        XLabel.setText(PXName);
        OLabel.setText(POName);
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
        record_btn.setDisable(true);
        history_btn.setDisable(true);
        redioRecord.setDisable(true);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int key : replay.keySet()) {

                    if (key == 0) {
                        Platform.runLater(() -> btn11.setText(replay.get(key)));

                    }

                    if (key == 1) {
                        Platform.runLater(() -> btn2.setText(replay.get(key)));
                    }

                    if (key == 2) {

                        Platform.runLater(() -> btn3.setText(replay.get(key)));

                    }

                    if (key == 3) {
                        Platform.runLater(() -> btn4.setText(replay.get(key)));
                    }

                    if (key == 4) {
                        Platform.runLater(() -> btn5.setText(replay.get(key)));
                    }

                    if (key == 5) {
                        Platform.runLater(() -> btn6.setText(replay.get(key)));
                    }

                    if (key == 6) {
                        Platform.runLater(() -> btn7.setText(replay.get(key)));
                    }

                    if (key == 7) {
                        Platform.runLater(() -> btn8.setText(replay.get(key)));
                    }

                    if (key == 8) {
                        Platform.runLater(() -> btn9.setText(replay.get(key)));
                    }

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameLocalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "would you like to play again ?",
                                ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.YES) {

                            resetGame();
                            history_btn.setDisable(false);
                            record_btn.setDisable(false);
                            turnFlag = 0;
                            getLocalplayername(XLabel.getText(), OLabel.getText());

                        }

                        if (alert.getResult() == ButtonType.NO) {

                            FXMLLoader Loader = new FXMLLoader();
                            Loader.setLocation(getClass().getResource("Dashboard.fxml"));
                            try {
                                Loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GameLocalController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Parent p = Loader.getRoot();

                            Stage s = (Stage) XLabel.getScene().getWindow();

                            s.close();

                            Stage stage = new Stage();
                            stage.setScene(new Scene(p));
                            stage.showAndWait();

                        }

                    }
                });

            }
        });
        t.start();

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

    public void playersName(String nameP1, String nameP2) {
        XLabel.setText(nameP1);
        OLabel.setText(nameP2);

    }

    public void getrecordedFromTable(String gameDate) {
        String FilegameDate;
        String FilePlayer1Name;
        String FilePlayer1Score;
        String FilePlayer2Name;
        String FilePlayer2Score;
        String FileWinner;
        int btn;
        String X_O;

        // retrievedFromFile = new LinkedHashMap<Integer, String>();
        GameDateFromTabel = gameDate;
        System.out.println(GameDateFromTabel);

        FileDBLocal fdbl = new FileDBLocal();
        readLocalFile = fdbl.dataLocl;
        String[] splitline = readLocalFile.split("/n");
        for (int i = 0; i < splitline.length; i++) {
            String[] items = splitline[i].split(",");

            FilegameDate = items[0];
            if (GameDateFromTabel.equals(FilegameDate)) {

                FilePlayer1Name = items[1];
                FilePlayer1Score = items[2];
                FilePlayer2Name = items[3];
                FilePlayer2Score = items[4];
                FileWinner = items[items.length - 1];

                for (int j = 5, k = 6; j < items.length - 1 && k < items.length - 1; j += 2, k += 2) {
                    btn = Integer.parseInt(items[j]);
                    X_O = items[k];
                    retrievedFromFile.put(btn, X_O);
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa" + btn + "/" + X_O);

                }

                for (int key : retrievedFromFile.keySet()) {
                    System.out.println(key + retrievedFromFile.get(key));
                }
                // Thread.sleep(2000);
                // Timer tm = new Timer();

                replayGame(retrievedFromFile);

                System.out.println("/////////////////////////////////////");

                System.out.println(items[0] + "  " + items[1] + "  " + items[2] + "  " + items[3] + "  " + items[4] + "  " + items[items.length - 1]);

            }

        }

    }

    public void playersDataFromTabel(String pXName, String pOName, String pXScore, String pOScore) {
        String pTieScore;
        XLabel.setText(pXName);
        XScore.setText(pXScore);
        OLabel.setText(pOName);
        OScore.setText(pOScore);
        if (pXScore.equalsIgnoreCase(pOScore)) {
            pTieScore = "1";
        } else {
            pTieScore = "0";
        }
        TieScore.setText(pTieScore);

    }

    

    @FXML
    private void backHandler(MouseEvent event) {
         try {
            Parent scen1viewer = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void onClickRecord(MouseEvent event) {
        record = true;
        redioRecord.setSelected(true);
    }

    @FXML
    private void StartRecord(ActionEvent event) {
    }

}

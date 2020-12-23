/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import FileAccess.FileDBLocal;
import FileAccess.FileDBSingle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modes.MinMax;
import modes.Mode;

/**
 * FXML Controller class
 *
 * @author Laptop
 */

public class GameController implements Initializable {

    int comInd;
    boolean record;
    boolean is_loss, is_win, is_full;
    MinMax mnmx;


    String level;
    LinkedHashMap<Integer, String> steps;
    FileDBSingle fDBS;

    String readSingleFile;
    String GameDateFromTabel;
    LinkedHashMap<Integer, String> retrievedFromFile = new LinkedHashMap<Integer, String>();
    @FXML
    private GridPane gridView;
    
    @FXML
    public Button btn1;
    @FXML
    public Button btn2;
    @FXML
    public Button btn4;
    @FXML
    public Button btn3;
    @FXML
    public Button btn5;
    @FXML
    public Button btn6;
    @FXML
    public Button btn7;
    @FXML
    public Button btn8;
    @FXML
    public Button btn9;


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
    private Label TieScore;
    @FXML
    private ImageView rcordBtnId;
    @FXML
    private Button easy;
    @FXML
    private Button Hard;
    @FXML
    private ImageView back;

    @FXML
    private void handlerecoredGamesAction(ActionEvent event) throws IOException {
        
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("History.fxml"));
        Loader.load();
        Parent p = Loader.getRoot();

        HistoryController Hc = Loader.getController();
        String xName = XLabel.getText();
        Hc.assignplayername(xName, "PC");

        Stage s = (Stage) Recorded.getScene().getWindow();

        s.close();

        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();

    }

//*************************************************************************************************
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        btn1.setFont(Font.loadFont("file:src/fonts/Bogart-Alt-Light-trial.ttf", 42));
        btn2.setFont(Font.loadFont("file:src/fonts/Bogart-Alt-Light-trial.ttf", 42));
        btn3.setFont(Font.loadFont("file:src/fonts/Bogart-Alt-Light-trial.ttf", 42));
        btn4.setFont(Font.loadFont("file:src/fonts/Bogart-Alt-Light-trial.ttf", 42));
        btn5.setFont(Font.loadFont("file:src/fonts/Bogart-Alt-Light-trial.ttf", 42));
        btn6.setFont(Font.loadFont("file:src/fonts/Bogart-Alt-Light-trial.ttf", 42));
        btn7.setFont(Font.loadFont("file:src/fonts/Bogart-Alt-Light-trial.ttf", 42));
        btn8.setFont(Font.loadFont("file:src/fonts/Bogart-Alt-Light-trial.ttf", 42));
        btn9.setFont(Font.loadFont("file:src/fonts/Bogart-Alt-Light-trial.ttf", 42));*/
        steps = new LinkedHashMap<Integer, String>();
        fDBS = new FileDBSingle();
        mnmx = new MinMax();
        is_loss = false;
        is_win = false;
        is_full = false;
        record = false;
        comInd = -1;
        btnRecord.setDisable(true);
        newGame();
        OLabel.setText("PC");
        Hard.setDisable(false);
        easy.setDisable(false);
        level = "Easy";

    }
//*******************************************************************************************

    public void setUserName(String UserName) {
        String s = UserName;
}

    public void endGame(String w) {
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
        btn5.setDisable(true);
        btn6.setDisable(true);
        btn7.setDisable(true);
        btn8.setDisable(true);
        btn9.setDisable(true);
        comInd = -1;

        is_loss = false;
        is_win = false;
        is_full = false;
        steps.clear();
        for (int i = 0; i < 9; i++) {
            mnmx.xo[i] = "0";

        }

        if (!(w.equals("tied"))) {

            FXMLLoader Loader = new FXMLLoader(getClass().getResource("Video.fxml"));
            Parent root = null;
            try {

                root = Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(GameLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            VideoController vc = Loader.getController();
            String xName = XLabel.getText();
            vc.setWinnerName(w, "Game.fxml", xName, "PC");
            System.out.println("////" + xName);

            Stage s = (Stage) Recorded.getScene().getWindow();

            s.close();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Winner Gift");
            stage.show();

        } else {
            String finalResult = "";

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    finalResult + "OOPS you are tied, would you like to play again ?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                newGame();
                getplayername(XLabel.getText(), OLabel.getText());
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

                Stage s = (Stage) Recorded.getScene().getWindow();

                s.close();

                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.showAndWait();

            }

        }

    }

    public void getplayername(String PXName, String POName) {
        String PX = PXName, PO = POName;
        XLabel.setText(PX);
        OLabel.setText(PO);
        System.out.println("ssssss" + XLabel.getText() + "//" + OLabel.getText());
    }

    //*************************************************************************************************************
    public void newGame() {
        for (int i = 0; i < 9; i++) {
            mnmx.xo[i] = "0";
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

    public void getrecordedFromTable(String gameDate) {
        String FilegameDate;
        String FilePlayer1Name;
        String FilePlayer1Score;
        String Pc;
        String PcScore;
        String FileWinner;
        int btn;
        String X_O;

        // retrievedFromFile = new LinkedHashMap<Integer, String>();
        GameDateFromTabel = gameDate;
        System.out.println(GameDateFromTabel);

        FileDBSingle fdbs = new FileDBSingle();
        readSingleFile = fdbs.dataSngl;
        String[] splitline = readSingleFile.split("/n");
        for (int i = 0; i < splitline.length; i++) {
            String[] items = splitline[i].split(",");

            FilegameDate = items[0];
            if (GameDateFromTabel.equals(FilegameDate)) {

                FilePlayer1Name = items[1];
                FilePlayer1Score = items[2];
                Pc = items[3];
                PcScore = items[4];
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
        // System.exit(0);

    }

    void replayGame(LinkedHashMap<Integer, String> replay) {

        btn8.setDisable(true);
        btn9.setDisable(true);
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
        btn5.setDisable(true);
        btn6.setDisable(true);
        btn7.setDisable(true);
        easy.setDisable(true);
        Hard.setDisable(true);
        rcordBtnId.setDisable(true);
        Recorded.setDisable(true);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int key : replay.keySet()) {

                    if (key == 0) {
                        Platform.runLater(() -> btn1.setText(replay.get(key)));

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

                            newGame();
                            Recorded.setDisable(false);
                            rcordBtnId.setDisable(false);
                            easy.setDisable(false);
                            Hard.setDisable(false);
                            getplayername(XLabel.getText(), OLabel.getText());

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

                            Stage s = (Stage) Recorded.getScene().getWindow();

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

    @FXML
    private void StartRecord(ActionEvent event) {//radio

    }
    
    @FXML
    private void recordGame(MouseEvent event) {
        record = true;
        btnRecord.setSelected(true);
    }

    public void playerName(String pName) {
        XLabel.setText(pName);
    }

    public void playersDataFromTabel(String pXName, String pXScore, String pOScore) {
        String pTieScore;
        XLabel.setText(pXName);
        XScore.setText(pXScore);
        OLabel.setText("pc");
        OScore.setText(pOScore);
        if (pXScore.equalsIgnoreCase(pOScore)) {
            pTieScore = "1";
        } else {
            pTieScore = "0";
        }
        TieScore.setText(pTieScore);

    }

    @FXML
    private void EasySelect(ActionEvent event) {
        Hard.setDisable(true);
        easy.setDisable(true);
        level = "Easy";

    }

    @FXML
    private void HardSelect(ActionEvent event) {
        easy.setDisable(true);
        Hard.setDisable(true);
        level = "Hard";
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
    private void playOnClick(ActionEvent event) {
        Button source = (Button) event.getSource();

        if (source.getText().equals("")) {
            source.setText(mnmx.sgm);
            source.setDisable(true);
        }
        if (source.getId().equals(btn1.getId())) {

            System.out.println("1" + source.getText());
            mnmx.xo[0] = source.getText();
            // gameMoves.add(0);
            steps.put(0, mnmx.sgm);
        } else if (source.getId().equals(btn2.getId())) {
            mnmx.xo[1] = source.getText();
            System.out.println("2" + source.getText());
            // gameMoves.add(1);
            steps.put(1, mnmx.sgm);
        } else if (source.getId().equals(btn3.getId())) {
            mnmx.xo[2] = source.getText();
            System.out.println("3" + source.getText());
            // gameMoves.add(2);
            steps.put(2, mnmx.sgm);
        } else if (source.getId().equals(btn4.getId())) {
            mnmx.xo[3] = source.getText();
            System.out.println("4" + source.getText());
            //gameMoves.add(3);
            steps.put(3, mnmx.sgm);
        } else if (source.getId().equals(btn5.getId())) {
            mnmx.xo[4] = source.getText();
            System.out.println("5" + source.getText());
            // gameMoves.add(4);
            steps.put(4, mnmx.sgm);
        } else if (source.getId().equals(btn6.getId())) {
            mnmx.xo[5] = source.getText();
            System.out.println("6" + source.getText());
            // gameMoves.add(5);
            steps.put(5, mnmx.sgm);
        } else if (source.getId().equals(btn7.getId())) {
            mnmx.xo[6] = source.getText();
            System.out.println("7" + source.getText());
            // gameMoves.add(6);
            steps.put(6, mnmx.sgm);
        } else if (source.getId().equals(btn8.getId())) {
            mnmx.xo[7] = source.getText();
            System.out.println("8" + source.getText());
            // gameMoves.add(7);
            steps.put(7, mnmx.sgm);
        } else if (source.getId().equals(btn9.getId())) {
            mnmx.xo[8] = source.getText();
            System.out.println("9" + source.getText());
            // gameMoves.add(8);
            steps.put(8, mnmx.sgm);
        }
        if (mnmx.isWin()) {
            mnmx.oppScore++;
            if (record) {
                fDBS.WriteSingleGameSteps(XLabel.getText(), mnmx.Score, mnmx.oppScore, steps, "pc");
                record = false;
                btnRecord.setSelected(false);
            }
            OScore.setText(mnmx.oppScore + "");
            is_win = true;
            endGame("pc");
            System.out.println("Good luck Ai is win ,You loss the game ");

        }
        if (mnmx.isLoss()) {
            mnmx.Score += 1;
            if (record) {
                fDBS.WriteSingleGameSteps(XLabel.getText(), mnmx.Score,mnmx.oppScore, steps, XLabel.getText());
                record = false;
                btnRecord.setSelected(false);

            }
            XScore.setText(mnmx.Score + "");

            is_loss = true;
            endGame(XLabel.getText());
            System.out.println("Congratulations,You wine the game!");
        }
        if (mnmx.isFull()) {

           mnmx. tieScore++;
            if (record) {
                fDBS.WriteSingleGameSteps(XLabel.getText(), mnmx.Score, mnmx.oppScore, steps, "tied");
                record = false;
                btnRecord.setSelected(false);
            }
            TieScore.setText(mnmx.tieScore + "");

            is_full = true;
            endGame("tied");
            System.out.println("You and your opponent are tied ");
        }
        if (!is_full && !is_loss && !is_win) {
            if (level.equals("Easy")) {
                System.out.println("Easy");
                comInd = generateRand();
            } else if (level.equals("Hard")) {
                System.out.println("Hard");

                comInd = mnmx.minimax();    
            }
        System.out.println(comInd+"");
            mnmx.xo[comInd] = mnmx.oppSgm;
            if (comInd == 0) {
                btn1.setText(mnmx.oppSgm);
                btn1.setDisable(true);

               // gameMoves.add(0);
                 steps.put(0,mnmx.oppSgm);
            }
            if (comInd == 1) {
                btn2.setText(mnmx.oppSgm);
                btn2.setDisable(true);
                //gameMoves.add(1);

                steps.put(1, mnmx.oppSgm);

            }
            if (comInd == 2) {
                btn3.setText(mnmx.oppSgm);
                btn3.setDisable(true);

               // gameMoves.add(2);
                 steps.put(2,mnmx.oppSgm);
            }
            if (comInd == 3) {
                btn4.setText(mnmx.oppSgm);
                btn4.setDisable(true);

               // gameMoves.add(3);
                 steps.put(3,mnmx.oppSgm);
            }
            if (comInd == 4) {
                btn5.setText(mnmx.oppSgm);
                btn5.setDisable(true);

               // gameMoves.add(4);
                 steps.put(4,mnmx.oppSgm);
            }
            if (comInd == 5) {
                btn6.setText(mnmx.oppSgm);
                btn6.setDisable(true);

               // gameMoves.add(5);
                 steps.put(5,mnmx.oppSgm);
            }
            if (comInd == 6) {
                btn7.setText(mnmx.oppSgm);
                btn7.setDisable(true);

               // gameMoves.add(6);
                 steps.put(6,mnmx.oppSgm);
            }
            if (comInd == 7) {
                btn8.setText(mnmx.oppSgm);
                btn8.setDisable(true);

               // gameMoves.add(7);
                 steps.put(7,mnmx.oppSgm);
            }
            if (comInd == 8) {
                btn9.setText(mnmx.oppSgm);
                btn9.setDisable(true);

                // gameMoves.add(8);
                steps.put(8, mnmx.oppSgm);
            }
            if (mnmx.isWin()) {
                mnmx.oppScore++;
                if (record) {
                    fDBS.WriteSingleGameSteps(XLabel.getText(), mnmx.Score, mnmx.oppScore, steps, "pc");
                    record = false;
                    btnRecord.setSelected(false);
                }
                OScore.setText(mnmx.oppScore + "");

                is_win = true;
                endGame("pc");
                System.out.println("Good luck Ai is win ,You loss the game ");

            }
        }
    }

    
    
    

}

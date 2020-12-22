/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modes.Mode;
import modes.Player;
import online.Client;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class GameOnlineController extends Mode implements Initializable {
    String can_play;
    boolean is_record;
    String userName,oppUserName;
    int score,oppScore;
    Client client ;
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
    private RadioButton btnRecord;
    @FXML
    private Label scoreLable1;
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
    private Button logOut_btn;
    @FXML
    private ImageView record_btn;
    @FXML
    private RadioButton redioRecord;
    @FXML
    private Button history_btn;

    private void handleGamesHistoryAction(ActionEvent event) throws IOException {
     FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource("OnlineHistory.fxml"));
      Loader.load();

             Parent p =Loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
    }
    
    private void handleOnlinePlayersAction(ActionEvent event) throws IOException {
      FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource("OnlinePlayers.fxml"));
      Loader.load();
        OnlinePlayersController onlnC = Loader.getController();
             Parent p =Loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //OnlinePlayersController.reqThread.stop();
        can_play="false";
        is_record=false;
        userName=OnlineController.username;
        score=0;oppScore=0;
        btnRecord.setDisable(false);
         newGame();
        try {

            client = Client.getClient(DashboardController.ip, 5007);


             client.sendMessage("StartGame." + OnlinePlayersController.oppUserName + "." + userName);

              readAndParseMsg();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
          
      
      
    }    

    @FXML
    
    private void OnClickPlay(ActionEvent event) {
         Button source = (Button) event.getSource();
         System.out.println("can play : "+can_play);
         System.out.println("userName : "+userName);
    if(can_play.equals("true"))
    {
        if (source.getText().equals("")) {
            source.setText(sgm);
            source.setDisable(true);
        }
        if (source.getId().equals(btn11.getId())) {
            System.out.println("1"+source.getText());
            xo[0] = source.getText();
            try {
                client.sendMessage("play."+oppUserName+".0."+sgm);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source.getId().equals(btn2.getId())) {
            xo[1] = source.getText();
            System.out.println("2"+source.getText());
             try {
                client.sendMessage("play."+oppUserName+".1."+sgm);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source.getId().equals(btn3.getId())) {
            xo[2] = source.getText();
            System.out.println("3"+source.getText());
             try {
                client.sendMessage("play."+oppUserName+".2."+sgm);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source.getId().equals(btn4.getId())) {
            xo[3] = source.getText();
            System.out.println("4"+source.getText());
             try {
                client.sendMessage("play."+oppUserName+".3."+sgm);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source.getId().equals(btn5.getId())) {
            xo[4] = source.getText();
            System.out.println("5"+source.getText());
             try {
                client.sendMessage("play."+oppUserName+".4."+sgm);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source.getId().equals(btn6.getId())) {
            xo[5] = source.getText();
            System.out.println("6"+source.getText());
             try {
                client.sendMessage("play."+oppUserName+".5."+sgm);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source.getId().equals(btn7.getId())) {
            xo[6] = source.getText();
            System.out.println("7"+source.getText());
             try {
                client.sendMessage("play."+oppUserName+".6."+sgm);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source.getId().equals(btn8.getId())) {
            xo[7] = source.getText();
            System.out.println("8"+source.getText());
             try {
                client.sendMessage("play."+oppUserName+".7."+sgm);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source.getId().equals(btn9.getId())) {
            xo[8] = source.getText();
            System.out.println("9"+source.getText());
             try {
                client.sendMessage("play."+oppUserName+".8."+sgm);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        if (isLoss()) {    //oppnent lose
            Score++;
             try {
                client.sendMessage("win."+oppUserName+"."+userName);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
           /*if(is_record)
        {
            is_record=false;
            btnRecord.setSelected(false);    
        }*/
            XScore.setText(Score+"");  
            endGame();
            System.out.println("Congratulations,You win the game!");
        }
        if (isFull()) {
            tieScore++;
             try {
                client.sendMessage("tied."+oppUserName+"."+userName);
            } catch (IOException ex) {
                Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
              /*  if(is_record)
        {
            is_record=false;
            btnRecord.setSelected(false);
        }*/
            TieScore.setText(tieScore+"");
            endGame();
            System.out.println("You and your opponent are tied ");
        }
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
    /*public void getGame(String player1Name,String player1Score,String player1sgm,String player2Name,String player2Score,String player2Sgm)
        Score=Integer.parseInt(player2Score);
        userName=player2Name;
        sgm=player2Sgm;
        oppUserName=player2Name;
        oppSgm=player2Sgm;
       // OLabel.setText(player2Name);
    }*/
//*******************************************************************************************************
    @FXML
    private void OnClickLogOut(ActionEvent event) {
        try {
            client.sendMessage("LOGOUT."+userName);
            String msg=client.readResponse();
            if(msg.equals("Player went offline succefully"))
            {
            client.closeConnection();
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("OnlineHistory.fxml"));
            Loader.load();
             Parent p =Loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
           // System.exit(0);
            }
    // do what you have to do    stage.close();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }
    //*********************************************************************************************************
     public void readAndParseMsg()
    {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   while (true)
                   {
                    System.out.println("The name : " + userName);
                      System.out.println("game thread oooooooooooo");
                   String msg= client.readResponse();
                 //  System.out.println("game thread oooooooooooo");
                   System.out.println("The message : " + msg);
                   String [] parsedMsg=msg.split("\\.");
                    System.out.println("Start oooooooooooooo ");
                     System.out.println("parse ===== "+parsedMsg[0]);
                    if(parsedMsg[0].equals("StartGame"))
                   {
                        System.out.println("Start oooooooooooooo ");
                     if(parsedMsg[1].equals(userName))    
                     {
                          System.out.println("user ooooooooooo  ");
                         oppUserName=parsedMsg[2];
                         can_play=parsedMsg[3];
                         System.out.println("asdfghj  "+parsedMsg[3]);
                         totalScore=Integer.parseInt(parsedMsg[4]);
                         sgm=parsedMsg[5];
                         oppSgm=parsedMsg[6];
                     }
                   }
                   else if(parsedMsg[0].equals("play"))
                   {
                       if(parsedMsg[1].equals(userName))
                          playStep(parsedMsg);
                          can_play="true";
                   }

                    else if(parsedMsg[0].equals("lose"))
                   {
                       if(parsedMsg[1].equals(userName)){
                           oppScore++;
                           endGame();

                       }
                       }
                      else if(parsedMsg[0].equals("tied"))
                   {
                       if(parsedMsg[1].equals(userName)){
                          endGame();
                          tieScore++;
                       }
                   }

               }} catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
       }).start();
    }

    void playStep(String []msg)
    {
         if (msg[3].equals("0")) {
                btn11.setText(oppSgm);
                btn11.setDisable(true);
                xo[0]=oppSgm;
            }
            if (msg[3].equals("1")) {
                btn2.setText(oppSgm);
                btn2.setDisable(true);
                xo[1]=oppSgm;
            }
            if (msg[3].equals("2")) {
                btn3.setText(oppSgm);
                btn3.setDisable(true);
                xo[2]=oppSgm;
            }
            if (msg[3].equals("3")) {
                btn4.setText(oppSgm);
                btn4.setDisable(true);
                xo[3]=oppSgm;
            }
            if (msg[3].equals("4")) {
                btn5.setText(oppSgm);
                btn5.setDisable(true);
                xo[4]=oppSgm;
            }
            if (msg[3].equals("5")) {
                btn6.setText(oppSgm);
                btn6.setDisable(true);
                xo[5]=oppSgm;
            }
            if (msg[3].equals("6")) {
                btn7.setText(oppSgm);
                btn7.setDisable(true);
                xo[6]=oppSgm;
            }
            if (msg[3].equals("7")) {
                btn8.setText(oppSgm);
                btn8.setDisable(true);
                xo[7]=oppSgm;
            }
            if (msg[3].equals("8")) {
                btn9.setText(oppSgm);
                btn9.setDisable(true);
                xo[8]=oppSgm;
        
    }
   
 

    
    
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

    @FXML
    private void backHandler(MouseEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("Dashboard.fxml"));
            Loader.load();
            
            
            Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
            
            s.close();
            
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GameOnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

   

    @FXML
    private void StartRecord(ActionEvent event) {
    }

    @FXML
    private void handlerecoredAction(ActionEvent event) {
    }

    @FXML
    private void OnClickRec(MouseEvent event) {
        is_record=true;
        btnRecord.setSelected(true);
    }

    
}

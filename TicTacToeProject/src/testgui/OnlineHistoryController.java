/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import Models.ModelTable;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modes.Player;
import online.Client;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class OnlineHistoryController implements Initializable {
Client client;

    @FXML
    private Label title;
    @FXML
    private TableView<ModelTable> historyTable;
    @FXML
    private TableColumn<ModelTable,Integer>game;
    @FXML
    private TableColumn<ModelTable,String> player1;
    @FXML
    private TableColumn<ModelTable,String> player2;
    @FXML
    private TableColumn <ModelTable,String>winner;
    @FXML
    private Button back;
    @FXML
    private TableColumn<ModelTable,String> p1score;
    @FXML
    private TableColumn<ModelTable,String> p2score;

    @FXML
     private void handleBackAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Game.fxml"));
    Scene s1 = new Scene(scen1viewer);
            
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
    window.setScene(s1);
    window.show();
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     loadData();
     
     
   
    } 
 
    // This method will load the data to the history table
    private void loadData() {
        try {
            
            client = Client.getClient("127.0.0.1", 5007);
            System.out.println("Connected!");
            System.out.println("Sending string to the ServerSocket");
            client.sendMessage("History");
            String message = client.readResponse();
            System.out.println("The message sent from the socket was: " + message);
            ArrayList<String> Data = new ArrayList<>();
            String [] history=message.split("\\.");  
            System.out.println("list of data is"+Data);
             for (int i = 0; i < history.length; i++) {
                Data.add(history[i]);
                System.out.println("DATA ID = " + Data.get(0));
                System.out.println("DATA p1 = " + Data.get(1));
                System.out.println("DATA p1SCORE = " + Data.get(2));
                System.out.println("DATA p2 = " + Data.get(3));
                System.out.println("DATA p2SCORE= " + Data.get(4));
                System.out.println("DATA winner= " + Data.get(5));
                }
                ObservableList<Models.ModelTable> oblist = FXCollections.observableArrayList();
                oblist.add(new ModelTable(Integer.parseInt(Data.get(0)), Data.get(1), Data.get(2), Data.get(3), Data.get(4),Data.get(5)));
                game.setCellValueFactory(new PropertyValueFactory<>("GameId"));
                player1.setCellValueFactory(new PropertyValueFactory<>("player1"));
                p1score.setCellValueFactory(new PropertyValueFactory<>("p1Score"));
                player2.setCellValueFactory(new PropertyValueFactory<>("player2"));
                p2score.setCellValueFactory(new PropertyValueFactory<>("p2Score"));
                winner.setCellValueFactory(new PropertyValueFactory<>("Winner"));
                historyTable.setItems(oblist);
        } catch (IOException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
   /*
    // recording game
    private void replay(GameController gameScene, ModelTable selectedGame) {
        
        try {
            System.out.println("in replay id = " + selectedGame.getGameId());
            System.out.println("in replay");
            client = Client.getClient("127.0.0.1", 5007);
            System.out.println("Connected!");
            //outputStream = socket.getOutputStream();
            //InputStream inputStream = socket.getInputStream();
            //DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            System.out.println("Sending string to the ServerSocket");
            // write the message we want to send
            client.sendMessage(OnlineController.p.name + "_" + selectedGame.getGameId() + ".GetMoves");
            String message = client.readResponse();
            System.out.println("The message sent from the socket was: " + message);
            client.closeConnection();
            
            List<String> moves = new ArrayList<String>();
            Collections.addAll(moves, message.split("_"));
            System.out.println(moves);

            System.out.println("moves size=" + moves.size());
            int x = 1;
            String player;
            int POS;
            int MoveNum;
            while (x < moves.size()) {
                String move = moves.get(x);
                List<String> MData = new ArrayList<String>();
                Collections.addAll(MData, move.split(","));
                System.out.println("MDATA GAME ID = " + MData.get(0));
                System.out.println("MDATA MOVENUM= " + MData.get(1));
                System.out.println("MDATA POS = " + MData.get(2));
                System.out.println("MDATA PLAYER = " + MData.get(3));

                MoveNum = Integer.valueOf(MData.get(1));
                POS = Integer.valueOf(MData.get(2));
                player = MData.get(3);

                x++;

                String symb;
                if (player.equalsIgnoreCase(selectedGame.getPlayer1())) {
                    symb = "X";
                } else {
                    symb = "O";
                }
                System.out.println(symb);
                switch (POS) {
                    case (0):
                        Platform.runLater(() -> gameScene.btn1.setText(symb));
                        
                        System.out.println(1);
                        break;
                    case (1):
                        Platform.runLater(() -> gameScene.btn2.setText(symb));
                        System.out.println(2);
                        break;
                    case (2):

                        Platform.runLater(() -> gameScene.btn3.setText(symb));
                        System.out.println(3);
                        break;
                    case (3):

                        Platform.runLater(() -> gameScene.btn4.setText(symb));
                        System.out.println(4);
                        break;
                    case (4):
                           
                        Platform.runLater(() -> gameScene.btn5.setText(symb));
                        System.out.println(5);
                        break;
                    case (5):
                        
                        Platform.runLater(() -> gameScene.btn6.setText(symb));
                        System.out.println(6);
                        break;
                    case (6):
                        Platform.runLater(() -> gameScene.btn7.setText(symb));
                        System.out.println(7);
                        break;
                    case (7):
                        Platform.runLater(() -> gameScene.btn8.setText(symb));
                        System.out.println(8);
                        break;
                    case (8):
                        Platform.runLater(() -> gameScene.btn9.setText(symb));
                        System.out.println(9);
                        break;
                }
                TimeUnit.SECONDS.sleep(2);

            }
            //######
            //Platform.runLater(() -> gameScene.statusLable.setText("Show ended"));

            ;
        } catch (IOException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
              client.closeConnection();
            } catch (IOException ex) {
                Logger.getLogger(OnlineHistoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }*/
}

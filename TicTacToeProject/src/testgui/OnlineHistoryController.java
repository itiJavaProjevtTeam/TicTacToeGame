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
import java.util.LinkedHashMap;
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
import modes.Game;
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
    private TableColumn<ModelTable, Integer> game;
    @FXML
    private TableColumn<ModelTable, String> player1;
    @FXML
    private TableColumn<ModelTable, String> player2;
    @FXML
    private TableColumn<ModelTable, String> winner;
    @FXML
    private Button back;
    @FXML
    private TableColumn<ModelTable, String> p1score;
    @FXML
    private TableColumn<ModelTable, String> p2score;
    
    LinkedHashMap<Integer, String> retrievedFromTable = new LinkedHashMap<Integer, String>();


    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        Parent scen1viewer = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Scene s1 = new Scene(scen1viewer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

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

            client = Client.getClient(DashboardController.ip, 5007);
            System.out.println("Connected!");
            System.out.println("Sending string to the ServerSocket");
            client.sendMessage("History");
            String message = client.readResponse();
            System.out.println("The message sent from the socket was: " + message);

            String[] history = message.split("\\_");
            String[] GID = history[0].split("\\.");
            String[] P1 = history[1].split("\\.");
            String[] P1Score = history[2].split("\\.");
            String[] P2 = history[3].split("\\.");
            String[] P2Score = history[4].split("\\.");
            String[] Winner = history[5].split("\\.");

            for (int i = 0; i < GID.length; i++) {
                System.out.println("The message sent from the socket was: " + GID[i]);
                System.out.println("The message sent from the socket was: " + P1[i]);
                System.out.println("The message sent from the socket was: " + P1Score[i]);
                System.out.println("The message sent from the socket was: " + P2[i]);
                System.out.println("The message sent from the socket was: " + P2Score[i]);
                System.out.println("The message sent from the socket was: " + Winner[i]);
            }
            //ArrayList<String> Data = new ArrayList<>();
            ObservableList<Models.ModelTable> oblist = FXCollections.observableArrayList();
            for (int i = 0; i < GID.length; i++) {
                oblist.add(new ModelTable(Integer.parseInt(GID[i]), P1[i], P1Score[i], P2[i], P2Score[i], Winner[i]));
            }
            game.setCellValueFactory(new PropertyValueFactory<>("GameId"));
            player1.setCellValueFactory(new PropertyValueFactory<>("player1"));
            p1score.setCellValueFactory(new PropertyValueFactory<>("p1Score"));
            player2.setCellValueFactory(new PropertyValueFactory<>("player2"));
            p2score.setCellValueFactory(new PropertyValueFactory<>("p2Score"));
            winner.setCellValueFactory(new PropertyValueFactory<>("Winner"));
            historyTable.setItems(oblist);
            
            historyTable.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("GameOnline.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ModelTable selectedItem = historyTable.getSelectionModel().getSelectedItem();
                GameOnlineController g = Loader.getController();
                Record(g,selectedItem.getGameId());

                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
                    

                }
            });
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // recording game
    private void Record(GameOnlineController g,int gid) {

        try {
            System.out.println("replaied game id = " + gid);
            client = Client.getClient(DashboardController.ip, 5007);
            System.out.println("Connected!");
            // write the message we want to send
            client.sendMessage("RecordedGames." + gid);
            String message = client.readResponse();
            System.out.println("The message sent from the socket was: " + message);
            //client.closeConnection();

            ArrayList<String> moves = new ArrayList<String>();
            String[] historyRecording = message.split("\\_");
            System.out.println(moves);

            String[] Symbols = historyRecording[0].split("\\.");
            String[] Position = historyRecording[1].split("\\.");
            String[] Playername = historyRecording[2].split("\\.");
            String X_O;
            int btn;
            for (int i = 0; i < Symbols.length; i++) {
                System.out.println("The message sent from the socket was: " + Symbols[i]);
                System.out.println("The message sent from the socket was: " + Position[i]);
                System.out.println("The message sent from the socket was: " + Playername[i]);
            }
            
            for (int i = 0; i < Symbols.length ; i++) {
                    btn = Integer.parseInt(Position[i]);
                    X_O = Symbols[i];
                    retrievedFromTable.put(btn, X_O);
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa" + btn + "/" + X_O);

                }
            g.replayGame(retrievedFromTable);


            

        } catch (IOException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void replay(GameController gameScene, ModelTable selectedItem) {

        try {
            System.out.println("replaied game id = " + selectedItem.getGameId());
            client = Client.getClient(DashboardController.ip, 5007);
            System.out.println("Connected!");
            // write the message we want to send
            client.sendMessage("RecordedGames." + selectedItem.getGameId());
            String message = client.readResponse();
            System.out.println("The message sent from the socket was: " + message);
            //client.closeConnection();
            /*
            String [] historyRecording=message.split("\\_");     
            String [] PlayersMoves=historyRecording[0].split("\\.");
            String [] Position=historyRecording[1].split("\\.");
            String [] Playername=historyRecording[2].split("\\.");
            
            
          for(int i = 0;i < PlayersMoves.length;i++){
            System.out.println("The message sent from the socket was: " + PlayersMoves[i]);
            System.out.println("The message sent from the socket was: " + Position[i]);
            System.out.println("The message sent from the socket was: " + Playername[i]);
          }
            
            ObservableList<Models.ModelTable> oblist = FXCollections.observableArrayList();
                for(int i = 0;i < PlayersMoves.length;i++){
                oblist.add(new ModelTable(Integer.parseInt(PlayersMoves[i]),Integer.parseInt(Position[i]), Playername[i]));
                }   */
            List<String> moves = new ArrayList<String>();
            Collections.addAll(moves, message.split("\\_"));
            System.out.println(moves);

            System.out.println("moves size=" + moves.size());
            int x = 1;
            String player;
            int POS;
            int MoveNum;
            while (x < moves.size()) {
                String move = moves.get(x);
                List<String> MData = new ArrayList<String>();
                Collections.addAll(MData, move.split("\\."));
                System.out.println("MDATA MOVENUM= " + MData.get(0));
                System.out.println("MDATA POS = " + MData.get(1));
                System.out.println("MDATA PLAYER = " + MData.get(2));

                MoveNum = Integer.valueOf(MData.get(0));
                POS = Integer.valueOf(MData.get(1));
                player = MData.get(2);

                x++;

                String symb;
                if (player.equalsIgnoreCase(selectedItem.getPlayer1())) {
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

    }
    
    private void getrecordedFromTable(ModelTable selectedItem)
    {
        
    
    }
}

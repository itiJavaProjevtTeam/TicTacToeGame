/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import FileAccess.FileDBLocal;
import FileAccess.FileDBOnline;
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
import javafx.scene.image.ImageView;
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
    String data;
    FileDBOnline fdbl;
    String PlayeXName,PlayeOName;
    @FXML
    private TableView<Game> historyTable;
    @FXML
    private TableColumn<Game, String> game;
    @FXML
    private TableColumn<Game, String> player1;
    @FXML
    private TableColumn<Game, String> player2;
    @FXML
    private TableColumn<Game, String> winner;
    @FXML
    private ImageView back;
    @FXML
    private TableColumn<Game, String> p1score;
    @FXML
    private TableColumn<Game, String> p2score;
    
    LinkedHashMap<Integer, String> retrievedFromTable = new LinkedHashMap<Integer, String>();


    private void handleBackAction(ActionEvent event) throws IOException {
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fdbl = new FileDBOnline();
        data = fdbl.dataOnline;
        String[] line = data.split("/n");
        System.out.println("hist:" + data);
       // Game game;
        ObservableList<Game> elements = FXCollections.observableArrayList();
        for (int i = 0; i < line.length; i++) {
            String[] items = line[i].split(",");
            elements.add(new Game(items[0], items[1], items[2], items[3], items[4], items[items.length - 1]));

        }
        // elements.add(new Game("1","2","3","4","5","6"));

        System.out.println("ele:" + elements);

        game.setCellValueFactory(new PropertyValueFactory<Game, String>("gameDate"));
        player1.setCellValueFactory(new PropertyValueFactory<Game, String>("player1"));
        player2.setCellValueFactory(new PropertyValueFactory<Game, String>("scoreP1"));
        p1score.setCellValueFactory(new PropertyValueFactory<Game, String>("player2"));
        p2score.setCellValueFactory(new PropertyValueFactory<Game, String>("scoreP2"));
        winner.setCellValueFactory(new PropertyValueFactory<Game, String>("winner"));
        historyTable.setItems(elements);

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


                Game selectedItem = historyTable.getSelectionModel().getSelectedItem();
                 String player1 = historyTable.getSelectionModel().getSelectedItem().getPlayer1();
                String player2 = historyTable.getSelectionModel().getSelectedItem().getPlayer2();
                String scoreP1 = historyTable.getSelectionModel().getSelectedItem().getScoreP1();
                String scoreP2 = historyTable.getSelectionModel().getSelectedItem().getScoreP2();
                String winner = historyTable.getSelectionModel().getSelectedItem().getWinner();

                GameOnlineController glc = Loader.getController();
                glc.getrecordedFromTable(selectedItem.getGameDate());
                glc.playersDataFromTabel(player1, player2,scoreP1,scoreP2);
                
              Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
          
                    s.close();
                
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();

            }
        });

    }
    public void assignplayername(String P1Name, String P2Name){

        
        PlayeXName = P1Name;

        PlayeOName = P2Name;
}
    // recording game
   

    @FXML
    private void handleBackAction(javafx.scene.input.MouseEvent event)  {
   FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("GameOnline.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
             GameOnlineController gc = Loader.getController();
                gc.getONlineplayername(PlayeXName, PlayeOName);
        
              Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
          
                    s.close();
        
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();
}
}

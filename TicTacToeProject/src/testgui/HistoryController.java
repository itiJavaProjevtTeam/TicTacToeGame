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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modes.Game;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class HistoryController implements Initializable {


    @FXML
    private ImageView Back;
    @FXML
    private TableView<Game> HistoryTableId;
    @FXML
    private TableColumn<Game, String> gameDateId;
    @FXML
    private TableColumn<Game, String> player1Id;
    @FXML
    private TableColumn<Game, String> pl1ScoreId;
    @FXML
    private TableColumn<Game, String> player2Id;
    @FXML
    private TableColumn<Game, String> pl2ScoreId;
    @FXML
    private TableColumn<Game, String> winnerId;
    
  FileDBSingle fdbs;
  String PlayeXName,PlayeOName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          fdbs = new FileDBSingle();
        String data = fdbs.dataSngl;
        String[] line = data.split("/n");
        System.out.println("hist:" + data);
        Game game;
        ObservableList<Game> elements = FXCollections.observableArrayList();
        for (int i = 0; i < line.length; i++) {
            String[] items = line[i].split(",");
            elements.add(new Game(items[0], items[1], items[2], items[3], items[4], items[items.length - 1]));

        }
        // elements.add(new Game("1","2","3","4","5","6"));

        System.out.println("ele:" + elements);

        gameDateId.setCellValueFactory(new PropertyValueFactory<Game, String>("gameDate"));
        player1Id.setCellValueFactory(new PropertyValueFactory<Game, String>("player1"));
        pl1ScoreId.setCellValueFactory(new PropertyValueFactory<Game, String>("scoreP1"));
        player2Id.setCellValueFactory(new PropertyValueFactory<Game, String>("player2"));
        pl2ScoreId.setCellValueFactory(new PropertyValueFactory<Game, String>("scoreP2"));
        winnerId.setCellValueFactory(new PropertyValueFactory<Game, String>("winner"));
        HistoryTableId.setItems(elements);
        
         HistoryTableId.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("Game.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                Game selectedItem = HistoryTableId.getSelectionModel().getSelectedItem();
                String player1 = HistoryTableId.getSelectionModel().getSelectedItem().getPlayer1();
                String player2 = HistoryTableId.getSelectionModel().getSelectedItem().getPlayer2();
                String scoreP1 = HistoryTableId.getSelectionModel().getSelectedItem().getScoreP1();
                String scoreP2 = HistoryTableId.getSelectionModel().getSelectedItem().getScoreP2();
                String winner = HistoryTableId.getSelectionModel().getSelectedItem().getWinner();

                GameController glc = Loader.getController();
                glc.getrecordedFromTable(selectedItem.getGameDate());
                glc.playersDataFromTabel(player1,scoreP1,scoreP2);
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

    @FXML
    private void handleBackAction(MouseEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("Game.fxml"));
            Loader.load();
            
            GameController gc = Loader.getController();
            gc.getplayername(PlayeXName,PlayeOName);
            Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
            
            s.close();
            
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}

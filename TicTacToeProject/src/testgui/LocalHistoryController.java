/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import FileAccess.FileDBLocal;
import Models.ModelTable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.length;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.Collation;
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
import online.Client;

/**
 * FXML Controller class
 *
 * @author nermeen
 */
public class LocalHistoryController implements Initializable {

    String data;
//String [] dates;
    FileDBLocal fdbl;
    String PlayeXName,PlayeOName;

    @FXML
    private TableView<Game> tabelId;
    @FXML
    private TableColumn<Game, String> GameDate;
    @FXML
    private TableColumn<Game, String> Player1Name;
    @FXML
    private TableColumn<Game, String> Player1Score;
    @FXML
    private TableColumn<Game, String> Player2Name;
    @FXML
    private TableColumn<Game, String> Player2Score;
    @FXML
    private TableColumn<Game, String> Winner;
    @FXML
    private ImageView BackId;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fdbl = new FileDBLocal();
        data = fdbl.dataLocl;
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

        GameDate.setCellValueFactory(new PropertyValueFactory<Game, String>("gameDate"));
        Player1Name.setCellValueFactory(new PropertyValueFactory<Game, String>("player1"));
        Player1Score.setCellValueFactory(new PropertyValueFactory<Game, String>("scoreP1"));
        Player2Name.setCellValueFactory(new PropertyValueFactory<Game, String>("player2"));
        Player2Score.setCellValueFactory(new PropertyValueFactory<Game, String>("scoreP2"));
        Winner.setCellValueFactory(new PropertyValueFactory<Game, String>("winner"));
        tabelId.setItems(elements);

        tabelId.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("GameLocal.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                Game selectedItem = tabelId.getSelectionModel().getSelectedItem();
                 String player1 = tabelId.getSelectionModel().getSelectedItem().getPlayer1();
                String player2 = tabelId.getSelectionModel().getSelectedItem().getPlayer2();
                String scoreP1 = tabelId.getSelectionModel().getSelectedItem().getScoreP1();
                String scoreP2 = tabelId.getSelectionModel().getSelectedItem().getScoreP2();
                String winner = tabelId.getSelectionModel().getSelectedItem().getWinner();

                GameLocalController glc = Loader.getController();
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
     // recording game
    


    
     public void assignplayername(String P1Name, String P2Name){

        
        PlayeXName = P1Name;

        PlayeOName = P2Name;
}

    @FXML
    private void handleBackAction(MouseEvent event) {
        
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Game.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
             GameLocalController gc = Loader.getController();
                gc.getLocalplayername(PlayeXName,PlayeOName);
        
              Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
          
                    s.close();
        
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();
    }
    
}

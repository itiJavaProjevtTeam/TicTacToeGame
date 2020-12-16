/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;


import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Collation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import modes.Game;

/**
 * FXML Controller class
 *
 * @author nermeen
 */
public class LocalHistoryController implements Initializable {
String data;
String [] dates;
    @FXML
    private Label Title;
    @FXML
    private Button Back;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    /*String[] line = data.split("\n");
        System.out.println("hist:"+data);   
    Game game ;
    ObservableList <Game> elements = null;
    for(int i = 0; i<line.length;i++){
        String[] items = line[i].split(",");
        elements=FXCollections.observableArrayList();
        elements.add(new Game(items[0],items[1],items[2],items[3],items[4],items[length-1]));
       
    }
     HistoryTableId.setItems(elements);
     gameDateId.setCellValueFactory(new PropertyValueFactory<Game,String>("gameDateId"));
     player1Id.setCellValueFactory(new PropertyValueFactory<Game,String>("player1Id"));
     pl1ScoreId.setCellValueFactory(new PropertyValueFactory<Game,String>("pl1ScoreId"));
     player2Id.setCellValueFactory(new PropertyValueFactory<Game,String>("player2Id"));
     pl2ScoreId.setCellValueFactory(new PropertyValueFactory<Game,String>("pl2ScoreId"));
     winnerId.setCellValueFactory(new PropertyValueFactory<Game,String>("winnerId"));
*/
    }    
    
    @FXML
    private void handleBackAction(ActionEvent event) {
    }
      public void setLocalData(String data,String[] dates)
    {
       this.data=data;
       this.dates=dates;
       System.out.println("hist:"+data); 
       
       
    }
}

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
  FileDBSingle fdbs;
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

                GameController glc = Loader.getController();
                glc.getrecordedFromTable(selectedItem.getGameDate());

                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();

            }
        });
        
        
    }  
    
    
      @FXML


    private void handleBackAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Game.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
    }
    
  /*  public void setSingleData(String data,String[] dates)
    {
       this.data=data;
       this.dates=dates;
       
    }*/
    
    
    
}

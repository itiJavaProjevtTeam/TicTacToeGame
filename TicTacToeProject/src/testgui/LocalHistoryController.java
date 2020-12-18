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
 * @author nermeen
 */
public class LocalHistoryController implements Initializable {

    String data;
//String [] dates;
    FileDBLocal fdbl;

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
    private Button BackId;

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

                GameLocalController glc = Loader.getController();
                glc.getrecordedFromTable(selectedItem.getGameDate());

                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();

            }
        });
    }

    /*String[] line = data.split("\n");
        System.out.println("hist:"+data);   
    Game game ;
    ObservableList <Game> elements = null;
    for(int i = 0; i<line.length;i++){
        String[] items = line[i].split(",");
        elements=FXCollections.observableArrayList();
        elements.add(new Game(items[0],items[1],items[2],items[3],items[4],items[length-1]));
       
>>>>>>> 68c40edc420d82303e0867edd27047605492d960
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Game.fxml"));
        Loader.load();

        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();

    }
    /*  public void setLocalData(String data,String[] dates)
    {
       this.data=data;
       this.dates=dates;
       System.out.println("hist:"+data); 
       
       
    }*/
    @FXML
    private void handleBackAction(ActionEvent event) {

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("GameLocal.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();
    }
}

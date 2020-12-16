/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class HistoryController implements Initializable {
String data="";
String[] dates;
    @FXML
    private Label Title;
    @FXML
    private Button Back;
    @FXML
    private TableView<?> HistoryTableId;
    @FXML
    private TableColumn<?, ?> gameDateId;
    @FXML
    private TableColumn<?, ?> player1Id;
    @FXML
    private TableColumn<?, ?> pl1ScoreId;
    @FXML
    private TableColumn<?, ?> player2Id;
    @FXML
    private TableColumn<?, ?> pl2ScoreId;
    @FXML
    private TableColumn<?, ?> winnerId;
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
        // TODO
    }   
    
    public void setSingleData(String data,String[] dates)
    {
       this.data=data;
       this.dates=dates;
       
    }
    
    
    
}

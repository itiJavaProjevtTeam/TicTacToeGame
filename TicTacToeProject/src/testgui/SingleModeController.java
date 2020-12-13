/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.File;
import java.io.FileOutputStream;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class SingleModeController implements Initializable {
    
    @FXML
     private TextField NameText;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label Title;
    @FXML
    private Label Name;
    @FXML
    private Button backbtn;
    @FXML
    private Button nectBtn;
     
    
    
    //Action of Back button

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
      Parent scen1viewer = FXMLLoader.load(getClass().getResource("Game.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
            
    
     StringBuilder S = new StringBuilder();
     S.append(NameText.getText().toString());
     FileOutputStream fos = new FileOutputStream("SingleMode.txt");
     
     
     
    }
    
  
    // Action of next button
     @FXML
    private void handleNextAction(ActionEvent event) throws IOException {
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
    
}

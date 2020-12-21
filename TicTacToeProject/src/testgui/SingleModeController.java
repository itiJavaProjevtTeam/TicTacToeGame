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
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
            Parent scen1viewer = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene s1 = new Scene(scen1viewer);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            Stage stage = (Stage) backbtn.getScene().getWindow();
          
         stage.close();
            
            window.setScene(s1);
            window.show();
   
            
            
    
        
            
    
     
     
    }
   
 
    // Action of next button
     @FXML
    private void handleNextAction(ActionEvent event) throws IOException { 
        if(!NameText.getText().isEmpty()&& !NameText.getText().startsWith(" ")){
         FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource("Game.fxml"));
      Loader.load();
     
      
        GameController gc = Loader.getController();
        gc.playerName(NameText.getText());
        System.out.print( NameText.getText());
            //   gc.playerName(NameText.getText());
              
            
              Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
          
                    s.close();
            
             Parent p =Loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
   
        } else {
            String finalResult="";
            
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    finalResult + "Please enter your name!",
                    ButtonType.OK);
                    alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
              alert.close();
            }

            
        }
    
            
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

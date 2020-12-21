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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class DashboardController implements Initializable {

    @FXML
    private Button singlebtn;
    @FXML
    private Button localbtn;
    @FXML
    private Button onlinebtn;

   
       @FXML
    private void handlesinglemodeAction(ActionEvent event) throws IOException {
    //System.out.println("You clicked me!");
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("SingleMode.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
         Stage stage = (Stage) singlebtn.getScene().getWindow();
          
         stage.close();
            
            window.setScene(s1);
            window.show();
    }
    
     @FXML
    private void handleLocalemodeAction(ActionEvent event) throws IOException {
    //System.out.println("You clicked me!");
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Local.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            Stage stage = (Stage) localbtn.getScene().getWindow();
          
         stage.close();
            
            window.setScene(s1);
            window.show();
    }
   @FXML
    private void handleOnlinemodeAction(ActionEvent event) throws IOException {
    //System.out.println("You clicked me!");
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Online.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
             Stage stage = (Stage) onlinebtn.getScene().getWindow();
          
         stage.close();
            
            window.setScene(s1);
            window.show();
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

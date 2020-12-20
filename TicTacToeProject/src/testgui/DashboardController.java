/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class DashboardController implements Initializable {

 public static String ip="" ;
   
       @FXML
    private void handlesinglemodeAction(ActionEvent event) throws IOException {
    //System.out.println("You clicked me!");
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("SingleMode.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
    }
    
     @FXML
    private void handleLocalemodeAction(ActionEvent event) throws IOException {
    //System.out.println("You clicked me!");
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Local.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
    }
   @FXML
    private void handleOnlinemodeAction(ActionEvent event) throws IOException {
    
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("IP Address");
        dialog.setHeaderText("Please enter the network IP address:");
        Optional<String> result = dialog.showAndWait();
        TextField input = dialog.getEditor();
         ip = input.getText();
        
        if(IPvalidatation.isValidIPAddress(ip))
         {
             System.out.println("your ip is "+ ip);
             ip=IPvalidatation.getIp();
             
            Parent scen1viewer = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
         }
        else
        {
            System.out.println("invild ip");
        }
      
       
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
   /* // check ip validation
     public static boolean validIP (String myip) {
    
        if ( myip == null || myip.isEmpty() ) {
            return false;
        }

        String[] parts = myip.split( "\\." );
        if ( parts.length != 4 ) {
            return false;
        }

        for ( String s : parts ) {
            int i = Integer.parseInt( s );
            if ( (i < 0) || (i > 255) ) {
                return false;
            }
        }
        if ( myip.endsWith(".") ) {
            return false;
        }

        return true;
    
    }*/
    
}

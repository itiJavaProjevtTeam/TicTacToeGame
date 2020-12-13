/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class LoserController implements Initializable {

    @FXML
    private void handleplayAgain(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Game.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
    }
    @FXML
    private void handleExitAction(ActionEvent event) throws IOException {
        Platform.exit();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

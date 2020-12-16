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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class LocalController implements Initializable {

    @FXML
    private Label Title;
    @FXML
    private Label PlayerX;
    @FXML
    private Label PlayerO;
    @FXML
    private Button Next;
    @FXML
    private Button Back;
    @FXML
    private TextField xName;
    @FXML
    private TextField oName;

     @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
    }
    
     @FXML
    private void handleNextAction(ActionEvent event) throws IOException {
     FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource("GameLocal.fxml"));
      Loader.load();
        GameLocalController  glc = Loader.getController();
        glc.playersName(xName.getText(),oName.getText());
  
             Parent p =Loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

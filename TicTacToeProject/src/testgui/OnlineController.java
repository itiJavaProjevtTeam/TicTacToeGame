/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import online.Client;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class OnlineController implements Initializable {

    Client client;
    private TextField PlayerName;
    private TextField Password;
    static PlayerData p = new PlayerData();
    private TextField IP;
    @FXML
    private ImageView back;
    @FXML
    private Button signup;
    @FXML
    private TextField NameTxt;
    @FXML
    private TextField PasswordTxt;
    @FXML
    private Button signin;

    private void handleLoginAction(ActionEvent event) throws IOException {
        login(event);
    }

    

    protected void login(ActionEvent event) {

        String username = PlayerName.getText();
        String password = Password.getText();
        String ip = IP.getText();
        System.out.println("Connected!");

        try {
            client = Client.getClient("127.0.0.1", 5007);
            System.out.println("Sending string to the ServerSocket");

            client.sendMessage("IN." + username + "." + password);

            String message = client.readResponse();
            System.out.println("The message sent from the socket was: " + message);
            // no data sent for login
            //client.closeConnection();
            if (message.equalsIgnoreCase("NO ENTRY")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login failed");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a unique Name and a password");
                alert.showAndWait();
            } else if (message.equalsIgnoreCase("NOT FOUND")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login failed");
                alert.setHeaderText(null);
                alert.setContentText("Please remember your userName");
                alert.showAndWait();
            } else if (message.equalsIgnoreCase("NOT Valid Name")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login failed");
                alert.setHeaderText(null);
                alert.setContentText("Please remember your Name");
                alert.showAndWait();

            } else if (message.equalsIgnoreCase("NOT Valid Pass")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login failed");
                alert.setHeaderText(null);
                alert.setContentText("Please remember your Password");
                alert.showAndWait();

            } else if (!message.equalsIgnoreCase("NOT FOUND") && !message.equalsIgnoreCase("NO ENTRY")) {
                System.out.println("Login");
                Parent scen1viewer = FXMLLoader.load(getClass().getResource("GameOnline.fxml"));
                Scene s1 = new Scene(scen1viewer);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(s1);
                window.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(OnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void hanleback(MouseEvent event) {
        try {
            Parent scen1viewer = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene s1 = new Scene(scen1viewer);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SingleModeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void signuphandler(ActionEvent event) {
         try {
            Parent scen1viewer = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene s1 = new Scene(scen1viewer);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SingleModeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSignUpAction(ActionEvent event) {
    }

    @FXML
    private void signinhandler(ActionEvent event) {
    }




    




}

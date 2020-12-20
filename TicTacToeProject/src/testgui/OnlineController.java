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
import javafx.stage.Stage;
import online.Client;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class OnlineController implements Initializable {

    Client client;
    @FXML
    private Button Login;
    @FXML
    private TextField PlayerName;
    @FXML
    private TextField Password;
    static PlayerData p = new PlayerData();
    @FXML
    private TextField IP;

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        login(event);
    }

    private void handleSignUpAction(ActionEvent event) throws IOException {
        /*
        Parent scen1viewer = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene s1 = new Scene(scen1viewer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(s1);
        window.show();*/
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

        // close the stream
        /*
        
        List<String> Data = new ArrayList<String>();
        Collections.addAll(Data, message.split("_"));
        System.out.println(Data);
        p.name = Data.get(0);
        
        System.out.println("pname=" + p.name);
        
        p.score = Data.get(1);
        System.out.println("score=" + p.score);
        int x = 2;
        while (x < Data.size()) {
        String game = Data.get(x);
        List<String> GData = new ArrayList<String>();
        Collections.addAll(GData, game.split(","));
        System.out.println("GDATA ID = " + GData.get(0));
        System.out.println("GDATA ID = " + GData.get(0));
        System.out.println("GDATA p1 = " + GData.get(1));
        System.out.println("GDATA p2 = " + GData.get(2));
        System.out.println("GDATA winner = " + GData.get(3));
        p.Games.add(game);
        
        //  p.Games.add(Models.ModelTable()
        x++;
        
        //  System.out.println("gameId" + GID);
        }
        p.PrintPlayer();*/
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

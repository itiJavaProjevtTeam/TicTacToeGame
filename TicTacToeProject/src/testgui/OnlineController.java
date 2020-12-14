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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class OnlineController implements Initializable {

    @FXML
    private Button Login;
    @FXML
    private Button signUp;
    @FXML
    private TextField PlayerName;
    @FXML
    private TextField Password;
    static PlayerData p = new PlayerData();

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Game.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
    }
    
     @FXML
    private void handleSignUpAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
            login(event);
    }
    
    protected void login(ActionEvent event) {
     
           
        try {
            String username = PlayerName.getText();
            String password = Password.getText();
            System.out.println("Connected!");
            Socket socket = new Socket("localhost", 5011);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            System.out.println("Sending string to the ServerSocket");
            
            dataOutputStream.writeUTF(username + "_" + password + "_IN");  // login or sign in
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String message = dataInputStream.readUTF();
            System.out.println("The message sent from the socket was: " + message);
            dataOutputStream.flush(); // send the message
            dataOutputStream.close(); // close the stream
            socket.close();
            
            
            // no data sent for login
            if(message.equalsIgnoreCase("NO ENTRY"))
            {
             
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login failed");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a unique Name and a password");
                alert.showAndWait();
            }
            else if (!message.equalsIgnoreCase("NOT FOUND")&& !message.equalsIgnoreCase("NO ENTRY")) {
                System.out.println("Login");}
            
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
                p.PrintPlayer();
            
        } catch (IOException ex) {
            Logger.getLogger(OnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

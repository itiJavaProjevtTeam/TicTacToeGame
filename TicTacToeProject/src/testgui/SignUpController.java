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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class SignUpController implements Initializable {

    @FXML
    private Label Title;
    @FXML
    private Label Name;
    @FXML
    private Label Password;
    @FXML
    private Button Create;
    @FXML
    private Button Back;
    @FXML
    private TextField NameTxt;
    @FXML
    private TextField PasswordTxt;

     @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Online.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
    }
    
     @FXML
    private void handleSignUpAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Online.fxml"));
               Scene s1 = new Scene(scen1viewer);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
            SignUp(event);
    }
    
    
    protected void SignUp(ActionEvent event)
    {
        
        try {
                 String username =NameTxt.getText();
                 String password = PasswordTxt.getText();
                 Socket socket = new Socket("localhost",5011 );
                 OutputStream outputStream = socket.getOutputStream();
                 InputStream inputStream = socket.getInputStream();
                 DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                 dataOutputStream.writeUTF(username + "_" + password+ "_UP");   // sign up
                 DataInputStream dataInputStream = new DataInputStream(inputStream);
                 String message = dataInputStream.readUTF();
                 
                 System.out.println("The message sent from the socket was: " + message);
                 dataOutputStream.flush();    // send the message
                 dataOutputStream.close();    // close the stream
                 socket.close();
                 
                 
                 // if user doesn't enter the reuired data for sign up
                 if(message.equalsIgnoreCase(" "))
                 {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Sign up failed");
                   alert.setHeaderText(null);
                   alert.setContentText("Please enter a unique Name and a password");
                   alert.showAndWait();
                 }
                 else if(!message.equalsIgnoreCase("ALREADY EXISTS") && !message.equalsIgnoreCase(" "))
                 {
                     System.out.println("signup");
                     OnlineController.p.name = message;
         
                        System.out.println("pname="+OnlineController.p.name);

                       OnlineController.p.score="0";
                       System.out.println("score=" +OnlineController.p.score);
                       OnlineController.p.PrintPlayer();
                 }
                   
                
                else {
                //Alert no user check your entries
                
                  System.out.println("already exists in else");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("signup failed");
                alert.setHeaderText(null);
                alert.setContentText("username already exists please enter a unique name");
                alert.showAndWait();
            }
                 
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

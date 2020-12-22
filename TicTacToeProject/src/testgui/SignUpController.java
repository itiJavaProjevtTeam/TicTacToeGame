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
import java.net.ConnectException;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Label;
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
public class SignUpController implements Initializable {

    Client client;
   // DashboardController d;
    @FXML
    private Label Title;
    @FXML
    private Button Create;
    @FXML
    private TextField NameTxt;
    @FXML
    private TextField PasswordTxt;
    @FXML
    private Label Title2;
    @FXML
    private ImageView back;

    private void handleBackAction(ActionEvent event) throws IOException {
        Parent scen1viewer = FXMLLoader.load(getClass().getResource("Online.fxml"));
        Scene s1 = new Scene(scen1viewer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(s1);
        window.show();
    }

    @FXML
    private void handleSignUpAction(ActionEvent event) throws IOException {
        SignUp(event);
    }
     public void alerts() {
        Alert confirmationAlert = new Alert(Alert.AlertType.ERROR);
        confirmationAlert.setTitle("Error");
        confirmationAlert.setHeaderText("Connection Error");
        confirmationAlert.setContentText("Please check your connectoin first");
        ButtonType buttonTypeAccept = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        
    }

    protected void SignUp(ActionEvent event) {

        try {
            String username = NameTxt.getText();
            String password = PasswordTxt.getText();
            System.out.println("your ip is "+ DashboardController.ip);
            client = Client.getClient(DashboardController.ip, 5007);
            client.sendMessage( "UP."+username + "." + password );   // sign up
            System.out.println("i am here2");
            try {
                String messgChecksUserEntryCases = client.readResponse();
                //client.closeConnection();
                System.out.println("The message sent from the socket was: " + messgChecksUserEntryCases);
                if (messgChecksUserEntryCases.equalsIgnoreCase("NO ENTRY")) {
                    System.out.println(messgChecksUserEntryCases);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("signup failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a unique username and a password");
                    alert.showAndWait();
                }
                else if (!messgChecksUserEntryCases.equalsIgnoreCase("ALREADY EXISTS") && !messgChecksUserEntryCases.equalsIgnoreCase("NO ENTRY")) {
                    System.out.println(messgChecksUserEntryCases);
                    System.out.println("signup");
                    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Online.fxml"));
                    Scene s1 = new Scene(scen1viewer);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    System.out.println("i am here");
                    window.setScene(s1);
                    window.show();
                    OnlineController.p.name = messgChecksUserEntryCases;

                    System.out.println("pname=" + OnlineController.p.name);

                    OnlineController.p.score = "0";
                    System.out.println("score=" + OnlineController.p.score);
                    OnlineController.p.PrintPlayer();
                } else {
                    System.out.println(messgChecksUserEntryCases);
                    System.out.println("User already exists");
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
        catch (ConnectException e) {
            Object ex = null;
            alerts();
            
            Logger.getLogger(OnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void hanleback(MouseEvent event) {
    }

}

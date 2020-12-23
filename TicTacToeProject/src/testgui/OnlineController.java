/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import online.Client;
import static testgui.DashboardController.ip;

/**
 * FXML Controller class
 *
 * @author Elashree
 */
public class OnlineController implements Initializable {

    OnlinePlayersController onlinePC;
    Client client;
    static PlayerData p = new PlayerData();

    public static String username;

    @FXML
    private TextField PlayerName;
    @FXML
    private TextField Password;
    @FXML
    private Button signin;

    @FXML
    private ImageView back;
    @FXML
    private Button signup;

    /**
     * Initializes the controller class.
     */
    public OnlineController() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (client.isReading() == -1) {
                    alerts();
                }
            }
        });
    }

    public void alerts() {
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Error");
        confirmationAlert.setHeaderText("Connection Error");
        confirmationAlert.setContentText("Please check your connectoin first");
        ButtonType buttonTypeAccept = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        Optional<ButtonType> result = confirmationAlert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                login(event);
            }
        });
    }

    protected void login(ActionEvent event) {

        username = PlayerName.getText();
        String password = Password.getText();
        System.out.println("Connected!");

        try {
            System.out.println("your ip is " + DashboardController.ip);
            client = Client.getClient(DashboardController.ip, 5007);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlinePlayers.fxml"));
                Parent root = loader.load();
                Scene s1 = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(s1);
                window.show();
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
                System.out.println("GDATA 2 = " + GData.get(0));
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
        } catch (ConnectException e) {
            Object ex = null;
            alerts();

            Logger.getLogger(OnlineController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void hanleback(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            Scene s1 = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(s1);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SingleModeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void signuphandler(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            Parent root = loader.load();
            Scene s1 = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(s1);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SingleModeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

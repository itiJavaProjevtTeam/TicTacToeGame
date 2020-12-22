/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modes.Game;
import modes.Player;
import online.Client;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class OnlinePlayersController extends Thread implements Initializable {

    Client client;
    String userName = OnlineController.username;
    public static String oppUserName;
    String scoreClient;
    String OnlinePlayers;
    String[] parsedMsg;
    int Score;
    ObservableList<Player> elements;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket mySocket;
    String[] nameScoreList;
    public static Thread reqThread ;

    @FXML
    private Label Title;
    @FXML
    private Button Backbtn;
    @FXML
    private ScrollPane ScrollTable;
    @FXML
    private TableView<Player> TableP;
    @FXML
    private TableColumn<Player, String> playerName;
    @FXML
    private TableColumn<Player, String> PlayerScore;
    @FXML
    private Button refreshBtn1;

    @FXML
    private void handleOnlineAction(ActionEvent event) throws IOException {
        Parent scen1viewer = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene s1 = new Scene(scen1viewer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(s1);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // PlayerScore.setVisible(false);

            client = Client.getClient("127.0.0.1", 5007);
            client.sendMessage("PLAYERLIST." + userName);
            System.out.println("i am here2");
            OnlinePlayers = client.readResponse();
            System.out.println("The message : " + OnlinePlayers);
            System.out.println("The message sent from the socket was: " + OnlinePlayers);
            nameScoreList = OnlinePlayers.split("\\.");
            elements = FXCollections.observableArrayList();
            for (int i = 2, j = (nameScoreList.length + 2) / 2; i < (nameScoreList.length + 2) / 2 && j < nameScoreList.length; i++, j++) {
                if (nameScoreList[i].equals(userName)){
                    continue;
                }
                elements.add(new Player(nameScoreList[i], nameScoreList[j]));
            }
            playerName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            PlayerScore.setCellValueFactory(new PropertyValueFactory<Player, String>("playerScore"));
            TableP.setItems(elements);
            readAndParseMsg();
        } catch (IOException ex) {
            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setUserName(String name) {
        userName = name;
    }

    @FXML
    private void OnMousePressed(MouseEvent event) {

        Player selectedItem = TableP.getSelectionModel().getSelectedItem();
                try {
            if(selectedItem.getName()!= null || (!selectedItem.getName().isEmpty())){
            client.sendMessage("DUWTP." + selectedItem.getName() + "." + userName);
                System.out.println("message sent = ");
            // readAndParseMsg();
            }
        } catch (IOException ex) {
            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }

    void openGame(String[] game) {
        try {
            reqThread.stop();
            System.out.println(" stop thread oooooooooooooo ");
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("GameOnline.fxml"));
            Loader.load();  
            Stage s = (Stage) refreshBtn1.getScene().getWindow();
            s.close();
            
            Parent p = Loader.getRoot();
            //  Platform.exit();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
          // reqThread.stop();
           // System.out.println(" stop thread oooooooooooooo ");
        }
        
    }

    @FXML
    private void refreshOnlineAction(ActionEvent event) {
        try {
            client.sendMessage("PLAYERLIST." + userName);
            // readAndParseMsg();
        } catch (IOException ex) {
            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* public void GetOnlinePlayerList()
    {
        
        try {
            client.sendMessage ("PLAYERLIST."+userName);
            readAndParseMsg();  
        } catch (IOException ex) {
            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public void readAndParseMsg() {
       reqThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        String msg = client.readResponse();
                        System.out.println("The message : " + msg);
                        System.out.println("Players List thread oooooooooooooo ");
                        String[] parsedMsg = msg.split("\\.");
                        if (parsedMsg[0].equals("PLAYERLIST")) {
                            if (parsedMsg[1].equals(userName)) {
                                loadTable(parsedMsg);
                                
                            }
                        } else if (parsedMsg[0].equals("DUWTP")) {
                            if (parsedMsg[1].equals(userName)) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        playRequest(parsedMsg);
                                       
                                    }

                                });
                            }
                        } else if (parsedMsg[0].equals("Accept")) {
                            if (parsedMsg[1].equals(userName)) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        oppUserName=parsedMsg[2];
                                        openGame(parsedMsg);
                                    }
                                });
                            }
                        } else if (parsedMsg[0].equals("Reject")) {
                             if (parsedMsg[1].equals(userName)) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                   
                                        ShowMessage(parsedMsg[2] + " reject playing with you select other player");
                                   
                                    }
                                }
                            );     
                        }
    
                        } else if (parsedMsg[0].equals("Playing")) {
                            if (parsedMsg[1].equals(userName)) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        ShowMessage(parsedMsg[2] + " is playing in game now reguest later or select other player ");
                                    }

                                });
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
       reqThread.start();
    }

    void loadTable(String[] onLinePlayers) {
        elements.removeAll(elements);
        TableP.getItems().clear();
        for (int i = 2, j = (onLinePlayers.length + 2) / 2; i < (onLinePlayers.length + 2) / 2 && j < onLinePlayers.length; i++, j++) {
            if (onLinePlayers[i].equals(userName)){
                    continue;
                }
            elements.add(new Player(onLinePlayers[i], onLinePlayers[j]));
        }
        // TableP.getItems().addAll(elements);
        playerName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        PlayerScore.setCellValueFactory(new PropertyValueFactory<Player, String>("playerScore"));
        TableP.setItems(elements);
    }

    void playRequest(String[] reqMsg) {
        if (didConfirm(reqMsg[2])) {
            try {
                oppUserName=reqMsg[2];
                client.sendMessage("Accept." + reqMsg[2] + "." + userName);
                openGame(reqMsg);
            } catch (IOException ex) {
                Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                client.sendMessage("Reject." + reqMsg[2] + "." + userName);
            } catch (IOException ex) {
                Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean didConfirm(String oppName) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("CONFIRMATION");
        confirmationAlert.setHeaderText("CONFIRMATION");
        confirmationAlert.setContentText("Play with " + oppName + " ?");
        ButtonType buttonTypeAccept = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void ShowMessage(String msg) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Rigect Request");
        confirmationAlert.setHeaderText("CONFIRMATION");
        confirmationAlert.setContentText(msg);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = confirmationAlert.showAndWait();
    }

}

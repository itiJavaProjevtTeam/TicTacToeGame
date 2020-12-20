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
    String userName;
    String scoreClient;
    String OnlinePlayers;
    String[] parsedMsg;
    int Score;
    ObservableList<Player> elements;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket mySocket;
     String[] nameScoreList;

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
            userName = "nermeen";           
            client = Client.getClient("127.0.0.1", 5007);
            client.sendMessage ("PLAYERLIST."+userName);
            System.out.println("i am here2");
            readAndParseMsg();
            elements = FXCollections.observableArrayList();
            for (int i = 0, j = nameScoreList.length / 2; i < nameScoreList.length / 2 && j < nameScoreList.length; i++, j++) {
                elements.add(i, new Player(nameScoreList[i], nameScoreList[j]));
            }
            playerName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            PlayerScore.setCellValueFactory(new PropertyValueFactory<Player, String>("playerScore"));
            TableP.setItems(elements);
        } catch (IOException ex) {
            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    void setUserNameScore(String name,int scr) 
        {
            userName=name;
            Score=scr;
        }
    @FXML
    private void OnMousePressed(MouseEvent event) {
        Player selectedItem = TableP.getSelectionModel().getSelectedItem();
        try {
            client.sendMessage("CanPlay."+userName+"."+selectedItem.getName());
            // readAndParseMsg();
        } catch (IOException ex) {
            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void openGame(String [] game)
    {
           FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("GameOnline.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } 
                GameOnlineController gc = Loader.getController();
                gc.getGame(game[1],game[2],game[3],game[4],game[5],game[6],false);
                Parent p = Loader.getRoot();
                Platform.exit();
                Stage stage = new Stage();
                stage .setScene(new Scene(p));
                stage.show();
    }

    @FXML
    private void refreshOnlineAction(ActionEvent event) {
        try {
            client.sendMessage ("PLAYERLIST."+userName);
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
    public void readAndParseMsg()
    {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   while (true)
                   {
                   String msg= client.readResponse();
                   System.out.println("The message : " + msg);
                   String [] parsedMsg=msg.split("\\.");
                   if(parsedMsg[0].equals("PLAYERLIST"))
                   {
                       if(parsedMsg[1].equals(userName))
                          loadTable(parsedMsg);
                   }
                   else if(parsedMsg[0].equals("CanPlay"))
                   {
                     if(parsedMsg[1].equals(userName))
                        playRequest(parsedMsg);
                   }
                   else if(parsedMsg[0].equals("Accept"))
                   {
                       if(parsedMsg[1].equals(userName))
                          openGame(parsedMsg);
                   }
                    else if(parsedMsg[0].equals("reject"))
                   {
                       if(parsedMsg[1].equals(userName))
                         ShowMessage(parsedMsg[2]+"reject playing with you select other player");
                   }
                     else if(parsedMsg[0].equals("Playing"))
                   {
                       if(parsedMsg[1].equals(userName))
                         ShowMessage(parsedMsg[2]+"is playing in game now reguest later or select other player ");
                   }
               }} catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
       }).start();
    }
    void loadTable(String [] onLinePlayers)
    {
        elements.removeAll(elements);
                TableP.getItems().clear();
                for (int i = 2, j = (onLinePlayers.length-2) / 2; i < (onLinePlayers.length-2) / 2 && j < onLinePlayers.length; i++, j++) {
                    elements.add(i, new Player(onLinePlayers[i], onLinePlayers[j]));
                }
                TableP.getItems().addAll(elements);
     }
    void playRequest(String [] reqMsg)
    {
        if(didConfirm(reqMsg[2]))
        {
            try {   
              client.sendMessage("accept."+userName+"."+reqMsg[1]);
            } catch (IOException ex) {
                Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {  
                client.sendMessage("reject"+userName+reqMsg[1]);
            } catch (IOException ex) {
                Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean didConfirm(String oppName) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("CONFIRMATION");
        confirmationAlert.setHeaderText("CONFIRMATION");
        confirmationAlert.setContentText("Play with"+oppName+" ?");
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

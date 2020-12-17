/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modes.Game;
import modes.Player;
import online.Client;

/**
 * FXML Controller class
 *
 * @author Laptop
 */
public class OnlinePlayersController implements Initializable {
Client client;
String userName;
String score; 
String OnlinePlayers;
String [] onLinePlayersName;
    @FXML
    private Label Title;
    @FXML
    private Button Backbtn;
    @FXML
    private ScrollPane ScrollTable;
    @FXML
    private TableView<?> TableP;
    @FXML
    private TableColumn<?, ?> playerName;
    @FXML
    private TableColumn<?, ?> PlayerScore;
  
   
    @FXML
    private void handleOnlineAction(ActionEvent event) throws IOException {
    Parent scen1viewer = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
               Scene s1 = new Scene(scen1viewer);
               
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            window.setScene(s1);
            window.show();
           // getOnlinePlayers();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        userName="nermeen";

        client=Client.getClient("127.0.0.1",5007);
        client.sendMessage(userName + ".5." +"PLAYERLIST");
        System.out.println("i am here2");
        getOnlinePlayers();
        onLinePlayersName=OnlinePlayers.split("\\.");
    } catch (IOException ex) {
        Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
    }
        ObservableList<Player> elements = FXCollections.observableArrayList();
        for (int i = 0; i < onLinePlayersName.length; i++) {
            elements.add(new Player(onLinePlayersName[i],"0"));

        }
        // elements.add(new Game("1","2","3","4","5","6"));

        System.out.println("ele:" + elements);
/*
        playerName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        PlayerScore.setCellValueFactory(new PropertyValueFactory<Player, String>("score"));
        TableP.setItems(elements);
*/

    
    } 
    void setUserNameScore(String userName,String score)
    {
        this.userName=userName;
        this.score=score;
    }
   void getOnlinePlayers()
    {
           new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                       OnlinePlayers = client.readResponse();
                        System.out.println("The message sent from the socket was: " + OnlinePlayers);
                      
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                        
    }}).start();
               
    }
            
           
    
}

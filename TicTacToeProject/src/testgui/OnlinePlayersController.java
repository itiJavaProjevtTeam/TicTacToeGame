/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class OnlinePlayersController extends Thread implements Initializable {

    Client client;
    String userName;
    String scoreClient;
    String OnlinePlayers;

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
    private TableColumn<Player,String> PlayerScore;

    @FXML
    private void handleOnlineAction(ActionEvent event) throws IOException {
        Parent scen1viewer = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene s1 = new Scene(scen1viewer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(s1);
        window.show();
        // getOnlinePlayers();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            userName = "nermeen";

            client = Client.getClient("127.0.0.1", 5007);
            client.sendMessage(userName + ".5." + "PLAYERLIST");
            System.out.println("i am here2");
            //start();
            OnlinePlayers = client.readResponse();
            System.out.println("The message : " + OnlinePlayers);
            System.out.println("The message sent from the socket was: " + OnlinePlayers);
            String [] nameScoreList=OnlinePlayers.split("\\.");
            ArrayList<String> scoreList = new ArrayList<>();
                    for(int j=13;j<nameScoreList.length;j++){
                        scoreList.add(nameScoreList[j]);
                        //System.out.println(scoreList.toString());
                    }
            //String [] nameList=nameScoreList[0].split("\\.");
           // String [] scoreList=nameScoreList[1].split("\\.");
        ObservableList<Player> elements = FXCollections.observableArrayList();
        for (int i = 0,j=nameScoreList.length/2; i < nameScoreList.length/2&&j<nameScoreList.length; i++,j++) {
           // System.out.println(nameScoreList[i]+":"+nameScoreList[j]);
            //System.out.println(nameScoreList[i]+"  "+scoreList.get(i));
            Player p = new Player(nameScoreList[i],scoreList.get(i));
            System.out.println(p.getName()+"    "+p.getscore());
            elements.add(i, p);
                    // System.out.println("ele:"+ elements.get(i));
        }
      //  System.out.println("ele:" + elements);
        playerName.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));

        PlayerScore.setCellValueFactory(new PropertyValueFactory<Player,String>("pScore"));
        TableP.setItems(elements);
//        System.out.println(TableP.getSelectionModel().getSelectedItem().getscore());
        } catch (IOException ex) {
        ex.printStackTrace();        }

    }

    void setUserNameScore(String userName, String score) {
        this.userName = userName;
        this.scoreClient = score;
    }

    public void run() {
        try {
            OnlinePlayers = client.readResponse();
            System.out.println("The message : " + OnlinePlayers);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}

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
    ObservableList<Player> elements;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket mySocket;

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
    private void handleOnlineAction(ActionEvent event) throws IOException {
        Parent scen1viewer = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene s1 = new Scene(scen1viewer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(s1);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PlayerScore.setVisible(false);
        try {
            mySocket = new Socket("127.0.0.1", 5007); // static variable for server ip
            dis = new DataInputStream(mySocket.getInputStream());
            dos = new DataOutputStream(mySocket.getOutputStream());
            userName = "nermeen";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        client = Client.getClient("127.0.0.1", 5007);
                        client.sendMessage(userName + ".5." + "PLAYERLIST");
                        System.out.println("i am here2");
                        OnlinePlayers = client.readResponse();
                        System.out.println("The message : " + OnlinePlayers);
                        System.out.println("The message sent from the socket was: " + OnlinePlayers);
                        String[] nameScoreList = OnlinePlayers.split("\\.");
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
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    void setUserNameScore(String userName, String score) {
        this.userName = userName;
        this.scoreClient = score;
    }

    @FXML
    private void OnMousePressed(MouseEvent event) {
        Player opponentName = TableP.getSelectionModel().getSelectedItem();
        if (opponentName != null) {
            TableP.setMouseTransparent(true);
            TableP.setFocusTraversable(false);
            new Thread() {
                public void run() {
                    try {
                        String sentMsg = new String("DUWTP." + opponentName.getName() + "." + userName);
                        client.sendMessage(sentMsg);
                        System.out.println("pressed on" + opponentName.getName());
                        int d = 0;
                        while (true) {
                            String recievedReqeustMsg = null;
                            System.out.println(++d + "");
                            recievedReqeustMsg = client.readResponse();
                            parsing(recievedReqeustMsg);
                            System.out.println(recievedReqeustMsg);
                            if (parsedMsg[0].equals("PREQ") && parsedMsg[1].equals("accept") && parsedMsg[2].equals(userName)) {
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        try {
                                            System.out.println("accepted");
                                            FXMLLoader loader = new FXMLLoader();
                                            loader.setLocation(getClass().getResource("GameOnline.fxml")); // game board
                                            Parent viewparent = loader.load();
                                            Scene viewscene = new Scene(viewparent);
                                            GameOnlineController controller = loader.getController();
                                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            System.out.println("stage: " + window.toString());
                                            window.setScene(viewscene);
                                            window.show();
                                        } catch (IOException ex) {
                                            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                            } else if (parsedMsg[0].equals("PREQ") && parsedMsg[1].equals("reject") && parsedMsg[2].equals(userName)) {
                                System.out.println("rejection received");
                                TableP.getSelectionModel().clearSelection();
                                TableP.setMouseTransparent(false);
                                TableP.setFocusTraversable(true);
                                break;
                            } else {
                                //nothing
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();

        }

    }

    public void getPlayerList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("PLAYERLIST");
                    while (true) {
                        String msg = dis.readUTF();
                        parsing(msg);
                        if (parsedMsg[0].equals("DUWTP") && parsedMsg[1].equals(userName)) {
                            String oppName = parsedMsg[2];
                            System.out.println("play request for me  " + oppName);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (didConfirm() == true) {
                                        try {
                                            System.out.println("accepted ");
                                            String sentMsg = new String("PREQ.accept." + oppName + "." + userName);

                                            dos.writeUTF(sentMsg);

                                            Platform.runLater(new Runnable() {
                                                public void run() {
                                                    showBoardForOpponent(oppName, userName);
                                                }
                                            });
                                        } catch (IOException ex) {
                                            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {
                                        System.out.println("rejection sent");
                                        String sentMsg = new String("PREQ.reject." + oppName + "." + userName);
                                        System.out.println(sentMsg);
                                        try {
                                            dos.writeUTF(sentMsg);
                                        } catch (IOException ex) {
                                            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            });
                        } else if (parsedMsg[0].equals("PLAYERLIST")) {
                            loadTableView();
                            dos.writeUTF("PLAYERLIST");
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public void parsing(String recievedMsg) {
        parsedMsg = recievedMsg.split("\\.");
    }

    public boolean didConfirm() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("CONFIRMATION");
        confirmationAlert.setHeaderText("CONFIRMATION");
        confirmationAlert.setContentText("Play?");
        ButtonType buttonTypeAccept = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void showBoardForOpponent(String opponent, String player) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GameOnline.fxml")); // gameboard gui
            Parent viewparent = fxmlLoader.load();
            Scene viewscene = new Scene(viewparent);
            GameOnlineController controller = fxmlLoader.getController();
            controller.getPlayer2Name(opponent, "15");
            Stage window = (Stage) TableP.getScene().getWindow();
            window.setScene(viewscene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlinePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableView() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                elements.removeAll(elements);
                TableP.getItems().clear();
                for (int i = 0, j = parsedMsg.length / 2; i < parsedMsg.length / 2 && j < parsedMsg.length; i++, j++) {

                    if (parsedMsg[i].equals(userName) || parsedMsg[i].equals("PLAYERLIST")) {
                        continue;
                    }
                    elements.add(i, new Player(parsedMsg[i], parsedMsg[j]));
                }
                TableP.getItems().addAll(elements);
            }
        });
    }

}

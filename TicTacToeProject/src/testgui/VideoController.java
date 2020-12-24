/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamedjoe
 */
public class VideoController implements Initializable {

    @FXML
    private MediaView meaiaViewer;
    @FXML
    private ImageView playAgainBtn;
    @FXML
    private ImageView exitBtn;
    @FXML
    private Label WinnerPlayer;
    @FXML
    private Label congrates;

    MediaPlayer mediaPlayer;

    String modes;
    String PlayeOName = "", LocalPlayeOName = "";
    String PlayeXName = "", LocalPlayeXName = "";

    String medialoserUrl;
    String mediawinnerUrl;
    String winnerPlayerss;
    Media media;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        medialoserUrl = "build/classes/images/loser.mp4";

        mediawinnerUrl = "build/classes/images/winner.mp4";

    }

    public void setWinnerName(String winner, String mode, String PXName, String POName) {

        winnerPlayerss = winner;
        WinnerPlayer.setText(winner);
        modes = mode;
        PlayeXName = PXName;
        PlayeOName = POName;
        System.out.println(PlayeXName + "///" + PlayeOName);

        
        
        if(modes.equalsIgnoreCase("GameOnline.fxml")&&winnerPlayerss.equals(PlayeXName)){
         media = new Media(new File(mediawinnerUrl).toURI().toString());
        }else if(modes.equalsIgnoreCase("GameOnline.fxml")&&winnerPlayerss.equals(PlayeOName)){
             WinnerPlayer.setText("");
            congrates.setText("oops you lose the game!! ");
            media = new Media(new File(medialoserUrl).toURI().toString());
        }
        
        else if (modes.equalsIgnoreCase("Game.fxml")&&winnerPlayerss.equalsIgnoreCase("pc")) {
           
            congrates.setText("oops the winner is ");
            media = new Media(new File(medialoserUrl).toURI().toString());
            
        }  else if (modes.equalsIgnoreCase("Game.fxml")&&winnerPlayerss.equalsIgnoreCase(PlayeXName)) {
          media = new Media(new File(mediawinnerUrl).toURI().toString());
            
        } 
        
        else if(modes.equalsIgnoreCase("GameLocal.fxml")&&winnerPlayerss.equalsIgnoreCase(PlayeXName)) {

            media = new Media(new File(mediawinnerUrl).toURI().toString());

        }
  else if(modes.equalsIgnoreCase("GameLocal.fxml")&&winnerPlayerss.equalsIgnoreCase(PlayeOName)) {

            media = new Media(new File(medialoserUrl).toURI().toString());

        }
        mediaPlayer = new MediaPlayer(media);
        meaiaViewer.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

    }

    public void assignLocalplayername(String P1Name, String P2Name) {

        LocalPlayeXName = P1Name;

        LocalPlayeOName = P2Name;
}
    @FXML
    private void playAgain(MouseEvent event) throws IOException{
        if(mediaPlayer.getStatus()==PLAYING){
        mediaPlayer.stop();}
      
        
       // FXMLLoader Loader = new FXMLLoader();
       Parent root;
        if (modes.equalsIgnoreCase("GameLocal.fxml")) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource(modes));
            
            root = loader.load();
            GameLocalController GLc = loader.getController();
            GLc.getLocalplayername(LocalPlayeXName, LocalPlayeOName);
        } else if(modes.equalsIgnoreCase("GameOnline.fxml")){
        
              FXMLLoader loader = new FXMLLoader(getClass().getResource(modes));
            
            root = loader.load();
            GameOnlineController GOC= loader.getController();
            GOC.getONlineplayername(PlayeXName, PlayeOName);
            System.out.println(PlayeXName + "///" + PlayeOName);
        }else {
             FXMLLoader loader = new FXMLLoader(getClass().getResource(modes));
            
            root = loader.load();
            GameController Gc = loader.getController();
            Gc.getplayername(PlayeXName, PlayeOName);
            System.out.println(PlayeXName + "///" + PlayeOName);
        }

       Scene s1 = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(s1);
            window.show();
      
        

    }
    

    
    

    @FXML
    private void Exit(MouseEvent event) throws IOException{
        if(mediaPlayer.getStatus()==PLAYING){
        mediaPlayer.stop();}
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Dashboard.fxml"));
        Loader.load();

        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();

        s.close();

        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        
        stage.setScene(new Scene(p));
        stage.show();
    }

}

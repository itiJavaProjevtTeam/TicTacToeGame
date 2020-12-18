/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button playAgainBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Label WinnerPlayer;
    @FXML
    private Label congrates;
  
    MediaPlayer mediaPlayer;
  
    String modes;  
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         String winnerUrl="file:/F:/XO_videos/winner.mp4";
         String LoserUrl="file:/F:/XO_videos/loser.mp4";
         String vid;
         if(WinnerPlayer.getText().equalsIgnoreCase("pc")){
         vid=LoserUrl;
         }else{
         vid=winnerUrl;
         }
         
        Media media = new Media (vid);
        mediaPlayer = new MediaPlayer(media);
        meaiaViewer.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    } 
   
    public void setWinnerName(String winner,String mode){
           WinnerPlayer.setText(winner);
           modes=mode;
           
    }
    
  
    

    @FXML
    private void playAgain(ActionEvent event) throws IOException {
          if(mediaPlayer.getStatus()==PLAYING){
        mediaPlayer.stop();}
        FXMLLoader Loader = new FXMLLoader();
        if(modes.equalsIgnoreCase("GameLocal.fxml")){
        Loader.setLocation(getClass().getResource("GameLocal.fxml"));
        }else{
        Loader.setLocation(getClass().getResource("Game.fxml"));
        }
        Loader.load();
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        
    }

    @FXML
    private void Exit(ActionEvent event) throws IOException {
          if(mediaPlayer.getStatus()==PLAYING){
        mediaPlayer.stop();}
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Dashboard.fxml"));
        Loader.load();
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        
    }
    
}

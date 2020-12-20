/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import javafx.application.Application;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Laptop
 */
public class TestGUI extends Application {
    
    private static final int COUNT_LIMIT = 300000;
    
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene scene = new Scene(root,700,395);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
     
    }
    
    @Override
    public void init() throws Exception {
        
        for(int i = 0; i < COUNT_LIMIT;i++)
        {
            double progress = (100*i)/COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this,new Preloader.ProgressNotification(progress));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(TestGUI.class,MyPreloader.class,args);
        
    }
    
}

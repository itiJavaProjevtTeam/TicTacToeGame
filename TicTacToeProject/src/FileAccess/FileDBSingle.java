/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileAccess;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import testgui.SingleModeController;

/**
 *
 * @author nermeen
 */
public class FileDBSingle {

    FileOutputStream fosSngl;
    FileInputStream fisSngl;
    DataInputStream disSngl;
    DataOutputStream dosSngl;
    File fileSingle;
    public String dataSngl;

    public FileDBSingle() {
        fileSingle = new File("single.txt");
        try {
            if (fileSingle.createNewFile()) {
                System.out.println("file created" + fileSingle.getName() + fileSingle.getPath());
            } else {
                System.out.println("file single already existed");
            }
            System.out.println("length:"+fileSingle.length());
            dataSngl=readSingleFile();
        }
         catch (IOException ex) {
           ex.printStackTrace();
        }
       
        

    }
    //write in solo mode

   
    public void WriteSingleGameSteps(String player1,int scoreP1,int scorePc, LinkedHashMap<Integer, String> moves,String winner) {
       
        try {
            
            fosSngl = new FileOutputStream(fileSingle);
            dosSngl = new DataOutputStream(fosSngl);
            LocalDateTime ldt=LocalDateTime.now();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = ldt.format(formatter);
            dataSngl+=formatDateTime+","+player1+","+scoreP1+","+"pc"+","+scorePc+",";
           for (int key:moves.keySet()) {
             dataSngl+=key+","+moves.get(key)+",";      
            }
            dataSngl+=winner+"/n";
            dosSngl.writeUTF(dataSngl);
            fosSngl.close();
            dosSngl.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String readSingleFile() {
        String data = "";
        if (fileSingle.length() >2 ) {
            try {
                fisSngl = new FileInputStream(fileSingle);
                disSngl = new DataInputStream(fisSngl);
                data = disSngl.readUTF();
                fisSngl.close();
                disSngl.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return data;
    }
  public String [] readSingleGameDateTime() {
     
        String data = "";
        if (fileSingle.length() > 2) {
            try {
                fisSngl = new FileInputStream(fileSingle);
                disSngl = new DataInputStream(fisSngl);
                data = disSngl.readUTF();
                fisSngl.close();
                disSngl.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        String result[] = data.split("/n", data.length());
         String [] gameDT=new String[result.length];
        for(int i=0;i<result.length;i++)
        {
            String game[] = result[i].split(",", data.length()); 
            gameDT[i]=game[0];
        }
        return gameDT;
    }
 

}

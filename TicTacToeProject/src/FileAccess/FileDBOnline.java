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
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

/**
 *
 * @author mohamedjoe
 */
public class FileDBOnline {
     FileOutputStream fosOnline;
    FileInputStream  fisOnline;
    DataInputStream  disOnline;
    DataOutputStream  dosOnline;
    File fileOnline;
    public String dataOnline;
    public FileDBOnline()
    {
           fileOnline = new File("Online.txt");   
        try {
            if (fileOnline.createNewFile()) {
                System.out.println("file created" + fileOnline.getName() + fileOnline.getPath());
            } else {
                System.out.println("file online already existed");
            }
                System.out.println("length:"+fileOnline.length());
                 dataOnline=readOnlineFile();
        }
         catch (IOException ex) {
           ex.printStackTrace();
        }
    }
    public void WriteOnlineGameSteps(String player1,int scoreP1,String player2,int scoreP2, LinkedHashMap<Integer, String> moves,String winner) {
       
        try {
            
            fosOnline = new FileOutputStream(fileOnline);
            dosOnline = new DataOutputStream(fosOnline);
           
               LocalDateTime ldt=LocalDateTime.now();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String formatDateTime = ldt.format(formatter);
            dataOnline+=formatDateTime+","+player1+","+scoreP1+","+player2+","+scoreP2+",";
           for (int key:moves.keySet()) {
             dataOnline+=key+","+moves.get(key)+",";      
            }
            dataOnline+=winner+"/n";
            dosOnline.writeUTF(dataOnline);
            fosOnline.close();
            dosOnline.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String readOnlineFile() {
        String data = "";
        if (fileOnline.length() >2 ) {
            try {
                fisOnline = new FileInputStream(fileOnline);
                disOnline = new DataInputStream(fisOnline);
                data = disOnline.readUTF();
                fisOnline.close();
                disOnline.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return data;
    }
  public String [] readOnlineGameId() {
     
        String data = "";
        if (fileOnline.length() > 2) {
            try {
                fisOnline = new FileInputStream(fileOnline);
                disOnline = new DataInputStream(fisOnline);
                data = disOnline.readUTF();
                fisOnline.close();
                disOnline.close();
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
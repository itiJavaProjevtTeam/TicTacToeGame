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
import java.util.LinkedHashMap;

/**
 *
 * @author nermeen
 */
public class FileDBLocal {
    FileOutputStream fosLocl;
    FileInputStream  fisLocl;
    DataInputStream  disLocl;
    DataOutputStream  dosLocl;
    File fileLocal;
    String dataLocl;
    public FileDBLocal()
    {
           fileLocal = new File("local.txt");   
        try {
            if (fileLocal.createNewFile()) {
                System.out.println("file created" + fileLocal.getName() + fileLocal.getPath());
            } else {
                System.out.println("file local already existed");
            }
                System.out.println("length:"+fileLocal.length());
                 dataLocl=readLocalFile();
        }
         catch (IOException ex) {
           ex.printStackTrace();
        }
    }
    public void WriteLocalGameSteps(String player1,int scoreP1,String player2,int scoreP2, LinkedHashMap<Integer, String> moves,String winner) {
       
        try {
            
            fosLocl = new FileOutputStream(fileLocal);
            dosLocl = new DataOutputStream(fosLocl);
            LocalDateTime ldt=LocalDateTime.now();
            dataLocl+=ldt+","+player1+","+scoreP1+","+player2+","+scoreP2+",";
           for (int key:moves.keySet()) {
             dataLocl+=key+","+moves.get(key)+",";      
            }
            dataLocl+=winner+"/n";
            dosLocl.writeUTF(dataLocl);
            fosLocl.close();
            dosLocl.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String readLocalFile() {
        String data = "";
        if (fileLocal.length() >2 ) {
            try {
                fisLocl = new FileInputStream(fileLocal);
                disLocl = new DataInputStream(fisLocl);
                data = disLocl.readUTF();
                fisLocl.close();
                disLocl.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return data;
    }
  public String [] readLocalGameDateTime() {
     
        String data = "";
        if (fileLocal.length() > 2) {
            try {
                fisLocl = new FileInputStream(fileLocal);
                disLocl = new DataInputStream(fisLocl);
                data = disLocl.readUTF();
                fisLocl.close();
                disLocl.close();
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

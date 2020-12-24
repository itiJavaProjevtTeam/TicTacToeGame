/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author nermeen
 */
public class Client extends Thread {
//private static final int CONNECTION_TIMEOUT = 3000;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket mySocket;
    static Client client;
    private Client(String serverAddr, int port) throws IOException{
        mySocket = new Socket(serverAddr,port);
        dis = new DataInputStream(mySocket.getInputStream());
        dos = new DataOutputStream(mySocket.getOutputStream());
    }
   /* public Client(String ip, int port)  {     
    }*/
    public static Client getClient(String serverAddr, int port) throws IOException
    {
        if(client==null)
        {
           client=new Client( serverAddr,port); 
        }
        return client;
    }
      public void startConnection(String serverAddr, int port) throws IOException {
       
      
    }

 public void sendMessage(String msg) throws IOException {
        dos.writeUTF(msg);
        dos.flush();
    }

    public String readResponse() throws IOException {
        return dis.readUTF();
    }

    public void closeConnection() throws IOException {
            dis.close();
            dos.close();
            mySocket.close();
    }
      public boolean isConnected() {
        return mySocket != null && mySocket.isConnected();
    }
      
      public int isReading(){
          int response = 0;
        try {
            response = mySocket.getInputStream().read();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
     }
       public void closeClient() {
        try {
            dos.close();
            dis.close();
            mySocket.close();
            Alert confirmationAlert = new Alert(Alert.AlertType.ERROR);
            confirmationAlert.setTitle("error");
            confirmationAlert.setHeaderText("connection error");
            confirmationAlert.setContentText("Server is down");
            ButtonType buttonTypeAccept = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            Optional<ButtonType> result = confirmationAlert.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   /* public void sendHandChMeg() {
        ps.println(" Is Connection good? ");
    }
 
    public void sendCurPos(int pos) {
        currentPos=pos;
        ps.println(pos + "");
    }

    public int recieveOpponentPos() {
        return opponentPos;
    }

    public void run() {
        int ind = 0;
        while (true) {
            try {
                String msg = dis.readLine();
                if (msg != null) {
                    if (ind >= 1) {
                        opponentPos = Integer.parseInt(msg);
                    } else if(ind==0){
                        System.out.println(msg);
                    }
                    else if(ind==1)
                    {
                       order=msg; 
                       if (order.equals("First"))
                       {
                        ps.println(sgm + "");   
                       }
                       else if(order.equals("Secand"))
                       {
                         String sgm2 = dis.readLine();
                         oppSgm=sgm2;
                       } 
                       
                    }
                    ind++;
           
            } catch (IOException ex) {
                 ex.printStackTrace();
            }

        }
    }*/ 
}


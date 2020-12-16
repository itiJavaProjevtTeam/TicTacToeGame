/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author nermeen
 */
public class Client extends Thread {
//private static final int CONNECTION_TIMEOUT = 3000;
    private DataInputStream dis;
    private PrintStream ps;
    private Socket mySocket;
  //  int currentPos, opponentPos;
 // String order;
 // public  String sgm,oppSgm;
    public Client(){}
    public Client(String ip, int port) throws IOException {
        mySocket = new Socket(ip, port); 
        dis = new DataInputStream(mySocket.getInputStream());
        ps = new PrintStream(mySocket.getOutputStream());
        /*currentPos = -1;
        opponentPos = -1;
        start();*/
    }
      public void startConnection(String serverAddr, int port) throws IOException {
        mySocket = new Socket(serverAddr,port);
       // mySocket.connect(new InetSocketAddress(serverAddr, port),CONNECTION_TIMEOUT);
        dis = new DataInputStream(mySocket.getInputStream());
        ps = new PrintStream(mySocket.getOutputStream());
      
    }

 public void sendMessage(String msg) {
        ps.println(msg);
    }

    public String readResponse() throws IOException {
        return dis.readLine();
    }

    public void closeConnection() throws IOException {
            dis.close();
            ps.close();
            mySocket.close();
      
    }
      public boolean isConnected() {
        return mySocket != null && mySocket.isConnected();
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


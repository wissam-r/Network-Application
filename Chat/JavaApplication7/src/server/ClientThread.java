/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author falcon
 */
public class ClientThread extends Thread {

    private Socket socket;
    private String id;

    public String getIds() {
        return id;
    }

    public ClientThread(String id, Socket socket) {
        this.id = id;
        this.socket = socket;
    }
    String clientSentenceId;
    String clientSentenceMsg;

    String capitalizedSentence;

    public boolean getMSG(Message msg) {
        try {
//            System.out.println(msg.senderId + msg.receverId + getIds() + msg.msg);
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            outToClient.writeBytes("From : " +msg.senderId +" : "+ msg.msg);
            
                    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    boolean done = false;

    public void run() {
        try {
            while (true) {

                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                clientSentenceId = inFromClient.readLine();
                clientSentenceMsg = inFromClient.readLine();

                
                Message msg = new Message(getIds(), clientSentenceId, clientSentenceMsg+ '\n');
                Server.pairMSG(msg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

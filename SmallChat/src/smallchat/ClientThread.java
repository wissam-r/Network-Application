/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smallchat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author CG
 */
public class ClientThread extends Thread {

    private Socket socket;
    String clientSentence;
    String capitalizedSentence;
    String clientName;
    
    public String getClientName(){
        return clientName ;
    }
    public void setClientName(String clientName){
        this.clientName = clientName ;
    }

    public ClientThread(Socket socket) {
        this.socket = socket;
    }
    

    @Override
    public void run() {
        try {
//                DataInputStream in
//                        = new DataInputStream(socket.getInputStream());
//                System.out.println(in.readUTF());
//                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//                out.writeUTF("Thank you for connecting to "+ socket.getLocalSocketAddress() + "\nGoodbye!") ;
//                socket.close();
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            BufferedReader inFromClient;
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientSentence = inFromClient.readLine();
//            outToClient.writeBytes();
            
            while (true) {

                inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                clientSentence = inFromClient.readLine();

                capitalizedSentence = clientSentence.toUpperCase() + '\n';

                outToClient.writeBytes(capitalizedSentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

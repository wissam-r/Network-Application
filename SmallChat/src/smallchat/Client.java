/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smallchat;

import java.net.*;
import java.io.*;

public class Client {
    
    public static void main(String[] args) {
        String sentence;
        String modifiedSentence;
        String serverName = "Test Server";
        int port = 9090;
        try {
            System.out.println("Connecting to " + serverName
                    + " on port " + port);

            Socket client = new Socket("localhost", port);
            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());
            DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
            BufferedReader inFromUser;
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outToServer.writeUTF("Hello from " + client.getLocalSocketAddress());
//            DataInputStream in
//                    = new DataInputStream(inFromServer);
//            System.out.println("Server says " + in.readUTF());
//            client.close();
            
            while (true) {

                inFromUser = new BufferedReader(new InputStreamReader(System.in));

                sentence = inFromUser.readLine();

                outToServer.writeBytes(sentence + '\n');

                modifiedSentence = inFromServer.readLine();

                System.out.println("FROM SERVER: " + modifiedSentence);
                if (modifiedSentence.equals(".")) {
                    break;
                }
            }
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

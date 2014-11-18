/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author falcon
 */
public class ClientWriter extends Thread {

    private Socket socket;

    public ClientWriter(Socket socket) {
        this.socket = socket;
    }

    BufferedReader inFromServer;
    String modifiedSentence;

    public void run() {
        try {
            while (true) {

                if (socket.isClosed()) {
                    System.out.println("Socket Closed");
                    break ;
                }
                inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                modifiedSentence = inFromServer.readLine();
                System.out.println(modifiedSentence);

            }
            
        } 
        catch (SocketException ex) {
            System.out.println("Socket Closed");
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

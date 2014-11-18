/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author falcon
 */
public class ClientReader extends Thread {

    private Socket socket;
    String sentenceId;
    String sentenceMsg ;
    public ClientReader(Socket socket) {
        this.socket = socket;
    }
    BufferedReader inFromUserId;
    BufferedReader inFromUserMsg;
    DataOutputStream outToServer;

    public void run() {
        try {
            while (true) {
                inFromUserId = new BufferedReader(new InputStreamReader(System.in));
                inFromUserMsg = new BufferedReader(new InputStreamReader(System.in));
                outToServer = new DataOutputStream(socket.getOutputStream());
                sentenceId = inFromUserId.readLine();
                if (sentenceId.equals("."))
                    break ;
                sentenceMsg = inFromUserMsg.readLine() ;
                outToServer.writeBytes(sentenceId+'\n');
                outToServer.writeBytes(sentenceMsg+'\n');
                if (sentenceId.equals("."))
                    break ;
            }
            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}

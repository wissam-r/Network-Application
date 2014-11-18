/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Zein
 */
public class Client {

    
    public static void main(String argv[]) throws Exception {
        
        Socket clientSocket = new Socket("localhost", 6789);
        ClientReader clientReader = new ClientReader(clientSocket);
        ClientWriter clientWriter = new ClientWriter(clientSocket) ;
        clientReader.start(); 
        clientWriter.start();
        while(!clientSocket.isClosed()){}
        
            
    }
}

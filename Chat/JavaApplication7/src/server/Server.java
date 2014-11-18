/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Zein
 */


public class Server {
    
    static int counterId=0;
    static ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    
    public static boolean brodMSG(Message msg){
        for (ClientThread clientThread : clients) {          
                clientThread.getMSG(msg);   
        }
        return true ;
    }
    public static boolean pairMSG(Message msg){
        
        for (ClientThread clientThread : clients) {
            if (clientThread.getIds().equals( msg.receverId)){
                clientThread.getMSG(msg);
                break ;
            }     
        }
        return true ;
    }
    
    public static String getCurrentUser(ArrayList<ClientThread> clients){
        String Users = "Current users : "+ clients.size() + " : "; 
                
        for (ClientThread clientThread : clients) {
            Users += clientThread.getIds() + " ," ;
        }
        return Users ;
    }

    public static void main(String args[]) throws Exception {
        
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {

            Socket connectionSocket = welcomeSocket.accept();
            ClientThread client  = new ClientThread(String.valueOf(++counterId),connectionSocket) ;
            System.out.println("New user : " + counterId+ " , on Port : " 
                    + connectionSocket.getPort() + " , with IP : "+ connectionSocket.getRemoteSocketAddress());
            brodMSG(new Message("Server", null, "new user with id : "+counterId +'\n')) ;
            Message msg = new Message("Server", null, getCurrentUser(clients) + '\n') ;
            clients.add(client);
            client.start(); 
            client.getMSG(msg) ;
        }
    }
}

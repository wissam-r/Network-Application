
package smallchat;

/**
 *
 * @author CG
 */


import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server extends Thread {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
//        serverSocket.setSoTimeout(10000);
    }
    
    static ArrayList<ClientThread> clients = new ArrayList<>() ;
    public static ArrayList<String> getClients (){
        ArrayList<String> clinetsName = new ArrayList<>() ;
        for (ClientThread name : clients) {
            clinetsName.add(name.getClientName()) ;
        }
        return clinetsName;
    }
    
    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client on port "
                        + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());
                ClientThread client = new ClientThread(server) ;
                client.start();
                clients.add(client) ;
                
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

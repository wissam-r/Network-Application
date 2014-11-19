/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengean;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author CG
 */
public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException  {
        int port = 5000 ;
        Registry registry = LocateRegistry.getRegistry("localhost", port) ;
        PServerInterface pServer = (PServerInterface) registry.lookup("//localhost:5000/SearchEngean") ;
        System.out.println(pServer.search("test secondry"));
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author CG
 */


public class Client {
    
    //define registry name
    private String registryName ;
    //define registry address
    private String registryAddress ;
    //define registry port
    private int registryPort ;
    //sserver interface object
    PServerInterface pServer  ;

    public PServerInterface getpServer() {
        return pServer;
    }
    
    
    public Client ()throws Exception{
        this.registryName = "SearchEngine" ;
        this.registryAddress = "localhost" ;
        this.registryPort = 5000 ;
        connectToTheEngine(registryName, registryAddress, registryPort);

    }
    public Client (String registryName , String registryAddress ,
            int registryPort) throws Exception{
    
        this.registryName = registryName ;
        this.registryAddress = registryAddress ;
        this.registryPort = registryPort ;
        connectToTheEngine(registryName, registryAddress, registryPort);
    }
    
    private void connectToTheEngine (String registryName , String registryAddress ,
            int registryPort) throws RemoteException , Exception{
        
        //get getregistry
        Registry registry = LocateRegistry.getRegistry(registryAddress,registryPort);
        //get sserver interface object
        pServer = (PServerInterface) registry.lookup
            ("//"+registryAddress+":"+registryPort+"/"+registryName);
    }
    
    public static String search(String songName , PServerInterface pServer) throws RemoteException{
        return pServer.search(songName) ;
    }
    public static void main(String[] args) throws RemoteException, NotBoundException  {
        int port = 5000 ;
        Registry registry = LocateRegistry.getRegistry("localhost", port) ;
        PServerInterface pServer = (PServerInterface) registry.lookup("//localhost:5000/SearchEngine") ;
        System.out.println(pServer.search("test secondry"));      
    }
}

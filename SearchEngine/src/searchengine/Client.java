/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javazoom.jl.decoder.JavaLayerException;
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
    //current song file
    private File currentSongFile = null ;
    //current running song
    private Thread currentSong= null ;

    public PServerInterface getpServer() {
        return pServer;
    }
    
    
    public Client ()throws Exception{
        this("SearchEngine","localhost" , 5000 ) ;
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
    
    public static String search(String songName ,String specifiPath , PServerInterface pServer) throws RemoteException{
        //call the search from connected PServer
        String result = pServer.search(songName , specifiPath)  ;
        if (result.equals(""))
            return "no match" ;
        return result ;
    }
    public void playSong (String songPath) throws RemoteException , JavaLayerException, Exception{
//        System.out.println(currentSongFile.getPath());
       if (currentSongFile ==null){
            currentSongFile = pServer.playSong(songPath) ;
            System.out.println("null");
       }
       else if (!(currentSongFile.getPath().equals(songPath))){
           currentSongFile = pServer.playSong(songPath) ;
           System.out.println("note equal");
       }
       System.out.println("equal");
        currentSong = new MP3Player(currentSongFile) ;
        currentSong.start() ;
        
    }
    public void stopSong () throws RemoteException , JavaLayerException, Exception{
        if (!( currentSong == null)){
            currentSong.stop(); 
            currentSong = null ;
        }
        
    }
    public String getLyrics (String songPath) throws RemoteException, IOException , Exception{
        System.out.println(songPath.substring(0  ,songPath.length()-3));
        return pServer.getLyrics(songPath.substring(0  ,songPath.length()-3)+"txt") ;
    }
    public static void main(String[] args) throws RemoteException, NotBoundException  {
    }
}

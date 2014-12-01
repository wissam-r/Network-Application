/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import InputOutFiles.* ;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import multicast.mcsend;

/**
 *
 * @author CG
 */
public class PServer extends UnicastRemoteObject implements PServerInterface, SServerInterface{
    
    //multicast channel variable
    private mcsend channel;
    //secondary servers number increased on join
    private int SServers = 0;
    //array list of response from secondary servers
    private ArrayList<String> response = new ArrayList<>();
    //define multicast group
    private String multicastAddress ;
    //define multicast port
    private int multicastPort ;
    //define file path and name
    private String filePath ; 
    //define registry name
    private String registryName ;
    //define registry address
    private String registryAddress ;
    //define registry port
    private int registryPort ;
    //define registry to rebind
    Registry registry ;
    
    public PServer (String filePath , String registryName , String registryAddress ,int registryPort 
            , String multicastAddress , int multicastPort) throws RemoteException, Exception {
        super();
        this.filePath =  filePath ;
        this.multicastAddress = multicastAddress ;
        this.multicastPort = multicastPort ;
        this.registryName = registryName ;
        this.registryAddress = registryAddress ;
        this.registryPort = registryPort ;       
        this.startEngine(registryName, registryAddress, registryPort, this.registry );
        //open a multicast socket to connect with secondary servers
        this.channel = new mcsend(this.multicastPort, this.multicastAddress, 10);
    }
    
    public PServer () throws RemoteException, Exception {
        this("TestFile.txt" ,"SearchEngine" ,"localhost",5000, "228.5.6.7" ,5001 );
    }
    
    
    private void startEngine (String registryName , String registryAddress ,
            int registryPort ,Registry registry) throws RemoteException{
        
        //create registry for PServer
        registry = LocateRegistry.createRegistry(registryPort) ;
        //bind the registry
        registry.rebind("//"+registryAddress+":"+registryPort+"/"+registryName, this);
    }
    @Override
    public String search(String songName , String specificPath) throws RemoteException {
        
        //define search result varible
        String songSearch = "" ;
        try {
            //search for the song in the specific file
            songSearch = Reader.searchForSong(this.filePath , songName , specificPath);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        if(songSearch.equals("")){
            try {
                // clear result array for new search
                response.clear();              
                //multicast all secondary servers the song's name
                this.channel.send(songName);
                //multicast all secondary servers the song's path
                this.channel.send(specificPath);
                //wait for responses from the secondary servers
                boolean SecondarySearchDone = false;
                while(!SecondarySearchDone){
                    //wait for get the result from SServers
                    Thread.sleep(250);
                    if(this.SServers == response.size()){
                        //all SServers replays
                        SecondarySearchDone = true;
                        for (String string : response) {
                            songSearch += string +"\n" ;
                        }}
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return songSearch ;
    }
    
    //new SServer join the group
    @Override
    public void join(){
        this.SServers += 1 ; 
    }
    //on of SServers shutdown
    @Override
    public void unJoin(){
        this.SServers -= 1 ; 
    }
    //get result for search request
    @Override
    public void submitResault(String resault){
        this.response.add(resault);
    }

    @Override
    public File playSong(String songPath) throws RemoteException  {
        try { 
        return new File(songPath);
        } catch (Exception ex) {
            Logger.getLogger(PServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
        }

    @Override
    public void stopSong(String songPath) throws RemoteException {
         FileInputStream fis ;
        try {
            fis = new FileInputStream(songPath);
            Player playMP3 = new Player(fis);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavaLayerException ex) {
            Logger.getLogger(PServer.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @Override
    public String getLyrics(String songPath) throws RemoteException , IOException {
        return Reader.getLyricsFromFile(songPath) ;
    }
    

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import InputOutFiles.ReadFromFile;
import multicast.mcjoin;
import java.io.*;
import java.net.Socket;
import java.rmi.registry.*;
/**
 *
 * @author eyadof 
 */
public class SServer extends Thread{
    
    //define file path and name
    private String filePath ; 
    //define registry name
    private String registryName ;
    //define registry address
    private String registryAddress ;
    //define registry port
    private int registryPort ;
    //define multicast group
    private String multicastAddress ;
    //define multicast port
    private int multicastPort ;
    
    public SServer (){
        this.filePath = "songs.txt" ;
        this.registryName = "SearchEngine" ;
        this.registryAddress = "localhost" ;
        this.registryPort = 5000 ;
        this.multicastAddress = "228.5.6.7" ;
        this.multicastPort = 5001 ; 
    }
    
    public SServer (String filePath , String registryName , String registryAddress ,
            int registryPort  ,String multicastAddress  , int multicastPort 
    ){
        this.filePath = filePath ;
        this.registryName = registryName ;
        this.registryAddress = registryAddress ;
        this.registryPort = registryPort ;
        this.multicastAddress = multicastAddress ;
        this.multicastPort = multicastPort ; 

    }
    
    public void startEngine (
    String filePath , String registryName , String registryAddress ,
            int registryPort  ,String multicastAddress  , int multicastPort ) 
            throws Exception{
        
        //get getregistry
        Registry registry = LocateRegistry.getRegistry(registryAddress,registryPort);
        //get sserver interface object
        SServerInterface pServer = (SServerInterface) registry.lookup
        ("//"+registryAddress+":"+registryPort+"/"+registryName);
        //join the group 
        mcjoin channel = new mcjoin(multicastPort,multicastAddress);
        //send join signal to the primary server
        pServer.join();
        //waiting for reciving a new message
        while(true){
            String message = channel.receive();
            //search for the song
            String song = ReadFromFile.readFromFile(message, filePath);
            if(song.equals("Song Not Found")){
                pServer.submitResault("Song Not Found");
            }
            else
                pServer.submitResault(song);
        }
        
    }
    
    @Override
    public void run () {
                try {
            this.startEngine (filePath , registryName , registryAddress , registryPort , multicastAddress , multicastPort) ;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }      
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import InputOutFiles.* ;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import multicast.mcsend;
import java.util.ArrayList;

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
        super() ;
        
        this.filePath = "TestFile.txt" ;
        this.multicastAddress = "228.5.6.7" ;
        this.multicastPort = 5001 ;
        this.registryName = "SearchEngine" ;
        this.registryAddress = "localhost" ;
        this.registryPort = 5000 ;
        this.startEngine(registryName, registryAddress, registryPort, this.registry );
        //open a multicast socket to connect with secondary servers
        this.channel = new mcsend(this.multicastPort, this.multicastAddress, 10);
    }
    
    
    private void startEngine (String registryName , String registryAddress ,
            int registryPort ,Registry registry) throws RemoteException{
        
        registry = LocateRegistry.createRegistry(registryPort) ;
        registry.rebind("//"+registryAddress+":"+registryPort+"/"+registryName, this);
    }
    @Override
    public String search(String songName) throws RemoteException {
        
        //define search result varible
        String songSearch = "" ;
        try {
            //search for the song in the specific file
            songSearch = ReadFromFile.readFromFile(songName , this.filePath );
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        if(songSearch.equals("Song Not Found")){
            try {
                // clear result array for new search
                response.clear();              
                //multicast all secondary servers the song's name
                this.channel.send(songName);
                //wait for responses from the secondary servers
                boolean SecondarySearchDone = false;
                while(!SecondarySearchDone){
                    //wait for get the result from SServers
                    Thread.sleep(500);
                    if(this.SServers == response.size()){
                        //all SServers replays
                        SecondarySearchDone = true;
                        songSearch = response.toString() ;}
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
    
}

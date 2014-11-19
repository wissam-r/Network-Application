/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengean;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import InputOutFiles.* ;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import multicast.mcsend;
import java.util.ArrayList;

/**
 *
 * @author CG
 */
public class PServer extends UnicastRemoteObject implements PServerInterface, SServerinterface{
    //multicast channel variable
    private mcsend channel;
    //secondary servers number increased on join
    private int SServers = 0;
    //array list of response from secondary servers
    private ArrayList<String> response = new ArrayList<>();
    
    public PServer () throws RemoteException, Exception {
        super() ;
        //open a multicast socket to connect with secondary servers
        this.channel = new mcsend(5001, "228.5.6.7", 1);
    }
    
    @Override
    public String search(String songName) throws RemoteException {
        String songSearch = "" ;
        try {
           songSearch = ReadFromFile.readFromFile(songName , "TestFile.txt");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        if(songSearch.equals("Song Not Found")){
            try {
                //multicast all secondary servers the song's name
                this.channel.send(songName);
                //wait for responses from the secondary servers
                boolean SecondarySearchDone = false;
                while(!SecondarySearchDone){
                    if(this.SServers == response.size()){
                        SecondarySearchDone = true;
                        songSearch = response.toString() ;}
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return songSearch ;
    }
    @Override
    public void join(){
        this.SServers += 1 ; 
    }
    @Override
    public void submitResault(String resault){
        this.response.add(resault);
    }
}

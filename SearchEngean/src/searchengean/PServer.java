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

/**
 *
 * @author CG
 */
public class PServer extends UnicastRemoteObject implements PServerInterface{
    private mcsend channel;
    public PServer () throws RemoteException, Exception {
        super() ;
        //TODO:open a tcp socket server to recieve from secondary servers
        //open a multicast socket to connect with secondary servers
        this.channel = new mcsend(5000, "228.5.6.7", 1);
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
                this.channel.send(songName);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return songSearch ;
    }
}

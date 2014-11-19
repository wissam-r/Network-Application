/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengean;

import InputOutFiles.ReadFromFile;
import multicast.mcjoin;
import java.io.*;
import java.net.Socket;
import java.rmi.registry.*;
/**
 *
 * @author eyadof
 */
public class SServer {
    
    public static void main (String[] args) throws Exception{
        //define registry port
        int rport = 5000;
        Registry registry = LocateRegistry.getRegistry("localhost",rport);
        //get sserver interface object
        SServerinterface pserver = (SServerinterface) registry.lookup("//localhost:5000/SearchEngean");
        //define multicast group and port
        int port = 5001;
        String group = "228.5.6.7";
        //join the group 
        mcjoin channel = new mcjoin(port,group);
        //send join signal to the primary server
        pserver.join();
        //waiting for reciving a new message
        while(true){
            String message = channel.receive();
            //search for the song
            String song = ReadFromFile.readFromFile(message, "songs.txt");
            if(song.equals("Song Not Found")){
                pserver.submitResault("Song Not Found");
            }
            else
                pserver.submitResault(song);
        }
        
    }
}

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

/**
 *
 * @author eyadof
 */
public class SServer {
    public static void main (String[] args) throws Exception{
        //open a tcp socket connection
        Socket tcpSocket = new Socket("localhost", 6789);
	DataOutputStream outToServer = new DataOutputStream(tcpSocket.getOutputStream());
        
        //define multicast group and port
        int port = 5000;
        String group = "228.5.6.7";
        //join the group 
        mcjoin channel = new mcjoin(port,group);
        //waiting for reciving a new message
        while(true){
            String message = channel.receive();
            //search for the song
            String song = ReadFromFile.readFromFile(message, "songs.txt");
            if(song.equals("Song Not Found")){
                outToServer.writeBytes("Song Not Found");
            }
            else
                outToServer.writeBytes(song);
        }
        
    }
}

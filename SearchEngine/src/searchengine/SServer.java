/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import multicast.mcjoin;
import java.io.*;
import java.net.Socket;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eyadof
 */
public class SServer extends Thread {

    //define file path and name
    private String filePath;
    //define registry name
    private String registryName;
    //define registry address
    private String registryAddress;
    //define registry port
    private int registryPort;
    //define multicast group
    private String multicastAddress;
    //define multicast port
    private int multicastPort;

    public SServer() {
        this("TestPath2", "SearchEngine", "localhost", 5000, "228.5.6.7", 5001);
    }

    public SServer(String filePath, String registryName, String registryAddress,
            int registryPort, String multicastAddress, int multicastPort
    ) {
        this.filePath = filePath;
        this.registryName = registryName;
        this.registryAddress = registryAddress;
        this.registryPort = registryPort;
        this.multicastAddress = multicastAddress;
        this.multicastPort = multicastPort;

    }

    public void startEngine (
            String filePath, String registryName, String registryAddress,
            int registryPort, String multicastAddress, int multicastPort)
            throws Exception {

        //get getregistry
        Registry registry = LocateRegistry.getRegistry(registryAddress, registryPort);
        //get sserver interface object
        SServerInterface pServer = (SServerInterface) registry.lookup("//" + registryAddress + ":" + registryPort + "/" + registryName);
        //join the group 
        mcjoin channel = new mcjoin(multicastPort, multicastAddress);
        //send join signal to the primary server
        pServer.join();
        //waiting for reciving a new message
        while (true) {
            //send song name
            String message = channel.receive();
            //send song specific path
            String songPath = channel.receive();
            //search for the song in the specific path
            String song = InputOutFiles.Reader.searchForSong(filePath, message, songPath);
            if (song.equals("")) {
                pServer.submitResault("");
            } else {
                pServer.submitResault(song);
            }
        }

    }

    @Override
    public void run() {
        try {        
            this.startEngine(filePath, registryName, registryAddress, registryPort, multicastAddress, multicastPort);
        } catch (Exception ex) {
             System.err.println(ex.getMessage());
        }
    }

}

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

/**
 *
 * @author CG
 */
public class PServer extends UnicastRemoteObject implements PServerInterface{
    
    public PServer () throws RemoteException {
        super() ;
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
        return  songSearch ;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author CG
 */
public interface PServerInterface extends Remote{
    public String search(String songName, String specificPath) throws RemoteException;
    public File getSong(String songPath) throws RemoteException ;
    public String getLyrics(String songPath) throws RemoteException ,IOException ;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengean;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author CG
 */
public interface PServerInterface extends Remote{
    public String search(String songName) throws RemoteException;
}

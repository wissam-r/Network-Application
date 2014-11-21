/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author eyadof
 */
public interface SServerInterface extends Remote{
    public void join() throws RemoteException;
    public void unJoin() throws RemoteException;
    public void submitResault(String res) throws RemoteException;
}

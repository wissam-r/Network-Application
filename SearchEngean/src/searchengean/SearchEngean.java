/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengean;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author CG
 */
public class SearchEngean {
    
    
    public static void main(String[] args) {
            
        try {
            PServer pServer = new PServer() ;
            int port = 5000 ;
            Registry registry = LocateRegistry.createRegistry(port) ;
            registry.rebind("//localhost:5000/SearchEngean", pServer);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}

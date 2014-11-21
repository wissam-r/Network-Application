/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicast;
import java.net.*;
/**
 *
 * @author eyadof
 */
public class mcsend {
    private int port,ttl;
    private String group;
    private MulticastSocket mcs;
    
    public mcsend(int port, String group, int ttl) throws Exception {
        this.port = port;
        this.group = group;
        this.ttl = ttl;
        this.mcs = new MulticastSocket();
    }
    
    public void send(String data) throws Exception {
        byte buf[] = data.getBytes();
        DatagramPacket packet = new DatagramPacket(buf,buf.length,
                InetAddress.getByName(group), port);
        mcs.send(packet,(byte)ttl);
    }
    
    public void disconnect(){
        mcs.close();
    }
}

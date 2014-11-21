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
public class mcjoin {
    private int port;
    private String group;
    private MulticastSocket mcs;
    
    public mcjoin(int port, String group) throws Exception {
        
        this.port  = port;
        this.group = group;
        this.mcs   = new MulticastSocket(this.port);
        mcs.joinGroup(InetAddress.getByName(group));
        
    }
    
    public String receive() throws Exception {
        byte buf[] = new byte[1024];
        
        DatagramPacket packet = new DatagramPacket(buf,buf.length);
        this.mcs.receive(packet);
        return new String(packet.getData()).trim();
    }
    
    public void disconnect() throws Exception {
        mcs.leaveGroup(InetAddress.getByName(this.group));
        mcs.close();
    }
}

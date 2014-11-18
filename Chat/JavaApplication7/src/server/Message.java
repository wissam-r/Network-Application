/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

/**
 *
 * @author falcon
 */
public class Message {
    
    String senderId , receverId ;
    String msg  ;
    
    public Message (String senderId , String receverId , String msg){
        this.receverId = receverId ;
        this.senderId = senderId;
        this.msg = msg ;
    }
    
}

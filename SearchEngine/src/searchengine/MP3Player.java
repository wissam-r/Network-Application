/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author CG
 */
public class MP3Player extends Thread{
   

    Player playMP3;
    public MP3Player(String songPath) throws Exception{
        this.playMP3 = new Player(new FileInputStream(songPath));
    }
    public void run()
                {
                    try {
                    playMP3.play();
                    }
                    catch(Exception ex){
                    ex.printStackTrace();}
                }
                    
    
}

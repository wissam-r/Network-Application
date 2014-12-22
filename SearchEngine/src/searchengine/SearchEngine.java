/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package searchengine;

import java.io.File;
import java.io.FileInputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;


/**
 *
 * @author CG
 */
public class SearchEngine {
    
    
    public static void main(String[] args) {
            
        
        try {
                FileInputStream fis = new FileInputStream("123/123.mp3");
                Player playMP3 = new Player(fis);
                playMP3.play();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}

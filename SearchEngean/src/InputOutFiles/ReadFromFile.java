/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutFiles;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author CG
 */
public class ReadFromFile {

    public static String readFromFile(String songName, String fileName) throws IOException, ClassNotFoundException {

        File file = new File(fileName);        
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        String searchResult = "" ;
        String songNameFromFile = null;
        boolean found = false ;
        try {
            while (true) {
                songNameFromFile = bufferReader.readLine() ;
                if (songNameFromFile == null)
                    break ;
                if (songName.equals(songNameFromFile)) {
                    searchResult += file.getPath() + "  " + songName +"\n";
                    found = true ;
                }
            }
            if (found)
                return searchResult ;
             return "Song Not Found";
        } catch (EOFException ex) { 
            return "Song Not Found";
        }

    }

    public static void main(String[] args) {

        try {
            System.out.println(readFromFile("i will always love u", "TestFile.txt"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

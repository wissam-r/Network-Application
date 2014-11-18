/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutFiles;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author CG
 */
public class ReadFromFile {

    public static String readFromFile(String songName, String fileName) throws IOException, ClassNotFoundException {

        ObjectInputStream readObjectS;
        File file = new File(fileName);
        readObjectS = new ObjectInputStream(new FileInputStream(file));
//                return (String) readObjectS.readObject() ;

        String songNameFromFile = null;
        try {
            while (true) {

                songNameFromFile = (String) readObjectS.readObject();
                if (songName.equals(songNameFromFile)) {
                    return file.getPath() + "  " + songName;
                }

            }
        } catch (EOFException ex) {
            readObjectS.close();
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

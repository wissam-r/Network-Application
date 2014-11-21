/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author CG
 */
public class WriteToFile {

    public static void writeToFile(String songName, String fileName) throws IOException {
        
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
//        ObjectOutputStream writeObject;
//        writeObject = new ObjectOutputStream(new FileOutputStream(file));
//        writeObject.writeObject(songName);
//        writeObject.flush();
//        writeObject.close();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        String line = "";
        String br = "" ;
        while ((br = bufferReader.readLine())!= null)
                line += br+"\n";
        line += songName ;
        bufferReader.close();
        System.out.println(line);
        FileWriter fileWriter = new FileWriter(file );
        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
        bufferWriter.write(line);
        bufferWriter.flush();
        bufferWriter.close();
    }

    public static void main(String[] args) {
        try {
            writeToFile("i will always love u", "TestFile.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

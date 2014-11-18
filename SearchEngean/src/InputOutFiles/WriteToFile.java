/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author CG
 */
public class WriteToFile {

    public static void writeToFile(String songName, String fileName) throws IOException {
        ObjectOutputStream writeObject;
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
            return;
        }
        writeObject = new ObjectOutputStream(new FileOutputStream(file));
        writeObject.writeObject(songName);
        writeObject.flush();
        writeObject.close();
    }

    public static void main(String[] args) {
        try {
            writeToFile("i will always love u", "TestFile.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

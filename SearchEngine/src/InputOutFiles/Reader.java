/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InputOutFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

/**
 *
 * @author CG
 */
public class Reader {
    
    public static String readFromFile(String songName, String fileName, String songPath) throws IOException, ClassNotFoundException, IllegalArgumentException {

        //create file object
        File file = new File(fileName);
        //define search result varible
        String searchResult = "";
        //define scanner to access file content
        Scanner songsFile = new Scanner(file);
        //set spiceal delimiter for our system files
        songsFile.useDelimiter("\\sENDEND\\s");
        while (true) {
            if (songsFile.hasNext()) {
                //reading the song name
                String songNameFile = songsFile.next();
                if (songsFile.hasNext()) {
                    System.out.println("kokok");
                    //reading song path
                    String songPathFile = songsFile.next();
                    //checking if the song is found and the specific path is match
                    if ((songName.equals(songNameFile)) && (songPathFile.startsWith(songPath))) {
                        searchResult += "Song Name : " + songName + "\n" +
                                "Found in : " + file.getAbsolutePath() + "\n" +
                                "Song Path File : " + songPathFile + "\n";
                                
                    }
                } else {
                    throw new IllegalArgumentException("no song path");
                }
            } else {
                break;
            }
        }
        return searchResult;
    }
    
    public static String searchForSong(String filePath , String songName , String songPath)throws Exception{
        //define result varible
        String result = "" ;
        //create file object
        File file = new File(filePath);
        //checking if the given path is a directory
        if (!file.isDirectory())
            throw new IllegalArgumentException("no path here : "+ filePath);
        else if (file.list().length==0)
            throw new IllegalArgumentException("no file in the path : "+ filePath);
        else {
            //for each file in the path check if there is a match song for our given path
            for (String fileo : file.list()) {
                if (new File(filePath+"/"+fileo).isFile())
                    //add the result from every file to the main result
                    result += readFromFile(songName,filePath+"/"+ fileo , songPath) ;
            }
        }       
        return result ;
    }
    
    public static String getLyricsFromFile (String songPath) throws FileNotFoundException{
        //open file
        File file = new File(songPath);
        //define result varible
        String result = "" ;
        //using scanner to navigate the file
        Scanner songsFile = new Scanner(file);
        //navigate the file
        while(songsFile.hasNextLine()){
            result += songsFile.nextLine() + "\n" ;
        }
        return result ;
        
    }
    public static void main(String[] args) {

        try {
            System.out.println(searchForSong("TestPath", "i_will_always_love_u", "")) ;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package ru.coderiders.teamtask.rename;

import java.io.File;
import java.io.IOException;

public class RenameClass {
    public static String getNewFileName(String filename) throws IOException {
        File aFile = new File(filename);
        int fileNo = 0;
        String newFileName = "";
        if (aFile.exists() && !aFile.isDirectory()) {


            while(aFile.exists()){
                fileNo++;
                aFile = new File(filename + "(" + fileNo + ").mp3" );
                newFileName = filename + "(" + fileNo + ").mp3";
            }


        } else if (!aFile.exists()) {
            aFile.createNewFile();
            newFileName = filename;
        }
        return newFileName;
    }
}

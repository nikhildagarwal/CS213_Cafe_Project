package com.example.cs213_cafe_project.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class FileCreate {

    public static final String FILENAME = "orderSummary.txt";

    private void createFile() {
        try {
            File file = new File(FILENAME);
            if (file.createNewFile()) {} else {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createEmptyFile(){
        createFile();
        String fileName = FILENAME;
        String lineToWrite = "Order Summary:\n\nThere are currently no orders.\n\nHave a nice day!";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(lineToWrite);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new FileCreate().createEmptyFile();
    }

}

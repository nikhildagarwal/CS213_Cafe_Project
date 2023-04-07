/**
 * Package for all data java files (structures)
 */
package com.example.cs213_cafe_project.data;

import javafx.collections.ObservableList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * Class to create a txt file with given information.
 * The information provided to the methods is a list of all active orders.
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class FileCreate {

    /**
     * The set filepath that we want to write our data to.
     */
    public static final String FILENAME = "orderSummary.txt";

    /**
     * Calls createFile to create a text file and then writes the following message to the file:
     *      Order Summary:
     *      There are currently no orders.
     *      Have a nice day!
     */
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

    /**
     * Calls createFile to create a text file and then writes the following message to the file:
     *      *      Order Summary:
     *      *      ~~ ALL ORDER DATA ~~
     *      *      Have a nice day!
     * @param orders list of orders
     */
    public void createFilledFile(ObservableList<Order> orders){
        createFile();
        String fileName = FILENAME;
        String lineToWrite = "Order Summary:";
        for(int i = 0;i < orders.size();i++){
            lineToWrite += ("\n\nOrder Number: "+ orders.get(i).getOrderNumber() + "\n"+orders.get(i));
        }
        lineToWrite += "\n\nHave a nice day!";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(lineToWrite);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * private helper method to create a txt files.
     * After this executes the above methods will determine what to the write to the file.
     */
    private void createFile() {
        try {
            File file = new File(FILENAME);
            if (file.createNewFile()) {} else {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

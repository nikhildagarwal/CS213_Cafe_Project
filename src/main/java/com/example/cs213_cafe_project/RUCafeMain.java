/**
 * Project package
 */
package com.example.cs213_cafe_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The launch class for RUCafe Project
 * stages the init fxml file for the project
 * @author Hyeon Oh, Nikhil Agarwal
 */
public class RUCafeMain extends Application {

    /**
     * start method to set the stage and scene for init fxml file.
     * @param stage Creates javaFX GUI stage
     * @throws IOException throws any input output error when setting stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RUCafeMain.class.getResource("StoreFrontView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Project 4 - RU Cafe");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * launch method.
     * Press run to run program.
     * @param args command line input (NOT USED)
     */
    public static void main(String[] args) {
        launch();
    }
}
package com.example.cs213_cafe_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private int value = 10;



    /**
     * When the image button is clicked, a new window(stage) will be displayed.
     * The scene graph associated with the window is orderingCoffee.fxml
     * When the fxml file is loading, an instance of OrderCoffeeController will be created
     * To get the reference to the instance of the controller, use the getController()
     * method.
     */
    @FXML
    protected void displayCoffeeView() {
        Stage coffeeView = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderingCoffee.fxml"));

            Scene scene = new Scene(loader.load(), 600, 600);
            coffeeView.setScene(scene);
            coffeeView.show();
            OrderCoffeeController coffeeViewController = loader.getController();
            /*
              The statement below is to pass the reference of the MainController object
              to the OrderCoffeeController object so the OrderCoffeeController can call the
              public methods in the MainController.
             */
            coffeeViewController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading orderingCoffee.fxml.");
            alert.setContentText("Couldn't load orderingCoffee.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayDonutView() {
        Stage donutView = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donutsView.fxml"));

            Scene scene = new Scene(loader.load(), 600, 600);
            donutView.setScene(scene);
            donutView.show();
            OrderDonutsController donutViewController = loader.getController();
            /*
              The statement below is to pass the reference of the MainController object
              to the OrderDonutsController object so the OrderDonutsController can call the
              public methods in the MainController.
             */
            donutViewController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading donutsView.fxml.");
            alert.setContentText("Couldn't load donutsView.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * The controller can use this getter method to read the private data.
     * @return
     */
    public int getValue() {
        return value;
    }
}
/**
 * Project package
 */
package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.data.BasketItem;
import com.example.cs213_cafe_project.data.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashSet;

/**
 * This class is the controller for StoreFrontView.fxml
 * Creates and sets the stages for all the other views in RUCafe Project
 * Contains all the universal data objects that are needed to successfully run the project.
 * @author Hyeon Oh, Nikhil Agarwal
 */
public class MainController {

    /**
     * data storage for Donut Basket Items
     */
    private ObservableList<BasketItem> donutBasketItems = FXCollections.observableArrayList();

    /**
     * data storage for All basket items
     */
    private ObservableList<BasketItem> fullBasket = FXCollections.observableArrayList();

    /**
     * data storage for list of all orders
     */
    private ObservableList<Order> listOfOrders = FXCollections.observableArrayList();

    /**
     * data storage for set of all active orderNumbers
     */
    private HashSet<Integer> orderNumbers = new HashSet<>();

    /**
     * getter method for reference of data in listOfOrders
     * @return ObservableList<Order> listOfOrders
     */
    public ObservableList<Order> getListOfOrders(){
        return listOfOrders;
    }

    /**
     * getter method for reference of data in orderNumbers
     * @return HashSet<Integer> orderNumbers
     */
    public HashSet<Integer> getOrderNumbers(){
        return orderNumbers;
    }

    /**
     * getter method for reference of data in donutBasketItems
     * @return ObservableList<BasketItem> donutBasketItems
     */
    public ObservableList<BasketItem> getDonutBasketItems(){
        return donutBasketItems;
    }

    /**
     * getter method for reference of data in fullBasket
     * @return ObservableList<BasketItem> fullBasket
     */
    public ObservableList<BasketItem> getFullBasket(){
        return fullBasket;
    }

    /**
     * When the image button is clicked, a new window(stage) will be displayed.
     * The scene graph associated with the window is orderingCoffee.fxml
     * When the fxml file is loading, an instance of OrderCoffeeController will be created
     * To get the reference to the instance of the controller, use the getController()
     * method.
     */
    @FXML
    public void displayCoffeeView() {
        Stage coffeeView = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderingCoffee.fxml"));
            Scene scene = new Scene(loader.load(), 600, 600);
            coffeeView.setScene(scene);
            coffeeView.show();
            OrderCoffeeController coffeeViewController = loader.getController();
            coffeeViewController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading orderingCoffee.fxml.");
            alert.setContentText("Couldn't load orderingCoffee.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * When the image button is clicked, a new window(stage) will be displayed.
     * The scene graph associated with the window is donutsView.fxml
     * When the fxml file is loading, an instance of OrderDonutsController will be created
     * To get the reference to the instance of the controller, use the getController()
     * method.
     */
    @FXML
    public void displayDonutView() {
        Stage donutView = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donutsView.fxml"));
            Scene scene = new Scene(loader.load(), 600, 600);
            donutView.setScene(scene);
            donutView.show();
            OrderDonutsController donutViewController = loader.getController();
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
     * When the image button is clicked, a new window(stage) will be displayed.
     * The scene graph associated with the window is orderBasket.fxml
     * When the fxml file is loading, an instance of OrderBasketController will be created
     * To get the reference to the instance of the controller, use the getController()
     * method.
     */
    @FXML
    public void displayBasketView() {
        Stage basketView = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderBasket.fxml"));
            Scene scene = new Scene(loader.load(), 600, 600);
            basketView.setScene(scene);
            basketView.show();
            OrderBasketController basketController = loader.getController();
            basketController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading orderBasket.fxml.");
            alert.setContentText("Couldn't load orderBasket.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * When the image button is clicked, a new window(stage) will be displayed.
     * The scene graph associated with the window is storeOrders.fxml
     * When the fxml file is loading, an instance of StoreOrderController will be created
     * To get the reference to the instance of the controller, use the getController()
     * method.
     */
    @FXML
    public void displayStoreView() {
        Stage storeOrderView = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("storeOrders.fxml"));
            Scene scene = new Scene(loader.load(), 600, 600);
            storeOrderView.setScene(scene);
            storeOrderView.show();
            StoreOrderController storeOrderController = loader.getController();
            storeOrderController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading storeOrders.fxml.");
            alert.setContentText("Couldn't load storeOrders.fxml.");
            alert.showAndWait();
        }
    }
}
/**
 * Project Package
 */
package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.data.FileCreate;
import com.example.cs213_cafe_project.data.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.HashSet;

/**
 * Controller class for storeOrders.fxml
 * Does all the event handling and the action handling for the storeOrders view.
 * Sends and receives data with the mainController.
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class StoreOrderController {

    /**
     * Variable to hold the reference for an instance of MainController()
     */
    private MainController mainController;

    /**
     * Initializes an instance of the mainController and sets mainController to that value
     * @param controller The controller class object
     */
    public void setMainController (MainController controller){
        mainController = controller;
    }

    /**
     * init method that runs as soon as the .fxml file is set and staged
     *      - set all monetary textFields to null
     */
    public void initialize(){
        cancelButton.disableProperty().set(true);
        orderTotal.disableProperty().set(true);
    }

    /**
     * Reference to ComboBox orderNumberList
     */
    @FXML
    private ComboBox<String> orderNumberList;

    /**
     * Reference to Button cancelButton
     */
    @FXML
    private Button cancelButton;

    /**
     * Reference to TextField orderTotal
     */
    @FXML
    private TextField orderTotal;

    /**
     * Reference to ListView<Order> orderListView
     */
    @FXML
    private ListView<Order> orderListView;

    /**
     * Event handler for export button clicked.
     * Checks to see if listOfOrders is empty
     *      if empty will run code to create file summary for empty orders
     *      if not empty will run code to create file summary for all orders
     */
    @FXML
    public void exportClicked(){
        ObservableList<Order> listOfOrders = mainController.getListOfOrders();
        if(listOfOrders.isEmpty()){
            new FileCreate().createEmptyFile();
            orderListView.setItems(FXCollections.observableArrayList(new Order(Order.EMPTYFILE)));
        }else{
            new FileCreate().createFilledFile(listOfOrders);
            orderListView.setItems(FXCollections.observableArrayList(new Order(Order.EMPTYFILE)));
        }
    }

    /**
     * Makes the list of orderNumbers that can then be selected in the orderNumberList comboBox.
     * This list is always made once the comboBox drop down is clicked.
     */
    @FXML
    public void makeList(){
        HashSet<Integer> set = mainController.getOrderNumbers();
        if(set.isEmpty()){
            orderListView.setItems(FXCollections.observableArrayList(new Order(Order.EMPTY)));
            return;
        }
        ObservableList<String> orderNumbers = FXCollections.observableArrayList("Orders (Order Number):");
        for(Integer integer : set){
            orderNumbers.add(Integer.toString(integer));
        }
        orderNumberList.setItems(orderNumbers);
    }

    /**
     * Event Handler for action on the orderNumberList comboBox.
     * Sets the listView to the selected order Number and the total box to the corresponding price of the order.
     */
    @FXML
    public void selectedOrder(){
        int selectedOrderNumber;
        try{
            selectedOrderNumber = Integer.parseInt(orderNumberList.valueProperty().getValue());
        }catch (Exception e){
            orderListView.setItems(null);
            orderTotal.setText("");
            cancelButton.disableProperty().set(true);
            return;
        }
        ObservableList<Order> listOfOrders = mainController.getListOfOrders();
        for(int i = 0;i<listOfOrders.size();i++){
            Order currOrder = listOfOrders.get(i);
            if(currOrder.getOrderNumber() == selectedOrderNumber){
                orderListView.setItems(FXCollections.observableArrayList(currOrder));
                cancelButton.disableProperty().set(false);
                orderTotal.setText("$"+currOrder.getOrderPrice());
                orderTotal.disableProperty().set(false);
                i = listOfOrders.size();
            }
        }
    }

    /**
     * Cancels the selected order and removes the order from the ObservableList<Order> of orders.
     * Resets the view to the init status.
     */
    @FXML
    public void cancelSelectedOrder(){
        ObservableList<Order> listOfOrders = mainController.getListOfOrders();
        int selectedOrderNumber;
        try{
            selectedOrderNumber = Integer.parseInt(orderNumberList.valueProperty().getValue());
        }catch (Exception e){
            return;
        }
        for(int i =0;i<listOfOrders.size();i++){
            if(listOfOrders.get(i).getOrderNumber() == selectedOrderNumber){
                listOfOrders.remove(i);
                i = listOfOrders.size();
            }
        }
        HashSet<Integer> set = mainController.getOrderNumbers();
        set.remove(selectedOrderNumber);
        ObservableList<String> orderNumbers = FXCollections.observableArrayList("Orders (Order Number):");
        for(Integer integer : set){
            orderNumbers.add(Integer.toString(integer));
        }
        orderNumberList.setItems(orderNumbers);
        orderTotal.setText("");
        orderTotal.focusTraversableProperty().set(false);
        orderNumberList.setValue("Orders (Order Number):");
        cancelButton.disableProperty().set(true);
        orderListView.setItems(null);
    }
}

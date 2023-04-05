package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.data.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;

public class StoreOrderController {

    private MainController mainController;

    //Get the reference to the MainController object
    public void setMainController (MainController controller){
        mainController = controller;
    }

    public void initialize(){
        cancelButton.disableProperty().set(true);
        orderTotal.disableProperty().set(true);
    }

    @FXML
    private ComboBox orderNumberList;
    @FXML
    private Button cancelButton;
    @FXML
    private Button exportButton;
    @FXML
    private TextField orderTotal;
    @FXML
    private ListView<Order> orderListView;

    @FXML
    public void makeList(){
        HashSet<Integer> set = mainController.getOrderNumbers();
        if(set.isEmpty()){
            orderListView.setItems(FXCollections.observableArrayList(new Order(Order.EMPTY)));
            orderNumberList.disableProperty().set(true);
            return;
        }
        ObservableList<Integer> orderNumbers = FXCollections.observableArrayList();
        for(Integer integer : set){
            orderNumbers.add(integer);
        }
        orderNumberList.setItems(orderNumbers);
    }

    @FXML
    public void selectedOrder(){
        int selectedOrderNumber;
        try{
            selectedOrderNumber = (int) orderNumberList.valueProperty().getValue();
        }catch (Exception e){
            return;
        }
        ObservableList<Order> listOfOrders = mainController.getListOfOrders();
        for(int i = 0;i<listOfOrders.size();i++){
            Order currOrder = listOfOrders.get(i);
            if(currOrder.getOrderNumber() == selectedOrderNumber){
                orderListView.setItems(FXCollections.observableArrayList(currOrder));
                cancelButton.disableProperty().set(false);
                orderTotal.setText(currOrder.getOrderPrice());
                orderTotal.disableProperty().set(false);
                i = listOfOrders.size();
            }
        }
    }
}

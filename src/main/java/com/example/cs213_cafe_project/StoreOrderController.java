package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.data.FileCreate;
import com.example.cs213_cafe_project.data.Order;
import com.example.cs213_cafe_project.donut.YeastDonut;
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
    private ComboBox<String> orderNumberList;
    @FXML
    private Button cancelButton;
    @FXML
    private Button exportButton;
    @FXML
    private TextField orderTotal;
    @FXML
    private ListView<Order> orderListView;

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
        System.out.println(set);
    }
}

package com.example.cs213_cafe_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class OrderCoffeeController {
    private MainController mainController;

    private ObservableList<String> coffeeSizeList;
    private ObservableList<String> coffeeAmount;
    @FXML
    private ComboBox<String> coffeeSize;
    @FXML
    private ComboBox<String> numCoffee;

    //Get the reference to the MainController object
    public void setMainController (MainController controller){
        mainController = controller;
    }

    public void initialize()
    {
        coffeeSizeList = FXCollections.observableArrayList("Tall", "Short", "Grande", "Venti");
        coffeeSize.setItems(coffeeSizeList);
        coffeeAmount = FXCollections.observableArrayList("1","2","3","4","5");
        numCoffee.setItems(coffeeAmount);
        //donutFlavorList = FXCollections.observableArrayList("test", "test2");
        //donutFlavorType.setItems(donutFlavorList);

    }
}

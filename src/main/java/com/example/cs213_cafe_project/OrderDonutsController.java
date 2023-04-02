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
public class OrderDonutsController {

    private MainController mainController;
    private ObservableList<String> donutList;
    private ObservableList<String> donutFlavorList;

    private ObservableList<String> donutAmount;
    @FXML
    private ComboBox<String> numDonuts;


    @FXML
    private ComboBox<String> donutType;

    @FXML
    private ListView<String> donutFlavorType;

    //Get the reference to the MainController object
    public void setMainController (MainController controller){
        mainController = controller;
    }


    public void initialize()
    {
        donutList = FXCollections.observableArrayList("yeast donuts", "cake donuts", "donut holes");
        donutType.setItems(donutList);
        donutAmount = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
        numDonuts.setItems(donutAmount);
        //donutFlavorList = FXCollections.observableArrayList("test", "test2");
        //donutFlavorType.setItems(donutFlavorList);

    }





}

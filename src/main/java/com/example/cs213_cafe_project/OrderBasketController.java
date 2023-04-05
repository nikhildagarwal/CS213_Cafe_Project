package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.data.BasketItem;
import com.example.cs213_cafe_project.data.MenuItem;
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
public class OrderBasketController {

    private MainController mainController;

    @FXML
    private ListView<BasketItem> basketListView;
    @FXML
    private TextField subTotal;
    @FXML
    private TextField tax;
    @FXML
    private TextField total;
    @FXML
    private Button removeButton;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button loadBasketButton;

    //Get the reference to the MainController object
    public void setMainController (MainController controller){
        mainController = controller;
    }

    public void initialize(){
        subTotal.setText("$0.00");
        tax.setText("$0.00");
        total.setText("$0.00");
        subTotal.focusTraversableProperty().set(false);
        tax.focusTraversableProperty().set(false);
        total.focusTraversableProperty().set(false);
        subTotal.disableProperty().set(true);
        tax.disableProperty().set(true);
        total.disableProperty().set(true);
        removeButton.disableProperty().set(true);
        placeOrderButton.disableProperty().set(true);
        basketListView.disableProperty().set(true);
    }

    @FXML
    public void setLoadBasket(){
        loadBasketButton.disableProperty().set(true);
        ObservableList<BasketItem> fullBasket = mainController.getFullBasket();
        if(fullBasket.isEmpty()){
            basketListView.setItems(FXCollections.observableArrayList(new BasketItem()));
        }else{
            basketListView.setItems(fullBasket);
            subTotal.disableProperty().set(false);
            tax.disableProperty().set(false);
            total.disableProperty().set(false);
            subTotal.focusTraversableProperty().set(false);
            tax.focusTraversableProperty().set(false);
            total.focusTraversableProperty().set(false);
            placeOrderButton.disableProperty().set(false);
            placeOrderButton.focusTraversableProperty().set(false);
            basketListView.disableProperty().set(false);
            setBasketPrice(fullBasket);
        }
    }

    @FXML
    public void listViewSelected(){
        BasketItem selected = basketListView.getSelectionModel().getSelectedItem();
        if(selected ==null){
            return;
        }
        removeButton.disableProperty().set(false);
    }

    @FXML
    public void removeSelectedItem(){
        BasketItem selected = basketListView.getSelectionModel().getSelectedItem();
        ObservableList<BasketItem> fullBasket = mainController.getFullBasket();
        fullBasket.remove(selected);
        setBasketPrice(fullBasket);
        basketListView.setItems(fullBasket);
        if(fullBasket.isEmpty()){
            basketListView.setItems(FXCollections.observableArrayList(new BasketItem()));
            placeOrderButton.disableProperty().set(true);
            removeButton.disableProperty().set(true);
            basketListView.disableProperty().set(true);
        }
    }

    private void setBasketPrice(ObservableList<BasketItem> basket){
        double subTotalAmount = 0.0;
        for(int i = 0;i< basket.size();i++){
            MenuItem currMenuItem = basket.get(i).getMenuItem();
            subTotalAmount += currMenuItem.itemPrice();
        }
        subTotalAmount = Math.round(subTotalAmount * 100.0) / 100.0;
        subTotal.setText(format(subTotalAmount));
        double taxAmount = subTotalAmount * 0.06625;
        taxAmount = Math.round(taxAmount * 100.0) / 100.0;
        tax.setText(format(taxAmount));
        double totalAmount = subTotalAmount + taxAmount;
        totalAmount = Math.round(totalAmount * 100.0) / 100.0;
        total.setText(format(totalAmount));
    }

    private String format(double amount){
        String amountString = Double.toString(amount);
        String[] splitter = amountString.split("\\.");
        if(splitter[1].length()<2){
            amountString += "0";
        }
        return "$"+amountString;
    }

}

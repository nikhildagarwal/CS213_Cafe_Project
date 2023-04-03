package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.data.*;
import com.example.cs213_cafe_project.data.MenuItem;
import com.example.cs213_cafe_project.donut.*;
import com.example.cs213_cafe_project.donut.flavors.CakeFlavor;
import com.example.cs213_cafe_project.donut.flavors.HoleFlavor;
import com.example.cs213_cafe_project.donut.flavors.YeastFlavor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;

public class OrderDonutsController {

    private MainController mainController;
    @FXML
    private ImageView donutImage;
    private Image yeastDonutImage = new Image("file:src/main/resources/com/example/cs213_cafe_project/yeast donut.jpg");
    private Image cakeDonutImage = new Image("file:src/main/resources/com/example/cs213_cafe_project/cake donut.jpg");
    private Image donutHoleImage = new Image("file:src/main/resources/com/example/cs213_cafe_project/donut holes.jpg");

    private ObservableList<String> donutList;
    private ObservableList<String> donutFlavorList;
    private ObservableList<String> donutAmount;
    private ObservableList<BasketItem> basketItems = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> numDonuts;
    @FXML
    private ComboBox<String> donutType;
    @FXML
    private ListView<String> donutFlavorType;
    @FXML
    private ListView<BasketItem> basketItemsListView;

    //Get the reference to the MainController object
    public void setMainController (MainController controller){
        mainController = controller;
    }


    public void initialize()
    {
        donutList = FXCollections.observableArrayList("Yeast Donuts", "Cake Donuts", "Donut Holes");
        donutType.setItems(donutList);
        donutAmount = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
        numDonuts.setItems(donutAmount);

    }

    //donutFlavorList = FXCollections.observableArrayList("test", "test2");
    //donutFlavorType.setItems(donutFlavorList);
    @FXML
    public void displaySelected(ActionEvent event) {
        String selected = donutType.getSelectionModel().getSelectedItem();
        if(selected.equals("Cake Donuts"))
        {
            donutFlavorList = FXCollections.observableArrayList("Plain Vanilla", "Chocolate Cake", "Strawberry Short-Cake");
            donutFlavorType.setItems(donutFlavorList);
            donutImage.setImage(cakeDonutImage);
        }
        else if(selected.equals("Yeast Donuts"))
        {
            donutFlavorList = FXCollections.observableArrayList("Classic", "Glazed", "Jelly", "Cinnamon Sugar", "Boston Cream", "Powdered Sugar");
            donutFlavorType.setItems(donutFlavorList);
            donutImage.setImage(yeastDonutImage);
        }
        else if(selected.equals("Donut Holes"))
        {
            donutFlavorList = FXCollections.observableArrayList("Glazed", "Chocolate", "Jelly");
            donutFlavorType.setItems(donutFlavorList);
            donutImage.setImage(donutHoleImage);
        }
        reset();
        setSelectionPrice();
    }

    @FXML
    private Button removeButton;
    @FXML
    private Button addToBasketButton;
    @FXML
    private TextField selectionTotal;
    @FXML
    private TextField basketTotal;

    @FXML
    public void removeButtonClicked(){
        removeButton.disableProperty().set(true);
        BasketItem selectedItem = basketItemsListView.getSelectionModel().getSelectedItem();
        basketItems.remove(selectedItem);
        setBasketPrice();
    }

    @FXML
    private void setBasketPrice(){
        double total = 0;
        for(int i =0;i<basketItems.size();i++){
            BasketItem currItem = basketItems.get(i);
            total += (currItem.getMenuItem().itemPrice() * currItem.getQuantity());
        }
        String money = "$"+ (Math.round(total * 100.0) / 100.0);
        basketTotal.setText(format(money));
        basketTotal.focusTraversableProperty().set(false);
    }

    @FXML
    public void addToBasket(){
        String dType = donutType.valueProperty().getValue();
        int quantity = Integer.parseInt(numDonuts.valueProperty().getValue());
        String dfType = donutFlavorType.getSelectionModel().getSelectedItem();
        switch(dType){
            case "Yeast Donuts":
                YeastFlavor yeastFlavor = getYeastFlavor(dfType);
                YeastDonut yeastDonut = new YeastDonut(yeastFlavor);
                BasketItem basketItemYeast = new BasketItem(yeastDonut,quantity);
                basketItems.add(basketItemYeast);
                break;
            case "Cake Donuts":
                CakeFlavor cakeFlavor = getCakeFlavor(dfType);
                CakeDonut cakeDonut = new CakeDonut(cakeFlavor);
                BasketItem basketItemCake = new BasketItem(cakeDonut,quantity);
                basketItems.add(basketItemCake);
                break;
            case "Donut Holes":
                HoleFlavor holeFlavor = getHoleFlavor(dfType);
                DonutHole donutHole = new DonutHole(holeFlavor);
                BasketItem basketItemHole = new BasketItem(donutHole,quantity);
                basketItems.add(basketItemHole);
                break;
        }
        reset();
        donutType.setValue("Yeast Donuts");
        basketItemsListView.setItems(basketItems);
        setBasketPrice();
    }

    @FXML
    public void setSelectionPrice(){
        String dType = donutType.valueProperty().getValue();
        enableAmountBox();
        String amount = numDonuts.valueProperty().getValue();
        if(amount==null){
            return;
        }
        double quantity = Double.parseDouble(amount);
        String dfType = donutFlavorType.getSelectionModel().getSelectedItem();
        if(dfType==null){
            return;
        }
        enableSubmitToBasketBox();
        priceByType(dType,quantity,dfType);
    }

    @FXML
    private void priceByType(String donutType, double quantity, String donutFlavorType){
        switch(donutType){
            case "Yeast Donuts":
                YeastFlavor yeastFlavor = getYeastFlavor(donutFlavorType);
                YeastDonut yeastDonut = new YeastDonut(yeastFlavor);
                selectionTotal.setText(format("$"+ (Math.round(yeastDonut.itemPrice() * quantity * 100.0) / 100.0)));
                break;
            case "Cake Donuts":
                CakeFlavor cakeFlavor = getCakeFlavor(donutFlavorType);
                CakeDonut cakeDonut = new CakeDonut(cakeFlavor);
                selectionTotal.setText(format("$"+ (Math.round(cakeDonut.itemPrice() * quantity * 100.0) / 100.0)));
                break;
            case "Donut Holes":
                HoleFlavor holeFlavor = getHoleFlavor(donutFlavorType);
                DonutHole donutHole = new DonutHole(holeFlavor);
                selectionTotal.setText(format("$"+ (Math.round(donutHole.itemPrice() * quantity * 100.0) / 100.0)));
                break;
        }
    }

    @FXML
    private void enableSubmitToBasketBox(){
        addToBasketButton.disableProperty().set(false);
    }

    @FXML
    private void enableAmountBox(){
        numDonuts.disableProperty().set(false);
    }

    @FXML
    private void enableRemoveBox(){
        removeButton.disableProperty().set(false);
    }

    @FXML
    private void reset(){
        addToBasketButton.disableProperty().set(true);
        selectionTotal.setText("$0.00");
    }

    private HoleFlavor getHoleFlavor(String flavorType){
        switch(flavorType){
            case "Glazed": return HoleFlavor.GLAZED;
            case "Chocolate": return HoleFlavor.CHOCOLATE;
            case "Jelly": return HoleFlavor.JELLY;
        }
        return null;
    }

    private YeastFlavor getYeastFlavor(String flavorType){
        switch(flavorType){
            case "Classic": return YeastFlavor.PLAIN;
            case "Glazed": return YeastFlavor.GLAZED;
            case "Jelly": return YeastFlavor.JELLY;
            case "Cinnamon Sugar": return YeastFlavor.CINNAMON;
            case "Boston Cream": return YeastFlavor.CREAM;
            case "Powdered Sugar": return YeastFlavor.SUGAR;
        }
        return null;
    }

    private CakeFlavor getCakeFlavor(String flavorType){
        switch(flavorType){
            case "Plain Vanilla": return CakeFlavor.PLAIN;
            case "Chocolate Cake": return CakeFlavor.CHOCOLATE;
            case "Strawberry Short-Cake": return CakeFlavor.STRAWBERRY;
        }
        return null;
    }

    private String format(String input){
        String[] places = input.split("\\.");
        if(places[1].length()<2){
            input+="0";
        }
        return input;
    }

}

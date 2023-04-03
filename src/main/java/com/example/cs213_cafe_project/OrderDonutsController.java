package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.donut.CakeDonut;
import com.example.cs213_cafe_project.donut.DonutHole;
import com.example.cs213_cafe_project.donut.YeastDonut;
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
    private Button addToBasketButton;
    @FXML
    private TextField selectionTotal;

    @FXML
    public void addToBasket(){
        
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
        priceByType(dType,quantity);
    }

    @FXML
    private void priceByType(String donutType, double quantity){
        switch(donutType){
            case "Yeast Donuts":
                YeastFlavor yeastFlavor = getYeastFlavor(donutType);
                YeastDonut yeastDonut = new YeastDonut(yeastFlavor);
                selectionTotal.setText("$"+ Math.round(yeastDonut.itemPrice() * quantity * 100.0) / 100.0);
                break;
            case "Cake Donuts":
                CakeFlavor cakeFlavor = getCakeFlavor(donutType);
                CakeDonut cakeDonut = new CakeDonut(cakeFlavor);
                selectionTotal.setText("$"+ Math.round(cakeDonut.itemPrice() * quantity * 100.0) / 100.0);
                break;
            case "Donut Holes":
                HoleFlavor holeFlavor = getHoleFlavor(donutType);
                DonutHole donutHole = new DonutHole(holeFlavor);
                selectionTotal.setText("$"+Math.round(donutHole.itemPrice() * quantity * 100.0) / 100.0 );
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
    private void reset(){
        addToBasketButton.disableProperty().set(true);
        numDonuts.setValue(null);
        numDonuts.setPromptText("Amount:");
        selectionTotal.setText(null);
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

}

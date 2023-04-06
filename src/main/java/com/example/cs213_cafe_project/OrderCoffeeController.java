package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.cofee.AddOn;
import com.example.cs213_cafe_project.cofee.Coffee;
import com.example.cs213_cafe_project.cofee.Size;
import com.example.cs213_cafe_project.data.BasketItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.skin.ComboBoxBaseSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;

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

    public void initialize() {
        coffeeSizeList = FXCollections.observableArrayList( "Size:","Short", "Tall","Grande", "Venti");
        coffeeSize.setItems(coffeeSizeList);
        coffeeAmount = FXCollections.observableArrayList("Amount:","1","2","3","4","5");
        numCoffee.setItems(coffeeAmount);
        coffeePrice.setText("$0.00");
        coffeePrice.focusTraversableProperty().set(false);
    }

    @FXML
    private Button addCoffeeToOrder;
    @FXML
    private TextField coffeePrice;
    @FXML
    private CheckBox sweetCreamAddOn;
    @FXML
    private CheckBox mochaAddOn;
    @FXML
    private CheckBox frenchVanillaAddOn;
    @FXML
    private CheckBox caramelAddOn;
    @FXML
    private CheckBox irishCreamAddOn;

    @FXML
    public void addCoffeeToOrderClicked(){
        Size size = getSizeFromString(coffeeSize.valueProperty().getValue());
        if(size == null){
            return;
        }
        String amount = numCoffee.valueProperty().getValue();
        int quantity = Integer.parseInt(amount);
        HashSet<AddOn> addOnList = new HashSet<>();
        fillSet(addOnList, sweetCreamAddOn.isSelected(), mochaAddOn.isSelected(), frenchVanillaAddOn.isSelected(), caramelAddOn.isSelected(), irishCreamAddOn.isSelected());
        Coffee myCoffee = new Coffee(size,addOnList);
        BasketItem myItem = new BasketItem(myCoffee,quantity);
        ObservableList<BasketItem> fullBasket = mainController.getFullBasket();
        fullBasket.add(myItem);
        reset();
    }

    private void reset(){
        addCoffeeToOrder.disableProperty().set(true);
        coffeePrice.setText("$0.00");
        coffeePrice.focusTraversableProperty().set(false);
        numCoffee.setValue("Amount:");
        coffeeSize.setValue("Size:");
        numCoffee.disableProperty().set(true);
        disableCheckBoxes();
        unselectCheckBoxes();
    }

    private void disableCheckBoxes(){
        sweetCreamAddOn.disableProperty().set(true);
        mochaAddOn.disableProperty().set(true);
        frenchVanillaAddOn.disableProperty().set(true);
        caramelAddOn.disableProperty().set(true);
        irishCreamAddOn.disableProperty().set(true);
    }

    private void unselectCheckBoxes(){
        sweetCreamAddOn.selectedProperty().set(false);
        mochaAddOn.selectedProperty().set(false);
        frenchVanillaAddOn.selectedProperty().set(false);
        caramelAddOn.selectedProperty().set(false);
        irishCreamAddOn.selectedProperty().set(false);
    }


    @FXML
    public void setCoffeePrice(){
        enableCheckBoxes();
        enableAmountBox();
        Size size = getSizeFromString(coffeeSize.valueProperty().getValue());
        if(size==null){
            return;
        }
        String amount = numCoffee.valueProperty().getValue();
        if(amount == null || amount.equals("Amount:")){
            return;
        }
        enableSubmitBox();
        double quantity = Double.parseDouble(amount);
        HashSet<AddOn> addOnList = new HashSet<>();
        fillSet(addOnList, sweetCreamAddOn.isSelected(), mochaAddOn.isSelected(), frenchVanillaAddOn.isSelected(), caramelAddOn.isSelected(), irishCreamAddOn.isSelected());
        Coffee myCoffee = new Coffee(size,addOnList);
        coffeePrice.setText("$"+Math.round(myCoffee.itemPrice() * quantity * 100.0) / 100.0);
    }

    @FXML
    public void setCoffeePriceInitial(){
        enableAmountBox();
        Size size = getSizeFromString(coffeeSize.valueProperty().getValue());
        if(size==null){
            return;
        }
        String amount = numCoffee.valueProperty().getValue();
        if(amount == null || amount.equals("Amount:")){
            return;
        }
        enableSubmitBox();
        double quantity = Double.parseDouble(amount);
        HashSet<AddOn> addOnList = new HashSet<>();
        fillSet(addOnList, sweetCreamAddOn.isSelected(), mochaAddOn.isSelected(), frenchVanillaAddOn.isSelected(), caramelAddOn.isSelected(), irishCreamAddOn.isSelected());
        Coffee myCoffee = new Coffee(size,addOnList);
        coffeePrice.setText("$"+Math.round(myCoffee.itemPrice() * quantity * 100.0) / 100.0);
    }

    private void fillSet(HashSet<AddOn> addOnList, boolean sweetCream, boolean mocha, boolean frenchVanilla, boolean caramel, boolean irishCream){
        if(sweetCream){
            addOnList.add(AddOn.SWEETCREAM);
        }
        if(mocha){
            addOnList.add(AddOn.MOCHA);
        }
        if(frenchVanilla){
            addOnList.add(AddOn.FRENCHVANILLA);
        }
        if(caramel){
            addOnList.add(AddOn.CARAMEL);
        }
        if(irishCream){
            addOnList.add(AddOn.IRISHCREAM);
        }
    }

    @FXML
    private void enableSubmitBox(){
        addCoffeeToOrder.disableProperty().set(false);
    }

    @FXML
    private void enableAmountBox(){
        numCoffee.disableProperty().set(false);
    }

    @FXML
    private void enableCheckBoxes(){
        sweetCreamAddOn.disableProperty().set(false);
        mochaAddOn.disableProperty().set(false);
        frenchVanillaAddOn.disableProperty().set(false);
        caramelAddOn.disableProperty().set(false);
        irishCreamAddOn.disableProperty().set(false);
    }

    private Size getSizeFromString(String size){
        switch (size){
            case "Short": return Size.SHORT;
            case "Tall": return Size.TALL;
            case "Grande": return Size.GRANDE;
            case "Venti": return Size.VENTI;
        }
        return null;
    }


}

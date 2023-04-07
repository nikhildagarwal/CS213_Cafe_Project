/**
 * Project Package
 */
package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.cofee.AddOn;
import com.example.cs213_cafe_project.cofee.Coffee;
import com.example.cs213_cafe_project.cofee.Size;
import com.example.cs213_cafe_project.data.BasketItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.HashSet;

/**
 * Controller class for orderingCoffee.fxml
 * Does all the event handling and the action handling for the coffee view.
 * Sends and receives data with the mainController.
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class OrderCoffeeController {

    /**
     * Variable to hold the reference to an instance of mainController()
     */
    private MainController mainController;

    /**
     * The list of items holding coffeeSizes:
     * Short, Tall, Grande, or Venti
     */
    private ObservableList<String> coffeeSizeList;

    /**
     * The list of items for quantity of coffee
     */
    private ObservableList<String> coffeeAmount;

    /**
     * Reference to coffeeSize comboBox
     */
    @FXML
    private ComboBox<String> coffeeSize;

    /**
     * Reference to numCoffee comboBox
     */
    @FXML
    private ComboBox<String> numCoffee;

    /**
     * Reference to addCoffeeToOrder button
     */
    @FXML
    private Button addCoffeeToOrder;

    /**
     * Reference to TextField coffeePrice
     */
    @FXML
    private TextField coffeePrice;

    /**
     * Reference to CheckBox object sweetCreamAddOn
     */
    @FXML
    private CheckBox sweetCreamAddOn;

    /**
     * Reference to CheckBox object mocha
     */
    @FXML
    private CheckBox mochaAddOn;

    /**
     * Reference to CheckBox object frenchVanilla
     */
    @FXML
    private CheckBox frenchVanillaAddOn;

    /**
     * Reference to CheckBox object caramel
     */
    @FXML
    private CheckBox caramelAddOn;

    /**
     * Reference to CheckBox object irishCream
     */
    @FXML
    private CheckBox irishCreamAddOn;

    /**
     * Initializes an instance of the mainController and sets mainController to that value
     * @param controller The controller class object
     */
    public void setMainController (MainController controller){
        mainController = controller;
    }

    /**
     * init method that runs as soon as the .fxml file is set and staged
     *      - set all monetary textFields to $0.00
     *      - Disable all buttons until the size of a coffee has been chosen
     */
    public void initialize() {
        coffeeSizeList = FXCollections.observableArrayList( "Size:","Short", "Tall","Grande", "Venti");
        coffeeSize.setItems(coffeeSizeList);
        coffeeAmount = FXCollections.observableArrayList("Amount:","1","2","3","4","5");
        numCoffee.setItems(coffeeAmount);
        coffeePrice.setText("$0.00");
        coffeePrice.focusTraversableProperty().set(false);
    }

    /**
     * Method ot handle action on addCoffee Button
     * Creates a new basketItem and sends it to the mainController list of all basket Items
     */
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

    /**
     * Sets the price of the coffee everytime there is enough information to determine the price of a coffee.
     * Enables the user to submit this coffee to the basket.
     */
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

    /**
     * Sets the initial price of the coffee
     */
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

    /**
     * resets disable() properties of views objects.
     * also sets the text-fields to "$0.00" and the ComboBox values back to original values.
     */
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

    /**
     * Disables checkBoxes
     */
    private void disableCheckBoxes(){
        sweetCreamAddOn.disableProperty().set(true);
        mochaAddOn.disableProperty().set(true);
        frenchVanillaAddOn.disableProperty().set(true);
        caramelAddOn.disableProperty().set(true);
        irishCreamAddOn.disableProperty().set(true);
    }

    /**
     * De-selects the checkBoxes
     */
    private void unselectCheckBoxes(){
        sweetCreamAddOn.selectedProperty().set(false);
        mochaAddOn.selectedProperty().set(false);
        frenchVanillaAddOn.selectedProperty().set(false);
        caramelAddOn.selectedProperty().set(false);
        irishCreamAddOn.selectedProperty().set(false);
    }

    /**
     * Fills the provided set with addOn enum Objects based on the boolean values provided.
     * if a checkbox is checked it will feed true to this function.
     * @param addOnList HashSet<AddOn> that will hold all the addOns for our coffee Object.
     * @param sweetCream boolean checkBox checked. true if check, false otherwise
     * @param mocha boolean checkBox checked. true if check, false otherwise
     * @param frenchVanilla boolean checkBox checked. true if check, false otherwise
     * @param caramel boolean checkBox checked. true if check, false otherwise
     * @param irishCream boolean checkBox checked. true if check, false otherwise
     */
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

    /**
     * Enables the "add to basket" Button
     */
    private void enableSubmitBox(){
        addCoffeeToOrder.disableProperty().set(false);
    }

    /**
     * Enables the "numCoffee" comboBox
     */
    private void enableAmountBox(){
        numCoffee.disableProperty().set(false);
    }

    /**
     * Enables the checkBoxes
     */
    private void enableCheckBoxes(){
        sweetCreamAddOn.disableProperty().set(false);
        mochaAddOn.disableProperty().set(false);
        frenchVanillaAddOn.disableProperty().set(false);
        caramelAddOn.disableProperty().set(false);
        irishCreamAddOn.disableProperty().set(false);
    }

    /**
     * Helper method to get the Size enum Object of the coffee based on a string value.
     * The String value is selected from the ComboBox.
     * @param size String size
     * @return Size enum Object
     */
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

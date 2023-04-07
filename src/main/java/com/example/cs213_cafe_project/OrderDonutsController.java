/**
 * Project Package
 */
package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.data.*;
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

/**
 * Controller class for donutsView.fxml
 * Does all the event handling and the action handling for the donut view.
 * Sends and receives data with the mainController.
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class OrderDonutsController {

    /**
     * Variable to hold the reference to an instance of mainController()
     */
    private MainController mainController;

    /**
     * Image for Yeast Donut
     */
    private Image yeastDonutImage = new Image("file:src/main/resources/com/example/cs213_cafe_project/yeast donut.jpg");

    /**
     * Image for Cake Donut
     */
    private Image cakeDonutImage = new Image("file:src/main/resources/com/example/cs213_cafe_project/cake donut.jpg");

    /**
     * Image for Donut Holes
     */
    private Image donutHoleImage = new Image("file:src/main/resources/com/example/cs213_cafe_project/donut holes.jpg");

    /**
     * List of donut Types
     */
    private ObservableList<String> donutList;

    /**
     * List of donut flavor types that corresponds to each specific donut type
     */
    private ObservableList<String> donutFlavorList;

    /**
     * The amount we want of each donut
     */
    private ObservableList<String> donutAmount;

    /**
     * Reference to ImageView donutImage
     */
    @FXML
    private ImageView donutImage;

    /**
     * Reference to ComboBox numDonuts
     */
    @FXML
    private ComboBox<String> numDonuts;

    /**
     * Reference to ComboBox donutType
     */
    @FXML
    private ComboBox<String> donutType;

    /***
     * Reference to ListView donutFlavorType
     */
    @FXML
    private ListView<String> donutFlavorType;

    /**
     * Reference to ListView basketItemsListView
     */
    @FXML
    private ListView<BasketItem> basketItemsListView;

    /**
     * Reference to Button addToOrderButton
     */
    @FXML
    private Button addToOrderButton;

    /**
     * Reference to Button removeButton
     */
    @FXML
    private Button removeButton;

    /**
     * Reference to Button addToBasketButton
     */
    @FXML
    private Button addToBasketButton;

    /**
     * Reference to TextField selectionTotal
     */
    @FXML
    private TextField selectionTotal;

    /**
     * Reference to TextField basketTotal
     */
    @FXML
    private TextField basketTotal;

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
     *      - Disable all buttons until the type of the donut has been chosen
     *      - sets the comboBox values
     */
    public void initialize() {
        donutList = FXCollections.observableArrayList("Choose Donut:","Yeast Donuts", "Cake Donuts", "Donut Holes");
        donutType.setItems(donutList);
        donutAmount = FXCollections.observableArrayList("Amount:","1","2","3","4","5","6","7","8","9","10","11","12");
        numDonuts.setItems(donutAmount);
        selectionTotal.setText("$0.00");
        selectionTotal.focusTraversableProperty().set(false);
        donutFlavorType.disableProperty().set(true);
        basketItemsListView.disableProperty().set(true);
        basketTotal.setText("$0.00");
        basketTotal.focusTraversableProperty().set(false);
    }

    /**
     * Action handler for comboBox "Choose Donuts"
     * Will display a list of items to be selected in a dropdown menu.
     * This dropdown menu depends on the donutType selected.
     */
    @FXML
    public void displaySelected() {
        String selected = donutType.getSelectionModel().getSelectedItem();
        if(selected == null || selected.equals("Choose Donut:")){
            if(selected.equals("Choose Donut:")){
                donutImage.setImage(null);
            }
            return;
        }
        if(selected.equals("Cake Donuts")) {
            donutFlavorList = FXCollections.observableArrayList("Plain Vanilla", "Chocolate Cake", "Strawberry Short-Cake");
            donutFlavorType.setItems(donutFlavorList);
            donutImage.setImage(cakeDonutImage);
        }
        else if(selected.equals("Yeast Donuts")) {
            donutFlavorList = FXCollections.observableArrayList("Classic", "Glazed", "Jelly", "Cinnamon Sugar", "Boston Cream", "Powdered Sugar");
            donutFlavorType.setItems(donutFlavorList);
            donutImage.setImage(yeastDonutImage);
        }
        else if(selected.equals("Donut Holes")){
            donutFlavorList = FXCollections.observableArrayList("Glazed", "Chocolate", "Jelly");
            donutFlavorType.setItems(donutFlavorList);
            donutImage.setImage(donutHoleImage);
        }
        reset();
        setSelectionPrice();
    }

    /**
     * Sends the BasketItems in the donutViewBasket to the FullBasket object in the mainController.
     * Clears the local basket object.
     * And resets the buttons to init values.
     */
    @FXML
    public void addToOrderButtonClicked(){
        ObservableList<BasketItem> basketItems = mainController.getDonutBasketItems();
        ObservableList<BasketItem> fullBasket = mainController.getFullBasket();
        for(int i = 0;i<basketItems.size();i++){
            BasketItem currItem = basketItems.get(i);
            fullBasket.add(currItem);
        }
        basketTotal.setText("$0.00");
        basketItems.clear();
        basketItemsListView.setItems(basketItems);
        removeButton.disableProperty().set(true);
        basketItemsListView.disableProperty().set(true);
        addToOrderButton.disableProperty().set(true);
    }

    /**
     * Event handler for loadBasketButton
     * Will set the items in the basket to what they were initially.
     * Almost like a retrieve data function
     */
    @FXML
    public void setInitialBasket(){
        donutType.disableProperty().set(false);
        ObservableList<BasketItem> basketItems = mainController.getDonutBasketItems();
        basketItemsListView.setItems(basketItems);
        setBasketPrice();
        if(!basketItems.isEmpty()){
            basketItemsListView.disableProperty().set(false);
            addToOrderButton.disableProperty().set(false);
        }else{
            basketItemsListView.setItems(FXCollections.observableArrayList(new BasketItem(BasketItem.DONUTVIEW)));
        }
    }

    /**
     * Event handler for removeButton
     * removes the basketItem from the donutBasket.
     * Updates the Sub-total accordingly
     */
    @FXML
    public void removeButtonClicked(){
        ObservableList<BasketItem> basketItems = mainController.getDonutBasketItems();
        removeButton.disableProperty().set(true);
        BasketItem selectedItem = basketItemsListView.getSelectionModel().getSelectedItem();
        basketItems.remove(selectedItem);
        setBasketPrice();
        if(basketItems.isEmpty()){
            addToOrderButton.disableProperty().set(true);
        }
    }

    /**
     * Sets the running subtotal of the donut if there is enough information to calculate a price.
     */
    @FXML
    public void setSelectionPrice(){
        donutFlavorType.disableProperty().set(false);
        String dType = donutType.valueProperty().getValue();
        if(dType.equals("Choose Donut:")){
            return;
        }
        enableAmountBox();
        String amount = numDonuts.valueProperty().getValue();
        if(amount==null || amount.equals("Amount:")){
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

    /**
     * Enables the remove button
     */
    @FXML
    public void enableRemoveBox(){
        removeButton.disableProperty().set(false);
    }

    /**
     * Adds BasketItem to the donutViewBasket.
     * MenuItem will always be a donut.
     * Resets the combo-box values and sets the subtotal back to $0.00
     */
    @FXML
    public void addToBasket(){
        basketItemsListView.disableProperty().set(false);
        ObservableList<BasketItem> basketItems = mainController.getDonutBasketItems();
        String dType = donutType.valueProperty().getValue();
        if(dType.equals("Choose Donut:")){
            return;
        }
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
        basketItemsListView.setItems(basketItems);
        setBasketPrice();
        addToOrderButton.disableProperty().set(false);
        resetComboBoxValue(donutType);
        resetComboBoxValue(numDonuts);
        numDonuts.disableProperty().set(true);
        donutFlavorType.disableProperty().set(true);
    }

    /**
     * helper method to set the price of a the basket.
     */
    private void setBasketPrice(){
        ObservableList<BasketItem> basketItems = mainController.getDonutBasketItems();
        double total = 0;
        for(int i =0;i<basketItems.size();i++){
            BasketItem currItem = basketItems.get(i);
            total += (currItem.getMenuItem().itemPrice() * currItem.getQuantity());
        }
        String money = "$"+ (Math.round(total * 100.0) / 100.0);
        basketTotal.setText(format(money));
        basketTotal.focusTraversableProperty().set(false);
    }

    /**
     * Sets the comboBox value back to the value of its promptText
     * @param comboBox The combo box we want to reset
     */
    private void resetComboBoxValue(ComboBox<String> comboBox){
        String value = comboBox.getPromptText();
        comboBox.setValue(value);
    }

    /**
     * Sets the selectionTotal textField to the price of the specific donut that we have selected
     * @param donutType String type of Donut
     * @param quantity double quantity of donut
     * @param donutFlavorType String flavor of Donut
     */
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

    /**
     * Enable "add Basket to Order" Button
     */
    private void enableSubmitToBasketBox(){
        addToBasketButton.disableProperty().set(false);
    }

    /**
     * Enables numDonuts ComboBox
     */
    private void enableAmountBox(){
        numDonuts.disableProperty().set(false);
    }

    /**
     * resets the buttons and text
     * revert to disable  = true and "$0.00"
     */
    private void reset(){
        addToBasketButton.disableProperty().set(true);
        selectionTotal.setText("$0.00");
        selectionTotal.focusTraversableProperty().set(false);
    }

    /**
     * helper method to get the HoleFlavor enum Object
     * @param flavorType String flavor of donut
     * @return corresponding HoleFlavor Object
     */
    private HoleFlavor getHoleFlavor(String flavorType){
        switch(flavorType){
            case "Glazed": return HoleFlavor.GLAZED;
            case "Chocolate": return HoleFlavor.CHOCOLATE;
            case "Jelly": return HoleFlavor.JELLY;
        }
        return null;
    }

    /**
     * helper method to get the YeastFlavor enum Object
     * @param flavorType String flavor of donut
     * @return corresponding YeastFlavor Object
     */
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

    /**
     * helper method to get the CakeFlavor enum Object
     * @param flavorType String flavor of donut
     * @return corresponding CakeFlavor Object
     */
    private CakeFlavor getCakeFlavor(String flavorType){
        switch(flavorType){
            case "Plain Vanilla": return CakeFlavor.PLAIN;
            case "Chocolate Cake": return CakeFlavor.CHOCOLATE;
            case "Strawberry Short-Cake": return CakeFlavor.STRAWBERRY;
        }
        return null;
    }

    /**
     * Formats String input to a String number with only 2 decimal places.
     * @param input String number Ex: "1.2"
     * @return String Ex: "1.20"
     */
    private String format(String input){
        String[] places = input.split("\\.");
        if(places[1].length()<2){
            input+="0";
        }
        return input;
    }

}

/**
 * Project package
 */
package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.data.BasketItem;
import com.example.cs213_cafe_project.data.MenuItem;
import com.example.cs213_cafe_project.data.Order;
import com.example.cs213_cafe_project.donut.YeastDonut;
import com.example.cs213_cafe_project.donut.flavors.YeastFlavor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Controller class for orderBasket.fxml
 * Does all the event handling and the action handling for the orderBasket.
 * Sends and receives data with the mainController.
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class OrderBasketController {

    /**
     * Variable to hold an instance of the mainController
     */
    private MainController mainController;

    /**
     * The list of basketItems that can be viewed and interacted with in the GUI
     */
    @FXML
    private ListView<BasketItem> basketListView;

    /**
     * The subtotal textField
     */
    @FXML
    private TextField subTotal;

    /**
     * The tax amount textField
     */
    @FXML
    private TextField tax;

    /**
     * The textField that holds the value of tax + subTotal
     */
    @FXML
    private TextField total;

    /**
     * Remove button reference
     */
    @FXML
    private Button removeButton;

    /**
     * placeOrderButton reference
     */
    @FXML
    private Button placeOrderButton;

    /**
     * loadBasketButton button reference
     */
    @FXML
    private Button loadBasketButton;

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
     *      - Disable all buttons until the load basket button is clicked to fetch data from mainController
     */
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
    }

    /**
     * Loads all the basket items from the mainController data storage into our listView.
     * The items can then be selected and some action carried out.
     * Enables all the other functionalities.
     * Sets the init values for the $ amounts in the textFields.
     */
    @FXML
    public void setLoadBasket(){
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
            setBasketPrice(fullBasket);
        }
    }

    /**
     * Checks to see if the listView has been selected.
     * If there is a value that has been selected, the remove button will be enabled.
     */
    @FXML
    public void listViewSelected(){
        BasketItem selected = basketListView.getSelectionModel().getSelectedItem();
        if(selected ==null){
            return;
        }
        removeButton.disableProperty().set(false);
    }

    /**
     * event handler for the remove button
     * Will remove the selected basketItem and update the prices that are displayed in the textFields.
     */
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
        }
    }

    /**
     * Resets the entire page to what it was when initialized.
     * Sends the data to the mainController as an order and will be added to the data storage of list of orders.
     */
    @FXML
    public void placeOrder(){
        int orderNumber = createOrder();
        basketListView.setItems(FXCollections.observableArrayList(new BasketItem(new YeastDonut(YeastFlavor.TEST), orderNumber) ));
        placeOrderButton.disableProperty().set(true);
        removeButton.disableProperty().set(true);
        subTotal.setText("$0.00");
        total.setText("$0.00");
        tax.setText("$0.00");
        loadBasketButton.disableProperty().set(false);
    }

    /**
     * Creates an order object based on the input fields.
     * @return integer orderNumber associated with the order that was just created.
     */
    private int createOrder(){
        ObservableList<BasketItem> fullBasket = mainController.getFullBasket();
        ArrayList<BasketItem> orderList = new ArrayList<>();
        createOrderList(orderList,fullBasket);
        int orderNumber = generateOrderNumber();
        Order order = new Order(orderNumber,orderList);
        ObservableList<Order> listOfOrders = mainController.getListOfOrders();
        listOfOrders.add(order);
        fullBasket.clear();
        return orderNumber;
    }

    /**
     * Generates a random OrderNumber and checks that it is not currently in use.
     * If the number is already in use it will keep creating orderNumbers until a unique number is found.
     * @return integer order-number.
     */
    private int generateOrderNumber(){
        HashSet<Integer> orderNumbers = mainController.getOrderNumbers();
        int randomNum = (int) (Math.random() * 1000);
        while(true){
            if(!orderNumbers.contains(randomNum)){
                orderNumbers.add(randomNum);
                return randomNum;
            }
        }
    }

    /**
     * Creates the list of orders and sets the reference of this new object to the reference in the mainController data storage.
     * Now this new object will be accessed to get a list of all the active orders.
     * @param orderList The existing list of orders that we want to add our new order to
     * @param fullBasket the list of basketItems that we are going to package into a new order.
     */
    private void createOrderList(ArrayList<BasketItem> orderList,ObservableList<BasketItem> fullBasket){
        for(int i = 0;i<fullBasket.size();i++){
            BasketItem currBasketItem = fullBasket.get(i);
            if(i==0){
                orderList.add(currBasketItem);
            }else{
                boolean signal = false;
                for(int j =0;j<orderList.size();j++){
                    if(orderList.get(j).getMenuItem().equals(currBasketItem.getMenuItem())){
                        BasketItem newItem = new BasketItem(orderList.get(j).getMenuItem(),currBasketItem.getQuantity()+orderList.get(j).getQuantity());
                        orderList.add(newItem);
                        orderList.remove(j);
                        signal = true;
                        j = orderList.size()+1;
                    }
                }
                if(!signal){
                    orderList.add(currBasketItem);
                }
            }
        }
    }

    /**
     * Helper method to set the values of the textFields whenever there is some additive action (LOAD)
     * or negative action (REMOVE)
     * @param basket A list of all basket items that our method will loop through to calculate the price.
     */
    private void setBasketPrice(ObservableList<BasketItem> basket){
        double subTotalAmount = 0.0;
        for(int i = 0;i< basket.size();i++){
            MenuItem currMenuItem = basket.get(i).getMenuItem();
            subTotalAmount += (currMenuItem.itemPrice() * basket.get(i).getQuantity());
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

    /**
     * Helper method to format a double as a monetary amount
     * @param amount double amount=
     * @return String monetary value Ex: 7.58 --> "$7.58"
     */
    private String format(double amount){
        String amountString = Double.toString(amount);
        String[] splitter = amountString.split("\\.");
        if(splitter[1].length()<2){
            amountString += "0";
        }
        return "$"+amountString;
    }

}

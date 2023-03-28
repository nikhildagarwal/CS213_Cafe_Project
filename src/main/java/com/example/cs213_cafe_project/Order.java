/**
 * master package for all java files
 */
package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.cofee.AddOn;
import com.example.cs213_cafe_project.cofee.Coffee;
import com.example.cs213_cafe_project.cofee.Size;
import com.example.cs213_cafe_project.donut.CakeDonut;
import com.example.cs213_cafe_project.donut.YeastDonut;
import com.example.cs213_cafe_project.donut.flavors.CakeFlavor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Implementation for orders in our project.
 * Each order object consists of an integer order number and a hashmap structure for the order itself.
 * Keys: menuItems, Values: quantity of corresponding menuItem
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class Order {

    /**
     * integer order number
     */
    private int orderNumber;

    /**
     * HashMap orderList
     * Keys: menuItems, Values: quantity of menuItem
     */
    private HashMap<MenuItem,Integer> orderList;

    /**
     * Constructor for order Object.
     * Initializes a new, empty order.
     * @param orderNumber
     */
    Order(int orderNumber){
        this.orderNumber = orderNumber;
        this.orderList = new HashMap<>();
    }

    /**
     * Overload Constructor for order Object.
     * Creates an object with a non-empty, user selected order.
     * @param orderNumber
     * @param orderList
     */
    Order(int orderNumber, HashMap<MenuItem,Integer> orderList){
        this.orderNumber = orderNumber;
        this.orderList = orderList;
    }

    /**
     * Override for toString() method in Java object class.
     * Prints order object as a string.
     * Also prints the total costs of the order.
     * @return String output consisting of order Number, menuItems and corresponding quantity, as well as order subtotals.
     */
    @Override
    public String toString(){
        String output = "";
        output += ("Order Number: "+orderNumber+"\n");
        for(Map.Entry<MenuItem,Integer> entry: orderList.entrySet()){
            MenuItem item = entry.getKey();
            Integer num = entry.getValue();
            output += item.toString();
            output += (". Quantity: "+ num);
            output += "\n";
        }
        Order order = new Order(orderNumber,orderList);
        output += ("Order Price: $" + order.orderPrice() + "\n");
        output += ("Tax: $" + order.orderTax() + "\n");
        output += ("Order Total: $" + order.orderTotal() + "\n");
        return output;
    }

    /**
     * Returns the total price of the order
     * (NOT INCLUDING TAX)
     * @return double order price - tax
     */
    public double orderPrice(){
        double total = 0;
        for(Map.Entry<MenuItem,Integer> entry: orderList.entrySet()){
            MenuItem item = entry.getKey();
            Integer num = entry.getValue();
            total += (item.itemPrice() * num);
        }
        return total;
    }

    /**
     * Returns the total tax for an order
     * @return double tax
     */
    public double orderTax(){
        Order order = new Order(orderNumber,orderList);
        return Math.round(order.orderPrice() * 0.06625 * 100.0) / 100.0;
    }

    /**
     * Returns the total cost of the order
     * (PRICE + TAX)
     * @return double total amount due
     */
    public double orderTotal(){
        Order order = new Order(orderNumber,orderList);
        return order.orderTax() + order.orderPrice();
    }

    public static void main (String args[]){
        CakeDonut cakeDonut = new CakeDonut(CakeFlavor.STRAWBERRY);
        int cakeDonutQuantity = 2;
        HashSet<AddOn> addOns = new HashSet<>();
        addOns.add(AddOn.CARAMEL);
        addOns.add(AddOn.MOCHA);
        Coffee coffee = new Coffee(Size.VENTI,addOns);
        int coffeeQuantity = 1;
        HashMap<MenuItem,Integer> orderList = new HashMap<>();
        orderList.put(cakeDonut,cakeDonutQuantity);
        orderList.put(coffee,coffeeQuantity);
        Order myOrder = new Order(98234,orderList);
        System.out.println(myOrder);
    }
}

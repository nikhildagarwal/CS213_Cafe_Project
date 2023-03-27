package com.example.cs213_cafe_project;

import com.example.cs213_cafe_project.cofee.AddOn;
import com.example.cs213_cafe_project.cofee.Coffee;
import com.example.cs213_cafe_project.cofee.Size;
import com.example.cs213_cafe_project.donut.CakeDonut;
import com.example.cs213_cafe_project.donut.YeastDonut;
import com.example.cs213_cafe_project.donut.flavors.CakeFlavor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Order {

    private int orderNumber;
    private HashMap<MenuItem,Integer> orderList;

    Order(int orderNumber){
        this.orderNumber = orderNumber;
        this.orderList = new HashMap<>();
    }

    Order(int orderNumber, HashMap<MenuItem,Integer> orderList){
        this.orderNumber = orderNumber;
        this.orderList = orderList;
    }

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

    public double orderPrice(){
        double total = 0;
        for(Map.Entry<MenuItem,Integer> entry: orderList.entrySet()){
            MenuItem item = entry.getKey();
            Integer num = entry.getValue();
            total += (item.itemPrice() * num);
        }
        return total;
    }

    public double orderTax(){
        Order order = new Order(orderNumber,orderList);
        return Math.round(order.orderPrice() * 0.06625 * 100.0) / 100.0;
    }

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

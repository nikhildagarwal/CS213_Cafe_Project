package com.example.cs213_cafe_project.data;

import com.example.cs213_cafe_project.donut.YeastDonut;
import com.example.cs213_cafe_project.donut.flavors.YeastFlavor;

public class BasketItem {
    private MenuItem menuItem;
    private int quantity;

    public static final int BASKETVIEW = 0;
    public static final int DONUTVIEW = 1;
    public static final int ORDERPLACED = 2;

    public BasketItem(MenuItem menuItem,int quantity){
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public BasketItem(){
        this.menuItem = null;
        this.quantity = 0;
    }

    public BasketItem(int quantity){
        this.menuItem = null;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem(){
        return menuItem;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString(){
        if(menuItem == null && quantity == BASKETVIEW){
            return "Basket is Empty!";
        }
        if(menuItem == null && quantity == DONUTVIEW){
            return "Basket is Empty!";
        }
        if(menuItem.equals(new YeastDonut(YeastFlavor.TEST))){
            return "Order Placed!\nOrder Number: "+quantity;
        }
        return quantity+ " x " + menuItem.toString();
    }
}

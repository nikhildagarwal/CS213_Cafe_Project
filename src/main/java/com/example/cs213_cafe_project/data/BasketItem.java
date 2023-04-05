package com.example.cs213_cafe_project.data;

public class BasketItem {
    private MenuItem menuItem;
    private int quantity;

    public static final int BASKETVIEW = 0;
    public static final int DONUTVIEW = 1;

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
            return "Basket is Empty!\nPlease go to 'Donuts' or 'Coffee' to add items to your basket.";
        }
        if(menuItem ==null && quantity == DONUTVIEW){
            return "Basket is Empty!\nPlease select donuts to add.";
        }
        return quantity+ " x " + menuItem.toString();
    }
}

package com.example.cs213_cafe_project.data;

public class BasketItem {
    private MenuItem menuItem;
    private int quantity;

    public BasketItem(MenuItem menuItem,int quantity){
        this.menuItem = menuItem;
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
        return quantity+ " x " + menuItem.toString();
    }
}

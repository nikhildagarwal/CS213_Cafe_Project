package com.example.cs213_cafe_project.data;

public class BasketItem {
    private MenuItem menuItem;
    private int quantity;

    public BasketItem(MenuItem menuItem,int quantity){
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public BasketItem(){
        this.menuItem = null;
        this.quantity = 0;
    }

    public MenuItem getMenuItem(){
        return menuItem;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString(){
        if(quantity == 0){
            return "Basket is Empty";
        }
        return quantity+ " x " + menuItem.toString();
    }
}

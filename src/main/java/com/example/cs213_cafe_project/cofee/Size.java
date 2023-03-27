package com.example.cs213_cafe_project.cofee;

public enum Size {

    SHORT(1.89),
    TALL(2.29),
    GRANDE(2.69),
    VENTI(3.09);

    private double price;

    Size(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return Double.toString(price);
    }
}

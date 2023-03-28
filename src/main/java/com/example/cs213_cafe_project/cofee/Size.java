package com.example.cs213_cafe_project.cofee;

public enum Size {

    SHORT(1.89,"Short"),
    TALL(2.29,"Tall"),
    GRANDE(2.69,"Grande"),
    VENTI(3.09,"Venti");

    private double price;
    private String size;

    Size(double price, String size){
        this.price = price;
        this.size = size;
    }

    public String getSize(){
        return size;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return Double.toString(price);
    }
}

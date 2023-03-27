package com.example.cs213_cafe_project.donut;

import com.example.cs213_cafe_project.donut.flavors.CakeFlavor;
import com.example.cs213_cafe_project.MenuItem;

public class CakeDonut extends MenuItem {

    public static final double cakeDonutPrice = 1.79;

    private CakeFlavor cakeFlavor;

    public CakeDonut(){
        this.cakeFlavor = CakeFlavor.PLAIN;
    }

    public CakeDonut(CakeFlavor cakeFlavor){
        this.cakeFlavor = cakeFlavor;
    }

    public CakeFlavor getCakeFlavor(){
        return cakeFlavor;
    }

    @Override
    public double itemPrice(){
        return cakeDonutPrice;
    }

    @Override
    public String toString(){
        return "Cake Donut - Flavor: "+cakeFlavor.getFlavor();
    }

    @Override
    public boolean equals(Object obj){
        CakeDonut cakeDonut = (CakeDonut) obj;
        if(cakeFlavor.equals(cakeDonut.getCakeFlavor())){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        CakeDonut c1 = new CakeDonut(CakeFlavor.STRAWBERRY);
        CakeDonut c2 = new CakeDonut(CakeFlavor.STRAWBERRY);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1.equals(c2));
        System.out.println(c1.itemPrice());
    }

}

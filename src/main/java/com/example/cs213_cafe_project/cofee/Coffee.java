package com.example.cs213_cafe_project.cofee;

import com.example.cs213_cafe_project.MenuItem;
import java.util.HashSet;

public class Coffee extends MenuItem {

    private Size size;
    private HashSet<AddOn> addOnList;

    public Coffee(){
        this.size = Size.SHORT;
        this.addOnList = new HashSet<>();
    }

    public Coffee(Size size){
        this.size = size;
        this.addOnList = new HashSet<>();
    }

    public Coffee(Size size, HashSet<AddOn> addOnList){
        this.size = size;
        this.addOnList = addOnList;
    }

    @Override
    public double itemPrice(){
        double addOnPrice = addOnList.size() * 0.30;
        return addOnPrice + size.getPrice();
    }

    @Override
    public String toString(){
        return "Coffee - Size: "+sizeString(size)+", AddOns: "+addOnList.toString();
    }

    @Override
    public boolean equals(Object obj){
        Coffee coffee = (Coffee) obj;
        if(coffee.size.equals(size) && coffee.addOnList.equals(addOnList)){
            return true;
        }
        return false;
    }

    public String sizeString(Size size){
        if(size.equals(Size.SHORT)){
            return "Short";
        }else if(size.equals(Size.TALL)){
            return "Tall";
        }else if(size.equals(Size.GRANDE)){
            return "Grande";
        }else{
            return "Venti";
        }
    }

    public static void main (String[] args){
        HashSet<AddOn> l1 = new HashSet<>();
        l1.add(AddOn.CARAMEL);
        l1.add(AddOn.FRENCHVANILLA);
        l1.add(AddOn.MOCHA);
        Coffee c1 = new Coffee(Size.TALL,l1);
        System.out.println(c1);
        System.out.println(c1.itemPrice());

        HashSet<AddOn> l2 = new HashSet<>();
        l2.add(AddOn.CARAMEL);
        l2.add(AddOn.FRENCHVANILLA);
        l2.add(AddOn.MOCHA);
        Coffee c2 = new Coffee(Size.TALL,l2);
        System.out.println(c1.equals(c2));
    }
}

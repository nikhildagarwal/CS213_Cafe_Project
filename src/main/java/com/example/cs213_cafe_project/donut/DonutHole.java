package com.example.cs213_cafe_project.donut;

import com.example.cs213_cafe_project.donut.flavors.HoleFlavor;
import com.example.cs213_cafe_project.MenuItem;

public class DonutHole extends MenuItem {

    public static final double donutHolePrice = 0.39;

    private HoleFlavor holeFlavor;

    public DonutHole(){
        this.holeFlavor = HoleFlavor.GLAZED;
    }

    public DonutHole(HoleFlavor holeFlavor){
        this.holeFlavor = holeFlavor;
    }

    public HoleFlavor getHoleFlavor(){
        return holeFlavor;
    }

    @Override
    public double itemPrice(){
        return donutHolePrice;
    }

    @Override
    public String toString(){
        return "Donut Hole - Flavor: "+holeFlavor.getFlavor();
    }

    @Override
    public boolean equals(Object obj){
        DonutHole donutHole = (DonutHole) obj;
        if(holeFlavor.equals(donutHole.getHoleFlavor())){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        DonutHole d1 = new DonutHole();
        DonutHole d2 = new DonutHole(HoleFlavor.JELLY);
        DonutHole d3 = new DonutHole(HoleFlavor.JELLY);
        System.out.println(d1);
        System.out.println(d2.equals(d3));
        System.out.println(d2.equals(d1));
        System.out.println(d1.itemPrice());
    }
}

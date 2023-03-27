package com.example.cs213_cafe_project.donut;

import com.example.cs213_cafe_project.MenuItem;
import com.example.cs213_cafe_project.donut.flavors.YeastFlavor;

public class YeastDonut extends MenuItem {

    public static final double yeastDonutPrice = 1.59;

    private YeastFlavor yeastFlavor;

    public YeastDonut(YeastFlavor yeastFlavor){
        this.yeastFlavor = yeastFlavor;
    }

    public YeastDonut(){
        this.yeastFlavor = YeastFlavor.PLAIN;
    }

    public YeastFlavor getYeastFlavor(){
        return yeastFlavor;
    }

    @Override
    public double itemPrice(){
        return yeastDonutPrice;
    }

    @Override
    public String toString(){
        return "Yeast Donut - Flavor: "+yeastFlavor.getFlavor();
    }

    @Override
    public boolean equals(Object obj){
        YeastDonut yeastDonut = (YeastDonut) obj;
        if(yeastFlavor.equals(yeastDonut.getYeastFlavor())){
            return true;
        }
        return false;
    }

    public static void main (String[] args){
        YeastDonut y1 = new YeastDonut(YeastFlavor.CINNAMON);
        YeastDonut y2 = new YeastDonut(YeastFlavor.CINNAMON);
        System.out.println(y1.equals(y2));
        System.out.println(y2.itemPrice());
    }
}

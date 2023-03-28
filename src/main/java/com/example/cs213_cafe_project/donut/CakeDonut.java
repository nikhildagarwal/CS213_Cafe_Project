/**
 * package for all donut objects
 * yeast donuts, cake donuts, donut holes
 */
package com.example.cs213_cafe_project.donut;

import com.example.cs213_cafe_project.donut.flavors.CakeFlavor;
import com.example.cs213_cafe_project.MenuItem;

/**
 * CakeDonut object which extends the superClass MenuItem
 * Consists of one variable: an enum object CakeFlavor
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class CakeDonut extends MenuItem {

    /**
     * final price of cake donut
     */
    public static final double cakeDonutPrice = 1.79;

    /**
     * Enum object CakeFlavor describing flavor of donut
     */
    private CakeFlavor cakeFlavor;

    /**
     * Default constructor for CakeDonut object
     * Sets flavour to "plain" by default
     */
    public CakeDonut(){
        this.cakeFlavor = CakeFlavor.PLAIN;
    }

    /**
     * Constructor overload for CakeDonut
     * Sets the flavour to an input object.
     * @param cakeFlavor enum object CakeFlavor describing flavor of donut
     */
    public CakeDonut(CakeFlavor cakeFlavor){
        this.cakeFlavor = cakeFlavor;
    }

    /**
     * getter method to return the cakeFlavor attribute of the CakeDonut object
     * @return returns CakeFlavor object
     */
    public CakeFlavor getCakeFlavor(){
        return cakeFlavor;
    }

    /**
     * Implements the abstract method itemPrice() from MenuItem superClass
     * Each cake donut costs $1.79
     * @return double total cost of Cake donut (NOT INCLUDING TAX)
     */
    @Override
    public double itemPrice(){
        return cakeDonutPrice;
    }

    /**
     * Override method for toString() method in Java Object class.
     * gets the Cake Donut object as a printable string.
     * @return String of cake donut object (along with flavor)
     */
    @Override
    public String toString(){
        return "Cake Donut - Flavor: "+cakeFlavor.getFlavor();
    }

    /**
     * Override equals() method in java Object class.
     * @param obj CakeDonut Object we want to compare to initial CakeDonut Object
     *            Syntax: CD1.equals(CD2)
     * @return true if the flavours of each donut is the same. false otherwise.
     */
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

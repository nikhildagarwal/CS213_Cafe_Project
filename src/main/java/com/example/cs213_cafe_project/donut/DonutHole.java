/**
 * package for all donut objects
 * yeast donuts, cake donuts, donut holes
 */
package com.example.cs213_cafe_project.donut;

import com.example.cs213_cafe_project.donut.flavors.HoleFlavor;
import com.example.cs213_cafe_project.data.MenuItem;

/**
 * DonutHole object which extends the superClass MenuItem
 * Consists of one variable: an enum object HoleFlavor
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class DonutHole extends MenuItem {

    /**
     * final price of donutHole
     */
    public static final double donutHolePrice = 0.39;

    /**
     * Enum object HoleFlavor describing flavor of donut
     */
    private HoleFlavor holeFlavor;

    /**
     * Default constructor for DonutHole object
     * Sets flavour to "glazed" by default
     */
    public DonutHole(){
        this.holeFlavor = HoleFlavor.GLAZED;
    }

    /**
     * Constructor overload for DonutHole
     * Sets the flavour to an input object.
     * @param holeFlavor enum object HoleFlavor describing flavor of donut
     */
    public DonutHole(HoleFlavor holeFlavor){
        this.holeFlavor = holeFlavor;
    }

    /**
     * getter method to return the holeFlavor attribute of the DonutHole object
     * @return returns HoleFlavor object
     */
    public HoleFlavor getHoleFlavor(){
        return holeFlavor;
    }

    /**
     * Implements the abstract method itemPrice() from MenuItem superClass
     * Each donut hole costs $0.39
     * @return double total cost of Donut Hole (NOT INCLUDING TAX)
     */
    @Override
    public double itemPrice(){
        return donutHolePrice;
    }

    /**
     * Override method for toString() method in Java Object class.
     * gets the Donut Hole object as a printable string.
     * @return String of donut hole object (along with flavor)
     */
    @Override
    public String toString(){
        return "Donut Hole - Flavor: "+holeFlavor.getFlavor();
    }

    /**
     * Override equals() method in java Object class.
     * @param obj DonutHole Object we want to compare to initial DonutHole Object
     *            Syntax: DH1.equals(DH2)
     * @return true if the flavours of each donut is the same. false otherwise.
     */
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

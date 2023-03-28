/**
 * master package for all java files
 */
package com.example.cs213_cafe_project;

/**
 * Abstract class MenuItem
 * Super class to all menu item classes: coffees, yeast donuts, cake donuts, and donut holes
 * @author Nikhil Agarwal, Hyeon Oh
 */
public abstract class MenuItem{

    /**
     * This method is implemented in each class that extends the MenuItem abstract class
     * @return the price of the menuItem
     */
    public abstract double itemPrice();

}

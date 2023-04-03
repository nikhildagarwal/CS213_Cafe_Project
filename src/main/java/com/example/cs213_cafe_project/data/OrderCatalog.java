package com.example.cs213_cafe_project.data;

import com.example.cs213_cafe_project.cofee.AddOn;
import com.example.cs213_cafe_project.cofee.Coffee;
import com.example.cs213_cafe_project.cofee.Size;
import com.example.cs213_cafe_project.donut.CakeDonut;
import com.example.cs213_cafe_project.donut.flavors.CakeFlavor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class OrderCatalog {

    private ArrayList<Order> orderCatalog;

    OrderCatalog(){
        this.orderCatalog = new ArrayList<>();
    }

    public void add(Order order){
        orderCatalog.add(order);
    }

    public void remove(int index){
        orderCatalog.remove(index);
    }

    public void remove(Order order){
        orderCatalog.remove(order);
    }

    public int size(){
        return orderCatalog.size();
    }

    public void clear(){
        while(orderCatalog.size()>0){
            orderCatalog.remove(0);
        }
    }

    public Order get(int index){
        return orderCatalog.get(index);
    }

    public int getIndex(Order order){
        for(int i = 0;i<orderCatalog.size();i++){
            if(orderCatalog.get(i).equals(order)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        return orderCatalog.toString();
    }

    public static void main(String[] args){
        CakeDonut cakeDonut = new CakeDonut(CakeFlavor.STRAWBERRY);
        int cakeDonutQuantity = 2;
        HashSet<AddOn> addOns = new HashSet<>();
        addOns.add(AddOn.CARAMEL);
        addOns.add(AddOn.MOCHA);
        Coffee coffee = new Coffee(Size.VENTI,addOns);
        int coffeeQuantity = 1;
        HashMap<MenuItem,Integer> orderList = new HashMap<>();
        orderList.put(cakeDonut,cakeDonutQuantity);
        orderList.put(coffee,coffeeQuantity);
        Order myOrder = new Order(98234,orderList);
        OrderCatalog orderCatalog = new OrderCatalog();
        orderCatalog.add(myOrder);
        orderCatalog.add(myOrder);
        System.out.println(orderCatalog);
        orderCatalog.clear();
        System.out.println(orderCatalog);
    }
}

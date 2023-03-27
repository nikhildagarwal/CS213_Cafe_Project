package com.example.cs213_cafe_project.donut.flavors;

public enum CakeFlavor {

    PLAIN("Plain Vanilla"),
    CHOCOLATE("Chocolate"),
    STRAWBERRY("Strawberry"),
    BLUEBERRY("Blueberry"),
    LEMON("Lemon"),
    VELVET("Red Velvet"),
    CARROT("Carrot"),
    PUMPKIN("Pumpkin");

    private final String flavor;

    CakeFlavor (String flavor){
        this.flavor = flavor;
    }

    public String getFlavor(){
        return flavor;
    }
}

package com.example.cs213_cafe_project.donut.flavors;

public enum HoleFlavor {

    PLAIN("Classic"),
    GLAZED("Glazed"),
    CHOCOLATE("Chocolate"),
    JELLY("Jelly"),
    PUMPKIN("Pumpkin");

    private final String flavor;

    HoleFlavor (String flavor){
        this.flavor = flavor;
    }

    public String getFlavor(){
        return flavor;
    }
}

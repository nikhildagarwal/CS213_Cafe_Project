package com.example.cs213_cafe_project.donut.flavors;

public enum YeastFlavor {

    PLAIN("Classic"),
    GLAZED("Glazed"),
    JELLY("Jelly"),
    CINNAMON("Cinnamon Sugar"),
    CREAM("Boston Cream"),
    SUGAR("Powdered Sugar");

    private final String flavor;

    YeastFlavor (String flavor){
        this.flavor = flavor;
    }

    public String getFlavor(){
        return flavor;
    }
}

package com.example.cs213_cafe_project.cofee;

public enum AddOn {

    SWEETCREAM("Sweet Cream"),
    FRENCHVANILLA("French Vanilla"),
    IRISHCREAM("Irish Cream"),
    CARAMEL("Caramel"),
    MOCHA("Mocha");

    private String addition;

    AddOn(String addition){
        this.addition = addition;
    }

    public String toString(){
        return addition;
    }

}

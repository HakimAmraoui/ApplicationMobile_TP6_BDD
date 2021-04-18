package com.example.bdd;

public class Donnee {
    private String name;
    private int size;
//    private int image;

    Donnee(String name, int size){
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }


}

package com.example.compsci.myapplication;

public class dataMaker {
    private static String energy; //provide each row of data into an object
    private String name;
    private static String protein;
    private static String fat;
    private static String carbs;
    private static String fiber;
    private static String sugars;

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public static String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public static String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public static String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public static String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public static String getSugars() {
        return sugars;
    }

    public void setSugars(String sugars) {
        this.sugars = sugars;
    }

    //intialize each variable in the constructor
    public dataMaker (String name, String energy, String protein, String fat, String carbs, String fiber, String sugars){
        this.name=name;
        this.energy=energy;
        this.protein=protein;
        this.fat=fat;
        this.carbs=carbs;
        this.fiber=fiber;
        this.sugars=sugars;
    }
}

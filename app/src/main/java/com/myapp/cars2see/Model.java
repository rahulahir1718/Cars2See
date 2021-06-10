package com.myapp.cars2see;

import java.io.Serializable;

public class Model implements Serializable {
    private String fuel_type,bold_type,name,image1,image2,image3,image4,fuel_tank_capacity,seating_capacity,mileage,price;
    private static Model model;

    private Model(){
    }

    public static Model getInstance(){
        if(model==null){
            model=new Model();
        }
        return model;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getBold_type() {
        return bold_type;
    }

    public void setBold_type(String bold_type) {
        this.bold_type = bold_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getFuel_tank_capacity() {
        return fuel_tank_capacity;
    }

    public void setFuel_tank_capacity(String fuel_tank_capacity) {
        this.fuel_tank_capacity = fuel_tank_capacity;
    }

    public String getSeating_capacity() {
        return seating_capacity;
    }

    public void setSeating_capacity(String seating_capacity) {
        this.seating_capacity = seating_capacity;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static Model getModel() {
        return model;
    }

    public static void setModel(Model model) {
        Model.model = model;
    }

    @Override
    public String toString() {
        return "Model{" +
                "fuel_type='" + fuel_type + '\'' +
                ", bold_type='" + bold_type + '\'' +
                ", name='" + name + '\'' +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", image3='" + image3 + '\'' +
                ", image4='" + image4 + '\'' +
                ", fuel_tank_capacity='" + fuel_tank_capacity + '\'' +
                ", seating_capacity='" + seating_capacity + '\'' +
                ", mileage='" + mileage + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

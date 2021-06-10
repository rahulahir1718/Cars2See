package com.myapp.cars2see;

public class Brands {

    private String name;
    private String image;
    private static Brands brands;

    private Brands(){

    }

    public static Brands getInstance(){
        if(brands==null){
            brands=new Brands();
        }

        return brands;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public void setName(String name){
        this.name=name;
    }

    public void setImage(String image){
        this.image=image;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }
}


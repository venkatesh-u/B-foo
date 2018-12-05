package com.venkatesh.foodapp.pojos;

public class DataModel {

    public String text;
    public int drawable, price, catId;
    public String color;

    public DataModel(String t, int d, String c, int pr, int id)
    {
        text=t;
        drawable=d;
        color=c;
        price = pr;
        catId = id;

    }
}

package com.ynov.rbncpyandroid.bo;

import java.io.Serializable;

public class Lodging implements Serializable {

    String id;
float price;
    String title;
    ImageBnb illustrations;

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public ImageBnb getIllustrations() {
        return illustrations;
    }

    public Lodging(String id, float price, String title, ImageBnb illustrations) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.illustrations = illustrations;
    }
}

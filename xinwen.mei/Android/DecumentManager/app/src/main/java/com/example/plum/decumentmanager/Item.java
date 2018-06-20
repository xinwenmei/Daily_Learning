package com.example.plum.decumentmanager;

/**
 * Created by plum on 2018/6/13.
 */

public class Item {

    private String name;

    private int imageId;


    public Item(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}

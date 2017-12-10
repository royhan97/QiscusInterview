package com.project.roy.qiscusinterview.Entity;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 12/6/2017.
 */

public class Product extends ExpandableGroup<Detail> {
    private Integer id;
    private String name;
    private String price;
    private String description;
    private ArrayList<Image> images;
    private ArrayList<Review> reviews;

//    public Product(Integer id, String name, String price, String description, ArrayList<Image> images, ArrayList<Review> reviews) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//        this.images = images;
//        this.reviews = reviews;
//    }

    public Product(String title, List<Detail> items, String price, ArrayList<Image> images) {
        super(title, items);
        this.price = price;
        this.images = images;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}

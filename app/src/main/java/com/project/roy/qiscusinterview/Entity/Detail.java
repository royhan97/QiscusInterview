package com.project.roy.qiscusinterview.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by roy on 12/10/2017.
 */

public class Detail implements Parcelable {

    private String description;
    private ArrayList<Review> reviews;

    public Detail(String description, ArrayList<Review> reviews) {
        this.description = description;
        this.reviews = reviews;
    }

    protected Detail(Parcel in) {
        this.description = in.readString();
        this.reviews = in.readArrayList(null);
    }

    public static final Creator<Detail> CREATOR = new Creator<Detail>() {
        @Override
        public Detail createFromParcel(Parcel in) {
            return new Detail(in);
        }

        @Override
        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeList(reviews);
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}

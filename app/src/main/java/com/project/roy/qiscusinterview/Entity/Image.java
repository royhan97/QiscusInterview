package com.project.roy.qiscusinterview.Entity;

/**
 * Created by roy on 12/6/2017.
 */

public class Image {
    private String full;
    private String thummb;

    public Image(String full, String thummb) {
        this.full = full;
        this.thummb = thummb;
    }

    public String getFull() {
        return full;
    }

    public String getThummb() {
        return thummb;
    }
}

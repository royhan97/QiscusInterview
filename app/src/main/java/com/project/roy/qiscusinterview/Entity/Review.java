package com.project.roy.qiscusinterview.Entity;

/**
 * Created by roy on 12/6/2017.
 */

public class Review {
    private String stars;
    private String body;
    private String authors;

    public Review(String stars, String body, String authors) {
        this.stars = stars;
        this.body = body;
        this.authors = authors;
    }

    public String getStars() {
        return stars;
    }

    public String getBody() {
        return body;
    }

    public String getAuthors() {
        return authors;
    }
}

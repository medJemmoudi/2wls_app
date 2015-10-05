package com.wls.jemmoudi.moviesapp.model;


public class Film {

    private int id;
    private String title;
    private String type;
    private String picture;
    private String synopsis;
    private String rating;

    public Film () {}

    public Film ( int id, String title, String type, String picture ) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }
}

package com.example.tubes01;

import android.widget.ImageView;

public class Film {

    private String title;
    private String synopsis;
    private ImageView poster;
    private Double rating;
    private String review;
    private boolean completedStatus;
    private String category;

    public Film(String title, String synopsis, ImageView poster, Double rating, String review, boolean completedStatus, String category){
        this.title = title;
        this.synopsis = synopsis;
        this.poster = poster;
        this.rating = rating;
        this.review = review;
        this.completedStatus = completedStatus;
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setPoster(ImageView poster) {
        this.poster = poster;
    }

    public ImageView getPoster() {
        return this.poster;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return this.review;
    }

    public void setCompletedStatus(boolean completedStatus) {
        this.completedStatus = completedStatus;
    }

    public boolean isCompletedStatus() {
        return this.completedStatus;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }

}

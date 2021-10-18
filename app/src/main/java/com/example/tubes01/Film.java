package com.example.tubes01;

import android.widget.ImageView;

public class Film {

    private String title;
    private String synopsis;
    private ImageView poster;
    private Double rating;
    private String review;
    private boolean completedStatus;

    public Film (String title, String synopsis, ImageView poster, Double rating, String review, boolean completedStatus){
        this.title = title;
        this.synopsis = synopsis;
        this.poster = poster;
        this.rating = rating;
        this.review = review;
        this.completedStatus = completedStatus;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompletedStatus() {
        return completedStatus;
    }

    public Double getRating() {
        return rating;
    }

    public ImageView getPoster() {
        return poster;
    }

    public String getReview() {
        return review;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setCompletedStatus(boolean completedStatus) {
        this.completedStatus = completedStatus;
    }

    public void setPoster(ImageView poster) {
        this.poster = poster;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

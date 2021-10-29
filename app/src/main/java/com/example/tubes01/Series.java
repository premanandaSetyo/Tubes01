package com.example.tubes01;

import android.widget.ImageView;

public class Series {
    private String title;
    private int eps;
    private String synopsis;
    private Float rating;
    private String review;
    private boolean completedStatus;

    public Series(String title, int eps, String synopsis, Float rating, String review, boolean completedStatus){
        this.title = title;
        this.eps = eps;
        this.synopsis = synopsis;
        this.rating = rating;
        this.review = review;
        this.completedStatus = completedStatus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setEps(int eps) {
        this.eps = eps;
    }

    public int getEps() {
        return eps;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getRating() {
        return rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setCompletedStatus(boolean completedStatus) {
        this.completedStatus = completedStatus;
    }

    public boolean isCompletedStatus() {
        // false 0: blm di review
        // true 1 : udah di review
        return completedStatus;
    }


}

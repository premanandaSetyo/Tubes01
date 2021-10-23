package com.example.tubes01;

import android.widget.ImageView;

public class Series {
    private String eps;
    private String sysnopsis;
    private ImageView poster;
    private Float rating;
    private String review;
    private boolean completedStatus;

    public Series(String eps, String sysnopsis, ImageView poster, Float rating, String review, boolean completedStatus){
        this.eps = eps;
        this.sysnopsis = sysnopsis;
        this.poster = poster;
        this.rating = rating;
        this.review = review;
        this.completedStatus = completedStatus;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getEps() {
        return eps;
    }

    public void setSysnopsis(String sysnopsis) {
        this.sysnopsis = sysnopsis;
    }

    public String getSysnopsis() {
        return sysnopsis;
    }

    public void setPoster(ImageView poster) {
        this.poster = poster;
    }

    public ImageView getPoster() {
        return poster;
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
        return completedStatus;
    }

}

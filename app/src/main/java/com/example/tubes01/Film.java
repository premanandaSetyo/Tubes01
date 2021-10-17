package com.example.tubes01;

import android.widget.ImageView;

public class Film {

    private String title;
    private int episode;
    private String synopsis;
    private ImageView poster;
    private Double rating;
    private String review;
    private boolean completedStatus;

    public Film (String title, int episode, String synopsis, ImageView poster, Double rating, String review, boolean completedStatus){
        this.title = title;
        this.episode = episode;
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

    public int getEpisode() {
        return episode;
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

    public void setEpisode(int episode) {
        this.episode = episode;
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

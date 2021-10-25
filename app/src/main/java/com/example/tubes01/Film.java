package com.example.tubes01;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Film {
    private String title;
    private String synopsis;
    private Bitmap poster;
    private int episode;
    private float rating;
    private String review;
    private boolean completedStatus;
    private String category;
    private int index;
    private boolean droppedStatus;

    public Film(String title, String synopsis, Bitmap poster, int episode, float rating, String review, boolean completedStatus, String category, int index, boolean droppedStatus){
        this.title = title;
        this.synopsis = synopsis;
        this.poster = poster;
        this.episode = episode;
        this.rating = rating;
        this.review = review;
        this.completedStatus = completedStatus;
        this.category = category;
        this.droppedStatus = droppedStatus;
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

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }

    public Bitmap getPoster() {
        return this.poster;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public int getEpisode() {
        return episode;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
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

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setDroppedStatus(boolean droppedStatus) {
        this.droppedStatus = droppedStatus;
    }

    public boolean isDroppedStatus() {
        return droppedStatus;
    }
}

package com.example.tubes01;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Comparator;

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

    public static Comparator<Film> SortTitleAZ = new Comparator<Film>() {
        @Override
        public int compare(Film film1, Film film2) {
            return film1.getTitle().compareTo(film2.getTitle());
        }
    };

    public static Comparator<Film> SortTitleZA = new Comparator<Film>() {
        @Override
        public int compare(Film film1, Film film2) {
            return film2.getTitle().compareTo(film1.getTitle());
        }
    };

    public static Comparator<Film> SortRatingAsc = new Comparator<Film>() {
        @Override
        public int compare(Film film1, Film film2) {
            int res = 0;
//            if(film1.getRating()!=0.0F && film2.getRating()!=0.0F){
//                res = film1.getRating().compareTo(film2.getRating());
//            }
            return res;
//            return film1.getRating().compareTo(film2.getRating();
        }
    };

    public static Comparator<Film> SortRatingDesc = new Comparator<Film>() {
        @Override
        public int compare(Film film1, Film film2) {
            int res = 0;
//            if(film1.getRating()!=null && film2.getRating()!=null){
//                res = film2.getRating().compareTo(film1.getRating());
//            }
            return res;
//            return film2.getRating().compareTo(film1.getRating());
        }
    };

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

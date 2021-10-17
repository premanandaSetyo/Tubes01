package com.example.tubes01;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainPresenter {
    protected ArrayList<Film> listFilmP;
    protected IMainActivity ui;
    private Film currFilm;

    public MainPresenter(MainActivity view){
        this.ui = (IMainActivity) view;
        this.listFilmP = new ArrayList<Film>();
    }

    public void addMovie(String title, String synopsis, ImageView poster){
        currFilm = new Film(title, 1, synopsis, poster, null, null, false);
        listFilmP.add(currFilm);
    }

    public void addSeries(String title, String synopsis, ImageView poster, int eps){
        for(int e = 1;e<=eps;e++){
            currFilm = new Film(title, e, synopsis, poster, null, null, false);
            listFilmP.add(currFilm);
        }
    }

    public void addReview(String review, Double rating, int position){
        this.listFilmP.get(position).setReview(review);
        this.listFilmP.get(position).setRating(rating);
        this.listFilmP.get(position).setCompletedStatus(true);
    }

    public void delete(int position){
        this.listFilmP.remove(position);
    }

}

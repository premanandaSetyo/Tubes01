package com.example.tubes01;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainPresenter {
    protected List<Film> listFilmP;
    protected IMainActivity ui;
    private Film currFilm;

    public MainPresenter(MainActivity view){
        this.ui = (IMainActivity) view;
        this.listFilmP = new ArrayList<Film>();
    }

    public void addMovie(String title, String synopsis, ImageView poster){
        currFilm = new Film(title, synopsis, poster, null, null, false);
        listFilmP.add(currFilm);
    }

    public void addSeries(String title, String synopsis, ImageView poster, int eps){
        currFilm = new Film(title, synopsis, poster, null, null, false);
        listFilmP.add(currFilm);
        List<Series> ls = new ArrayList<Series>();
        for(int e = 1;e<=eps;e++){
            Series currSeries = new Series(e,synopsis,poster,null,null,false);
            ls.add(currSeries);
        }
//        Collection<Series> col = new ArrayList<>(ls);
//        listFilmP.addAll(ls);
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

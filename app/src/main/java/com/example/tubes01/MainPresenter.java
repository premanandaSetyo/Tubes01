package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.LongDef;

import com.example.tubes01.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainPresenter {
    protected List<Film> listFilmP;
    protected List<Series> listSeriesP;
    protected IMainActivity ui;
    protected int index = 1;
    private Film currFilm;
    protected static MainPresenter singleton;

    public MainPresenter(IMainActivity view){
        this.ui = view;
        this.listFilmP = new ArrayList<Film>();
        this.listSeriesP = new ArrayList<Series>();
    }

    public static MainPresenter getMainPresenter(IMainActivity view){
        if(MainPresenter.singleton == null){
            MainPresenter.singleton = new MainPresenter(view);
        }
        return MainPresenter.singleton;
    }

    public void addMovie(String title, String synopsis, ImageView poster){
        currFilm = new Film(title, synopsis, poster, 1, 0.0F, null, false, "movie", 0);
        this.listFilmP.add(currFilm);
        this.ui.updateList(this.listFilmP);
    }

    public void addSeries(String title, String synopsis, ImageView poster, int eps){
        currFilm = new Film(title, synopsis, poster, eps, 0.0F, null, false, "series", this.index);
        this.listFilmP.add(currFilm);
        for(int e=1; e<=eps; e++){
            Series currSeries = new Series(("Episode " + e), synopsis, poster,0.0F,null,false);
            this.listSeriesP.add(currSeries);
        }
        Log.d("addSeries", "UDAH MASUK WOI");
        this.ui.updateList(this.listFilmP);
        this.ui.updateSeries(this.listSeriesP);
    }

    public void addReview(String review, float rating, int position){
        currFilm = (Film) this.listFilmP.get(position);
        currFilm.setReview(review);
        currFilm.setRating(rating);
        currFilm.setCompletedStatus(true);
        this.listFilmP.set(position, currFilm);
        Log.d("review", currFilm.getReview());
        Log.d("rating", String.valueOf(currFilm.getRating()));
        this.ui.sendData(currFilm, position);
    }

    public void delete(int position){
        this.listFilmP.remove(position);
    }

    public void loadData(Film[] arrFilm){
        if(listFilmP.isEmpty()){
            List<Film> film = Arrays.asList(arrFilm);
            for(Film f : film){
                this.listFilmP.add(f);
            }
            this.ui.updateList(this.listFilmP);
        }
    }

    public void getData(int position){
        currFilm = this.listFilmP.get(position);
        this.ui.sendData(currFilm, position);
    }

    public void changePage(int page){
        this.ui.changePage(page);
    }


//    loadData
//    saveData
//    reviewUlang

//    METHOD BUAT UPLOAD IMAGE
//    - encode
//    - decode --> view
//    - save

}

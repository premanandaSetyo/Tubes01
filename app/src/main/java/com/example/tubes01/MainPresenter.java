package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.tubes01.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainPresenter {
    protected List<Film> listFilmP;
    protected IMainActivity ui;
    private Film currFilm;
    protected static MainPresenter singleton;

    public MainPresenter(IMainActivity view){
        this.ui = view;
        this.listFilmP = new ArrayList<Film>();
    }

    public static MainPresenter getMainPresenter(IMainActivity view){
        if(MainPresenter.singleton == null){
            MainPresenter.singleton = new MainPresenter(view);
        }
        return MainPresenter.singleton;
    }

    public void addMovie(String title, String synopsis, ImageView poster){
        Log.d("test", "addMovie Presenter masuk");
        currFilm = new Film(title, synopsis, poster, 1, 0.0F, null, false, "movie", 0);
        this.listFilmP.add(currFilm);
//        for(Film f : listFilmP){
//            Log.d("judul", f.getTitle());
//        }
        this.ui.updateList(this.listFilmP);
    }

//    public void addSeries(String title, String synopsis, ImageView poster, int eps){
//        currFilm = new Film(title, synopsis, poster, null, null, false, "series");
//        listFilmP.add(currFilm);
//        List<Series> ls = new ArrayList<Series>();
//        for(int e = 1;e<=eps;e++){
//            Series currSeries = new Series(e,synopsis,poster,null,null,false);
//            ls.add(currSeries);
//        }
//        listFilmP.add(ls);
//    }

    public void addReview(String review, float rating, int position){
        currFilm = (Film) this.listFilmP.get(position);
        currFilm.setReview(review);
        currFilm.setRating(rating);
        currFilm.setCompletedStatus(true);
        this.listFilmP.add(position, currFilm);
    }

    public void delete(int position){
        this.listFilmP.remove(position);
    }

    public void loadData(Film[] arrFilm){
        List<Film> film = Arrays.asList(arrFilm);
        for(Film f : film){
            this.listFilmP.add(f);
        }
        this.ui.updateList(this.listFilmP);
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

}

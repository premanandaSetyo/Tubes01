package com.example.tubes01;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.LongDef;

//import com.example.tubes01.databinding.FragmentHomeBinding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainPresenter {
    protected List<Film> listFilmP;
    protected List<Series> listSeriesP;
    protected IMainActivity ui;
    protected int index = 1;
    private Film currFilm;
    private Series currSeries;
    private DbHelper db;
//    protected static MainPresenter singleton;
    private Context context;
    private String title;
    private int position; ////////////

    public MainPresenter(IMainActivity view, MainActivity activity){
        this.ui = view;
        this.listFilmP = new ArrayList<Film>();
        this.listSeriesP = new ArrayList<Series>();
        this.context = activity.getBaseContext();
        this.db = new DbHelper(this.context);
    }

//    public static MainPresenter getMainPresenter(IMainActivity view){
//        if(MainPresenter.singleton == null){
//            MainPresenter.singleton = new MainPresenter(view);
//        }
//        return MainPresenter.singleton;
//    }

    public byte[] bitmapToBytes(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public Bitmap decodeToBitmap(byte[] byteArray){
        Log.d("Bitmap decode", String.valueOf(byteArray[0]));
        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bm;
    }


    public void loadFilmData(){
        Cursor data = db.getAllFilm();
        Film currfilm;
        while(data.moveToNext()){
            String judul = data.getString(1);
            String synopsis = data.getString(2);
            byte[] poster = data.getBlob(3);
            Float rating = data.getFloat(4);
            String review = data.getString(5);
            boolean completedStatus = data.getInt(6)==1?true:false;
            String category = data.getString(7);
            int idx = data.getInt(8);
            int eps = data.getInt(9);
            boolean droppedStatus = data.getInt(10)==1?true:false;
            currfilm = new Film(judul,synopsis, poster, eps, rating, review,completedStatus,category, idx,droppedStatus);
            this.listFilmP.add(currfilm);
        }
        this.ui.updateList(this.listFilmP);
    }

//    public void sendTitle(String title){
//        this.title = title;
//    }


    public void loadSeriesData(String title){
//    public void loadSeriesData(int position){
//        Log.d("presenterLoadSeriesData", String.valueOf(position));
//        Cursor data = db.getAllSeries();
//        currFilm = this.listFilmP.get(position);
//        String title = currFilm.getTitle();
        this.listSeriesP = new ArrayList<>();
        Cursor data = db.getSeries(title);
        Series currseries;
        while(data.moveToNext()){
            String judul = data.getString(1);
            String synopsis = data.getString(2);
            Float rating = data.getFloat(3);
            String review = data.getString(4);
            int eps = data.getInt(5);
            boolean completedStatus = data.getInt(6)==1?true:false;
            currseries = new Series(judul, eps, synopsis, rating, review, completedStatus);
            listSeriesP.add(currseries);
        }
        this.ui.updateSeries(this.listSeriesP);
        Log.d("ArrSeriesLength", String.valueOf(listSeriesP.size()));
    }

    public void addMovie(String title, String synopsis, Bitmap poster){
        String data = db.checkTitle(title);
        if(data==null){
            byte[] tempPoster = bitmapToBytes(poster);
            boolean insertDataStatus = db.addDataFilm(title, synopsis, tempPoster, 0.0F, null, false, "movies", 0, 1, 0);
            if(insertDataStatus==true){
                this.ui.makeToastMessage("Movie successfully added. ");
            }else{
                this.ui.makeToastMessage("Failed to add movie. ");
            }
            Log.d("Debug add movie true", String.valueOf(listFilmP.size()));
        }else{
            this.ui.makeToastMessage("Movie title exists. ");
            Log.d("Debug add movie false", String.valueOf(listFilmP.size()));
        }
        this.loadFilmData();
        this.ui.resetForm();

//        this.db.addDataFilm(title, synopsis, poster, 1, 0.0F, null, false, "movie", 0);
//        currFilm = new Film(title, synopsis, poster, 1, 0.0F, null, false, "movie", 0);
//        this.listFilmP.add(currFilm);
//        this.ui.updateList(this.listFilmP);
//        this.ui.resetForm();
    }

    public void addSeries(String title, String synopsis, Bitmap poster, int eps){
        String data = db.checkTitle(title.toLowerCase());
        if(data==null){
            byte[] tempPoster = bitmapToBytes(poster);
            boolean insertDataFilmStatus = db.addDataFilm(title, synopsis, tempPoster, 0.0F, null, false, "series", this.index, eps, 0);
            if(insertDataFilmStatus==true){
                for(int e=1; e<=eps; e++){
                    boolean insertDataSeriesStatus = db.addDataSeries(title,synopsis,0.0F,null, e,false);
                }
                this.ui.makeToastMessage("Movie successfully added. ");
            }else{
                this.ui.makeToastMessage("Failed to add movie. ");
            }
        }else{
            this.ui.makeToastMessage("Movie title exists. ");
        }
        this.loadFilmData();
//        this.loadSeriesData();
        this.ui.resetForm();
        this.index+=eps;

//        currFilm = new Film(title, synopsis, poster, eps, 0.0F, null, false, "series", this.index);
//        this.listFilmP.add(currFilm);
//        for(int e=1; e<=eps; e++){
//            Series currSeries = new Series(("Episode " + e), synopsis,0.0F,null,false);
//            this.listSeriesP.add(currSeries);
//        }
//        Log.d("addSeries", "UDAH MASUK WOI");
//        this.ui.updateList(this.listFilmP);
//        this.ui.updateSeries(this.listSeriesP);
//        this.ui.resetForm();
    }

    public void addReview(String review, float rating, int position){
        this.loadFilmData();
        currFilm = this.listFilmP.get(position);
        String title = currFilm.getTitle();
        Log.d("addReview Presenter", title);
        boolean addRev = this.db.updateDataFilm(review,1, rating, title);
        if(addRev==true){
            this.ui.makeToastMessage("Review successfully added.");
        }else{
            this.ui.makeToastMessage("Can't add review.");
        }
        this.loadFilmData();

        Log.d("debug size", String.valueOf(this.listFilmP.size()));
        this.ui.resetForm();


//        Log.d("film rating", String.valueOf(currFilm.getRating()));
//        Log.d("film review", currFilm.getReview());

//        currFilm = (Film) this.listFilmP.get(position);
//        currFilm.setReview(review);
//        currFilm.setRating(rating);
//        currFilm.setCompletedStatus(true);
//        this.listFilmP.set(position, currFilm);
//        Log.d("review", currFilm.getReview());
//        Log.d("rating", String.valueOf(currFilm.getRating()));
//        this.ui.sendData(currFilm, position);
//        this.ui.resetForm();
    }

    public void addSeriesReview(String review, float rating, int position, String title, int eps) {
        this.loadSeriesData(title);
//        Log.d("sebelum", String.valueOf(this.listSeriesP.size()));
//        currSeries = this.listSeriesP.get(position);
//        String title = currSeries.getTitle();
//        Log.d("addReview Presenter", title);
        boolean addRev = this.db.updateDataSeries(review, 1, rating, title, eps);
        if (addRev == true) {
            this.ui.makeToastMessage("Review successfully added.");
        } else {
            this.ui.makeToastMessage("Can't add review.");
        }
//        if(this.listSeriesP.size()<){
            this.loadSeriesData(title);
//        }

        Log.d("setelah", String.valueOf(this.listSeriesP.size()));
//        this.loadSeriesData(position);

        Log.d("debug size", String.valueOf(this.listSeriesP.size()));
        this.ui.resetForm();
    }

    public void delete(int position){
        this.listFilmP.remove(position);
    }

    public void dropFilm(int position){
        loadFilmData();
        currFilm = this.listFilmP.get(position);
        String title = currFilm.getTitle();
        boolean addRev = this.db.dropF(title);
        if(addRev==true){
            this.ui.makeToastMessage("Film successfully dropped.");
        }else{
            this.ui.makeToastMessage("Failed to drop film.");
        }
        this.loadFilmData();
    }


//    public void loadData(Film[] arrFilm){
//        if(listFilmP.isEmpty()){
//            List<Film> film = Arrays.asList(arrFilm);
//            for(Film f : film){
//                this.listFilmP.add(f);
//            }
//            this.ui.updateList(this.listFilmP);
//        }
//    }

//    public void getData(int position){
//        this.loadFilmData();
//        currFilm = this.listFilmP.get(position);
//        this.ui.sendData(currFilm, position);
//    }

    public void getData(int position){
        this.loadFilmData();
        Log.d("getData Presenter", String.valueOf(position));
        currFilm = this.listFilmP.get(position);
        String title = currFilm.getTitle();
        byte[] poster = currFilm.getPoster();
        String synopsis = currFilm.getSynopsis();
        int episode = currFilm.getEpisode();
        boolean status = currFilm.isCompletedStatus();
        float rating = currFilm.getRating();
        String review = currFilm.getReview();
        Log.d("getData review", title);
        this.ui.sendData(position, title, synopsis, poster, episode, status, rating, review);
    }

    public void getSeriesData(int position, String title){
        this.loadSeriesData(title);
        Log.d("getData Presenter", String.valueOf(position));
        currSeries = this.listSeriesP.get(position);
        title = currSeries.getTitle();
        byte[] poster = null;
        String synopsis = currSeries.getSynopsis();
        int episode = currSeries.getEps();
        boolean status = currSeries.isCompletedStatus();
        float rating = currSeries.getRating();
        String review = currSeries.getReview();
        Log.d("getSeriesData Presenter", title);
        Log.d("getSeriesData eps", String.valueOf(episode));
        Log.d("getSeriesData pos", String.valueOf(position));
        this.ui.sendData(position, title, synopsis, poster, episode, status, rating, review);
    }

    public void changePage(int page){
//        Log.d("cpPresenter",String.valueOf(page));
        this.ui.changePage(page);
    }

    public Intent getImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setAction(Intent.ACTION_PICK);
        return intent;
    }





    //search film list
    public void filterView(String input){
        ArrayList<Film> filteredFilm = new ArrayList<>();
        for(Film film: listFilmP){
            if(film.getTitle().toLowerCase().contains(input.toLowerCase())){
                filteredFilm.add(film);
            }
        }
        this.ui.updateList(filteredFilm);
    }

    //sort film list
    public void sortView(int type){
        if(type==1){
            Collections.sort(listFilmP, Film.SortTitleAZ);
        }
        else if(type==2){
            Collections.sort(listFilmP, Film.SortTitleZA);
        }
        else if(type==3){
            Collections.sort(listFilmP, Film.SortRatingAsc);
        }
        else if(type==4){
            Collections.sort(listFilmP, Film.SortRatingDesc);
        }
        this.ui.updateList(listFilmP);
    }

//    loadData
//    saveData
//    reviewUlang

//    METHOD BUAT UPLOAD IMAGE
//    - encode
//    - decode --> view
//    - save

}

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
    private Context context;

    public MainPresenter(IMainActivity view, MainActivity activity){
        this.ui = view;
        this.listFilmP = new ArrayList<Film>();
        this.listSeriesP = new ArrayList<Series>();
        this.context = activity.getBaseContext();
        this.db = new DbHelper(this.context);
    }

    public byte[] bitmapToBytes(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public Bitmap decodeToBitmap(byte[] byteArray){
        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bm;
    }

    public void loadFilmData(){
        this.listFilmP = new ArrayList<>();
        Cursor data = db.getAllFilm();
        Film currfilm;
        while(data.moveToNext()){
            String judul = data.getString(1);
            String synopsis = data.getString(2);
            byte[] poster = data.getBlob(3);
            Float rating = data.getFloat(4);
            String review = data.getString(5);
            int status = data.getInt(6);
            String category = data.getString(7);
            int idx = data.getInt(8);
            int eps = data.getInt(9);
            boolean droppedStatus = data.getInt(10)==1?true:false;
            currfilm = new Film(judul,synopsis, poster, eps, rating, review, status, category, idx, droppedStatus);
            this.listFilmP.add(currfilm);
        }
        this.ui.updateList(this.listFilmP);
    }

    public void loadSeriesData(String title){
        int countReview = 0;
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
            if(completedStatus==true){
                countReview++;
            }
        }
        this.ui.updateSeries(this.listSeriesP);
        if(countReview==listSeriesP.size()){ //completed
            boolean updateStat = this.db.updateFilmStatus(1, title);
        }
        else{ //inprogrss
            boolean updateStat = this.db.updateFilmStatus(2, title);
        }
        loadFilmData();
    }

    public void addMovie(String title, String synopsis, Bitmap poster){
        String data = db.checkTitle(title);
        if(data==null){
            byte[] tempPoster = bitmapToBytes(poster);
            boolean insertDataStatus = db.addDataFilm(title, synopsis, tempPoster, 0.0F, null, 0, "movies", 0, 1, 0);
            if(insertDataStatus==true){
                this.ui.makeToastMessage("Movie successfully added. ");
            }else{
                this.ui.makeToastMessage("Failed to add movie. ");
            }
        }else{
            this.ui.makeToastMessage("Movie title exists. ");
        }
        this.loadFilmData();
        this.ui.resetForm();
    }

    public void addSeries(String title, String synopsis, Bitmap poster, int eps){
        String data = db.checkTitle(title.toLowerCase());
        if(data==null){
            byte[] tempPoster = bitmapToBytes(poster);
            boolean insertDataFilmStatus = db.addDataFilm(title, synopsis, tempPoster, 0.0F, null, 0, "series", this.index, eps, 0);
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
        this.ui.resetForm();
        this.index+=eps;
    }

    public void addReview(String review, float rating, int position){
        this.loadFilmData();
        currFilm = this.listFilmP.get(position);
        String title = currFilm.getTitle();
        boolean addRev = this.db.updateDataFilm(review,1, rating, title);
        if(addRev==true){
            this.ui.makeToastMessage("Review successfully added.");
        }else{
            this.ui.makeToastMessage("Can't add review.");
        }
        this.loadFilmData();
        this.ui.resetForm();
    }

    public void addSeriesReview(String review, float rating, int position, String title, int eps) {
        this.loadSeriesData(title);
        boolean addRev = this.db.updateDataSeries(review, 1, rating, title, eps);
        if (addRev == true) {
            this.ui.makeToastMessage("Review successfully added.");
        } else {
            this.ui.makeToastMessage("Can't add review.");
        }
        this.loadSeriesData(title);
        this.ui.resetForm();
    }

    public void delete(int position){
        this.loadFilmData();
        currFilm = this.listFilmP.get(position);
        String title = currFilm.getTitle();
        boolean delFilm = this.db.deleteFilm(title);
        if(delFilm==true){
            this.ui.makeToastMessage("Film successfully deleted.");
        }else{
            this.ui.makeToastMessage("Can't delete film.");
        }
        this.loadFilmData();
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

    public void getData(int position){
        this.loadFilmData();
        currFilm = this.listFilmP.get(position);
        String title = currFilm.getTitle();
        byte[] poster = currFilm.getPoster();
        String synopsis = currFilm.getSynopsis();
        int episode = currFilm.getEpisode();
        int status = currFilm.getStatus();
        float rating = currFilm.getRating();
        String review = currFilm.getReview();
        this.ui.sendData(position, title, synopsis, poster, episode, status, rating, review);
    }

    public void getSeriesData(int position, String title){
        this.loadSeriesData(title);
        currSeries = this.listSeriesP.get(position);
        title = currSeries.getTitle();
        byte[] poster = null;
        String synopsis = currSeries.getSynopsis();
        int episode = currSeries.getEps();
        int status = currSeries.isCompletedStatus()==true?1:0;
        float rating = currSeries.getRating();
        String review = currSeries.getReview();
        this.ui.sendData(position, title, synopsis, poster, episode, status, rating, review);
    }

    public void changePage(int page){
        this.ui.changePage(page);
    }

    public Intent getImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        return intent;
    }


    //status
    public String printStatus(int position){
        loadFilmData();
        currFilm = this.listFilmP.get(position);
        if(currFilm.isDroppedStatus()){
            return "Dropped";
        }
        else{
            int code = currFilm.getStatus();
            if(code == 0){
                return "Waiting List";
            }
            else if(code == 1){
                return "Completed";
            }
            else{
                return "In Progress";
            }
        }
    }

    //progress
    public int completedEps(String title){
        loadSeriesData(title);
        int count = 0;
        for(Series s : this.listSeriesP){
            if(s.isCompletedStatus()==true){
                count++;
            }
        }
        return count;
    }

    public float avgRating(String title){
//        loadSeriesData(title);
        float sum = 0;
        for(Series s : this.listSeriesP){
            if(completedEps(title)==this.listSeriesP.size()){
                sum+=s.getRating();
            }
        }
        return sum/this.listSeriesP.size();
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


}

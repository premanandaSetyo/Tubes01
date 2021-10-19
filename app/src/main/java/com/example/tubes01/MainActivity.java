package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubes01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    private Toolbar toolbar;
    FragmentManager fragmentManager;
    private HomeFragment home;
    private AddMovieFragment addMovie;
    private FilmListFragment filmList;
    private AddSeriesFragment addSeries;
    private ViewFilmFragment viewFilm;
    private ReviewPageFragment reviewPage;
    private ViewFilmReviewedFragment viewFilmReviewed;
//    private SeriesListFragment seriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        filmList = FilmListFragment.newInstance(this);
//        setContentView(R.layout.activity_main);

        //toolbar
        this.toolbar = findViewById(R.id.toolbar);
        this.toolbar.setTitle("Ma Montre"); //Judul aplikasi di toolbar
        this.setSupportActionBar(this.toolbar);

        //tombol garis tiga
        DrawerLayout drawer  = this.binding.drawerLayout;
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();


        this.home = HomeFragment.newInstance();
        this.filmList = FilmListFragment.newInstance(this);
        this.addMovie = AddMovieFragment.newInstance();
        this.addSeries = AddSeriesFragment.newInstance();
        this.viewFilm = ViewFilmFragment.newInstance();
        this.reviewPage = ReviewPageFragment.newInstance();
        this.viewFilmReviewed = ViewFilmReviewedFragment.newInstance();
        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if(savedInstanceState == null){
            this.changePage(1);
        }

        //page 1
//        this.home = HomeFragment.newInstance();
//        ft.add(R.id.fragment_container, this.home)
//                .addToBackStack(null)
//                .commit();

        //page 2
//        this.filmList = FilmListFragment.newInstance();
//        ft.add(R.id.fragment_container, this.filmList)
//                .commit();

        //page 3
//        this.addMovie = AddMovieFragment.newInstance();
//        ft.add(R.id.fragment_container, this.addMovie)
//                .commit();

        //page 4
//        this.addSeries = AddSeriesFragment.newInstance();
//        ft.add(R.id.fragment_container, this.addSeries)
//                .commit();

        //page 6
//        this.viewFilm = ViewFilmFragment.newInstance();
//        ft.add(R.id.fragment_container, this.viewFilm)
//                .commit();

        //page 7
//        this.reviewPage = ReviewPageFragment.newInstance();
//        ft.add(R.id.fragment_container, this.reviewPage)
//                .commit();

        //page 8
//        this.viewFilmReviewed = ViewFilmReviewedFragment.newInstance();
//        ft.add(R.id.fragment_container, this.viewFilmReviewed)
//                .commit();

        this.getSupportFragmentManager().setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("masuk", "activity");
                int page = result.getInt("page");
                changePage(page);
            }
        });



    }

    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            ft.replace(R.id.fragment_container, this.home)
                    .addToBackStack(null);
        }
        else if(page==2){
            ft.replace(R.id.fragment_container, this.filmList)
                    .addToBackStack(null);
        }
        else if(page==3){
            ft.replace(R.id.fragment_container, this.addMovie)
                    .addToBackStack(null);
        }
        else if(page==4){
            ft.replace(R.id.fragment_container, this.addSeries)
                    .addToBackStack(null);
        }
        else if(page==5){
//                ft.replace(R.id.fragment_container, this.seriesList)
//                        .addToBackStack(null);
        }
        else if(page==6) {
            ft.replace(R.id.fragment_container, this.viewFilm)
                    .addToBackStack(null);
        }
        else if(page==7) {
            ft.replace(R.id.fragment_container, this.reviewPage)
                    .addToBackStack(null);
        }
        else if(page==8) {
            ft.replace(R.id.fragment_container, this.viewFilmReviewed)
                    .addToBackStack(null);
        }
        ft.commit();
    }


    public void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }


}
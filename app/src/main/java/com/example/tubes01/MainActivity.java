package com.example.tubes01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //page 1
//        HomeFragment home = HomeFragment.newInstance();
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.fragment_container, home)
//                .commit();

        //page 3
//        AddMovieFragment addMovie = AddMovieFragment.newInstance();
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.fragment_container, addMovie)
//                .commit();

        //page 4
//        AddSeriesFragment addSeries = AddSeriesFragment.newInstance();
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.fragment_container, addSeries)
//                .commit();

        //page 5
//        FilmListFragment filmList = FilmListFragment.newInstance();
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.fragment_container, filmList)
//                .commit();

        //page 6
        ViewFilmFragment viewFilm = ViewFilmFragment.newInstance();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, viewFilm)
                .commit();

    }
}
package com.example.tubes01;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.tubes01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
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






        //page 1
        HomeFragment home = HomeFragment.newInstance();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, home)
                .commit();

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

//        //page 6
//        ViewFilmFragment viewFilm = ViewFilmFragment.newInstance();
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.fragment_container, viewFilm)
//                .commit();

        //page 8
//        ViewFilmReviewedFragment viewFilmReviewed = ViewFilmReviewedFragment.newInstance();
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.fragment_container, viewFilmReviewed)
//                .commit();

        //page 7
//        ReviewPageFragment reviewPage = ReviewPageFragment.newInstance();
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.fragment_container, reviewPage)
//                .commit();
    }
}
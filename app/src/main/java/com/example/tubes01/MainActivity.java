package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubes01.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IMainActivity {
    private ActivityMainBinding binding;
    private Toolbar toolbar;
    FragmentManager fragmentManager;
    private HomeFragment home; //page 1
    private FilmListFragment filmList; //page 2
    private AddMovieFragment addMovie; //page 3
    private AddSeriesFragment addSeries; //page 4
    private SeriesListFragment seriesList; //page 5
    private ViewFilmFragment viewFilm; //page 6
    private ReviewPageFragment reviewPage; //page 7
    private ViewFilmReviewedFragment viewFilmReviewed; //page 8
    private ViewSeriesFragment viewSeries; //page 9
    private NavigationView navView;
    private DrawerLayout drawer;

    private MainPresenter presenter;
//    private FilmListAdapter adapter;
//    private IMainActivity ui;

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
        this.drawer = this.binding.drawerLayout;
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        //Nav View
        this.navView = binding.navView;
        this.navView.setNavigationItemSelectedListener(this);

//        this.presenter = new MainPresenter(this);
//        this.adapter = new FilmListAdapter(this, this.presenter);
        

//        this.filmList = FilmListFragment.newInstance(this, this.presenter);
        this.filmList = new FilmListFragment(this);
//        this.filmList = new FilmListFragment(this, this.presenter, this.adapter);
//        this.addMovie = AddMovieFragment.newInstance(this, this.presenter);
        this.addMovie = new AddMovieFragment(this);
        this.reviewPage = new ReviewPageFragment(this);


        this.home = HomeFragment.newInstance();
//        this.filmList = FilmListFragment.newInstance();
//        this.addMovie = AddMovieFragment.newInstance();
        this.addSeries = AddSeriesFragment.newInstance();
        this.seriesList = SeriesListFragment.newInstance();
        this.viewFilm = ViewFilmFragment.newInstance();
//        this.reviewPage = ReviewPageFragment.newInstance();
        this.viewFilmReviewed = ViewFilmReviewedFragment.newInstance();
        this.viewSeries = ViewSeriesFragment.newInstance();

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

        //Change page
        this.getSupportFragmentManager().setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
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
                ft.replace(R.id.fragment_container, this.seriesList)
                        .addToBackStack(null);
        }
        else if(page==6) {
            ft.replace(R.id.fragment_container, this.viewFilm)
                    .addToBackStack(null);
        }
        else if(page==7) {
            ft.replace(R.id.fragment_container, this.reviewPage);
//                    .addToBackStack(null);
        }
        else if(page==8) {
            ft.replace(R.id.fragment_container, this.viewFilmReviewed)
                    .addToBackStack(null);
        }
        else if(page==9) {
            ft.replace(R.id.fragment_container, this.viewSeries)
                    .addToBackStack(null);
        }
        ft.commit();
    }

    public void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.nav_home){
            changePage(1);
        }else if(item.getItemId()==R.id.nav_list){
            changePage(2);
        }else if(item.getItemId()==R.id.nav_history){
//            changePage(2);
        }else if(item.getItemId()==R.id.nav_exit){
            closeApplication();
        }
        this.drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(this.drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void sendData(Film currFilm, int position) {

    }

    @Override
    public void updateList(List<Film> films) {

    }

}
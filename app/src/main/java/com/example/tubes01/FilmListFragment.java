package com.example.tubes01;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentFilmListBinding;
import com.example.tubes01.databinding.FragmentHomeBinding;

import java.util.List;

public class FilmListFragment extends Fragment implements View.OnClickListener, IMainActivity, SearchView.OnQueryTextListener {
    private FragmentFilmListBinding binding;
    private FilmListAdapter adapter;
    private MainPresenter presenter;
    private MainActivity activity;
//    private int page;

    public FilmListFragment(MainActivity activity){
        this.activity = activity;
    }

    public static FilmListFragment newInstance(MainActivity activity) {
        FilmListFragment fragment = new FilmListFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentFilmListBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        setHasOptionsMenu(true);
        this.binding.btnAddMovie.setOnClickListener(this);
        this.binding.btnAddSeries.setOnClickListener(this);

        this.presenter = new MainPresenter(this, this.activity);
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.adapter = FilmListAdapter.getFilmListAdapter(this.activity, this.presenter);
        this.binding.listFilm.setAdapter(this.adapter);

//        Film[] dummyData = {
//                new Film("Squid Game", "Squid main game", null, 1,0.0F, null, false, "movie", 0),
//                new Film("Octopus game", "Octopus main game", null, 1, 5.0F, "bagusss", true, "movie", 0),
//                new Film("Vincenzo", "Vincenzo shooting film", null, 10, 4.5F, "keren!", false, "series", 1)
//        };
//        this.presenter.loadData(dummyData);

//        this.presenter.addMovie("Squid Game","Squid main game", null);
//        this.presenter.addMovie("Octopus Game","Octopus main game", null);
//        this.presenter.addMovie("Prawn Game","Prawn main game", null);
//        this.presenter.addMovie("Fish Game","Fish main game", null);
//        this.presenter.addMovie("Shrimp Game","Shrimp main game", null);
//        this.presenter.addMovie("Crab Game","Crab main game", null);
        this.presenter.loadFilmData(); //load data dari DB

        this.binding.filmListSearch.setOnQueryTextListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.btnAddMovie){
            this.presenter.changePage(3);
        }
        else if(view == this.binding.btnAddSeries){
            this.presenter.changePage(4);
        }
    }

    @Override
    public void updateList(List<Film> films) {
        this.adapter.update(films);
    }

    @Override
    public void changePage(int page) {
//        this.page=page;
        Log.d("FlfChangePage", String.valueOf(page));

        Bundle args = new Bundle();
        args.putInt("page", page);
        this.getParentFragmentManager().setFragmentResult("changePage", args);
    }

    @Override
    public void sendData(int position, String title, String synopsis, int episode, Boolean status, Float rating, String review) {

//        Log.d("FilmList", String.valueOf(page));
//        Log.d("revFilm", title);
        Log.d("FilmListFragment","sendData");

//        if(page==6){
            Bundle args = new Bundle();
            args.putInt("Position", position);
            args.putString("FilmTitle", title);
            args.putString("FilmSynopsis", synopsis);
            args.putInt("FilmEpisode", episode);
            args.putBoolean("FilmStatus", status);
            args.putFloat("FilmRating", rating);
            args.putString("FilmReview", review);
            this.getParentFragmentManager().setFragmentResult("viewFilmList", args);
//        }else if(page==8){
//            Bundle args = new Bundle();
//            args.putInt("Position", position);
//            args.putString("FilmTitle", title);
//            args.putString("FilmSynopsis", synopsis);
//            args.putInt("FilmEpisode", episode);
//            args.putBoolean("FilmStatus", status);
//            args.putFloat("FilmRating", rating);
//            args.putString("FilmReview", review);
            this.getParentFragmentManager().setFragmentResult("viewFilmListReviewed", args);
//        }

    }

    @Override
    public void updateSeries(List<Series> series) {
        Log.d("Punyanya FilmList", "GA BENER");
    }

    @Override
    public void resetForm() {

    }
    @Override
    public void makeToastMessage(String message) {
        Toast.makeText(this.getContext(),message,Toast.LENGTH_LONG).show();
//        this.activity.recreate();
    }

    //Search Film List
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        this.presenter.filterView(s);
        return false;
    }

    //Sort Film List
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.title_az){
            this.presenter.sortView(1);
        }
        else if(item.getItemId()==R.id.title_za){
            this.presenter.sortView(2);
        }
        else if(item.getItemId()==R.id.rating_asc){
            this.presenter.sortView(3);
        }
        else if(item.getItemId()==R.id.rating_desc){
            this.presenter.sortView(4);
        }
        return super.onOptionsItemSelected(item);
    }
}

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
        this.adapter = FilmListAdapter.getFilmListAdapter(this.activity, this.presenter);
        this.binding.listFilm.setAdapter(this.adapter);

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
        Bundle args = new Bundle();
        args.putInt("page", page);
        this.getParentFragmentManager().setFragmentResult("changePage", args);
    }

    @Override
    public void sendData(int position, String title, String synopsis, byte[] poster, int episode, int status, Float rating, String review) {

            Bundle args = new Bundle();
            args.putInt("Position", position);
            args.putString("FilmTitle", title);
            args.putString("FilmSynopsis", synopsis);
            args.putByteArray("FilmPoster", poster);
            args.putInt("FilmEpisode", episode);
            args.putInt("FilmStatus", status);
            args.putFloat("FilmRating", rating);
            args.putString("FilmReview", review);
            //Film
            this.getParentFragmentManager().setFragmentResult("viewFilmList", args);
            this.getParentFragmentManager().setFragmentResult("viewFilmListReviewed", args);
            //Series
            this.getParentFragmentManager().setFragmentResult("viewSeriesData", args);
    }

    @Override
    public void updateSeries(List<Series> series) {

    }

    @Override
    public void resetForm() {

    }
    @Override
    public void makeToastMessage(String message) {
        Toast.makeText(this.getContext(),message,Toast.LENGTH_LONG).show();
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

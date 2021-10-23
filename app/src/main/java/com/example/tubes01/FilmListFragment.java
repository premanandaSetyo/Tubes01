package com.example.tubes01;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentFilmListBinding;
import com.example.tubes01.databinding.FragmentHomeBinding;

import java.util.List;

public class FilmListFragment extends Fragment implements View.OnClickListener, IMainActivity{
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
        this.binding.btnAddMovie.setOnClickListener(this);
        this.binding.btnAddSeries.setOnClickListener(this);

//        this.presenter = new MainPresenter(this);
        this.presenter = MainPresenter.getMainPresenter(this);
        this.adapter = FilmListAdapter.getFilmListAdapter(this.activity, this.presenter);
        this.binding.listFilm.setAdapter(this.adapter);

        Film[] dummyData = {
                new Film("Squid Game", "Squid main game", null, 1,0.0F, null, false, "movie", 0),
                new Film("Octopus game", "Octopus main game", null, 1, 5.0F, "bagusss", true, "movie", 0),
                new Film("Vincenzo", "Vincenzo shooting film", null, 10, 4.5F, "keren!", false, "series", 1)
        };
        this.presenter.loadData(dummyData);

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
    public void sendData(Film currFilm, int position) {
        Log.d("FilmList", "listtttt");
        String title = currFilm.getTitle();
        ImageView image = currFilm.getPoster();
        String synopsis = currFilm.getSynopsis();
        int episode = currFilm.getEpisode();
        boolean status = currFilm.isCompletedStatus();
        float rating = currFilm.getRating();
        String review = currFilm.getReview();
        Bundle args = new Bundle();
        args.putInt("Position", position);
        args.putString("FilmTitle", title);
        args.putString("FilmSynopsis", synopsis);
        args.putInt("FilmEpisode", episode);
        args.putBoolean("FilmStatus", status);
        args.putFloat("FilmRating", rating);
        args.putString("FilmReview", review);
        this.getParentFragmentManager().setFragmentResult("viewFilmData", args);
    }

    @Override
    public void updateSeries(List<Series> series) {
        Log.d("Punyanya FilmList", "GA BENER");
    }

    @Override
    public void resetForm() {

    }
}

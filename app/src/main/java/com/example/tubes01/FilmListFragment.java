package com.example.tubes01;

import android.os.Bundle;
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
        this.presenter = new MainPresenter(this);
        this.adapter = new FilmListAdapter(this.activity, this.presenter);
    }

//    public FilmListFragment(MainActivity activity, MainPresenter presenter, FilmListAdapter adapter){
//        this.activity = activity;
//        this.presenter = presenter;
//        this.adapter = adapter;
//    }

//    public static FilmListFragment newInstance(MainActivity activity) {
//        FilmListFragment fragment = new FilmListFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentFilmListBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        this.binding.btnAddMovie.setOnClickListener(this);
        this.binding.btnAddSeries.setOnClickListener(this);

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
            Bundle args = new Bundle();
            args.putInt("page", 3);
            this.getParentFragmentManager().setFragmentResult("changePage", args);
        }
        else if(view == this.binding.btnAddSeries){
            Bundle args = new Bundle();
            args.putInt("page", 4);
            this.getParentFragmentManager().setFragmentResult("changePage", args);
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
    public void sendData(Film currFilm) {
        String title = currFilm.getTitle();
        ImageView image = currFilm.getPoster();
        String synopsis = currFilm.getSynopsis();
        int episode = currFilm.getEpisode();
        boolean status = currFilm.isCompletedStatus();
        float rating = currFilm.getRating();
        String review = currFilm.getSynopsis();
        Bundle args = new Bundle();
        args.putString("FilmTitle", title);
        args.putString("FilmSynopsis", synopsis);
        args.putInt("FilmEpisode", episode);
        args.putBoolean("FilmStatus", status);
        args.putFloat("FilmRating", rating);
        args.putString("FilmReview", review);
        this.getParentFragmentManager().setFragmentResult("viewFilmData", args);
    }
}

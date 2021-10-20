package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        Film[] dummyData = {
                new Film("Squid Game", "Squid main game", null, null, null, false, "movie"),
                new Film("Octopus game", "Octopus main game", null, 5.0, "bagusss", true, "movie" )
        };
        this.binding.listFilm.setAdapter(this.adapter);
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
}

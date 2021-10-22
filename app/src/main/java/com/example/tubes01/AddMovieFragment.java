package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentAddMovieBinding;
import com.example.tubes01.databinding.FragmentHomeBinding;

import java.util.List;

public class AddMovieFragment extends Fragment implements View.OnClickListener, IMainActivity{
    private MainActivity activity;
    private FilmListAdapter adapter;
    private FragmentAddMovieBinding binding;
    private MainPresenter presenter;

    public AddMovieFragment(MainActivity activity){
        this.activity = activity;
    }

    public static AddMovieFragment newInstance(MainActivity activity) {
        AddMovieFragment fragment = new AddMovieFragment(activity);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


//    public AddMovieFragment(MainActivity activity){
//        this.activity = activity;
//        this.presenter = new MainPresenter(this);
//        this.adapter = new FilmListAdapter(this.activity, this.presenter);
//    }

//    public AddMovieFragment(MainActivity activity, MainPresenter presenter, FilmListAdapter adapter){
//        this.activity = activity;
//        this.presenter = presenter;
////        this.adapter = adapter;
//    }
//
//    public static AddMovieFragment newInstance(MainActivity activity, MainPresenter presenter) {
////        FilmListAdapter adapter = new FilmListAdapter(activity, presenter);
////        AddMovieFragment fragment = new AddMovieFragment(activity, presenter, adapter);
//        Bundle args = new Bundle();
//        FilmListAdapter adapter = (FilmListAdapter) args.getParcelable("Adapter");
//        AddMovieFragment fragment = new AddMovieFragment(activity, presenter, adapter);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentAddMovieBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        this.binding.amBtnAdd.setOnClickListener(this);
//        this.presenter = new MainPresenter(this);
//        this.adapter = new FilmListAdapter(this.activity, this.presenter);

//        this.presenter = new MainPresenter(this);
        this.presenter = MainPresenter.getMainPresenter(this);
        this.adapter = FilmListAdapter.getFilmListAdapter(this.activity, this.presenter);

        return view;
    }

    @Override
    public void onClick(View view) {
        Log.d("text", "clicked");
        String title = this.binding.addMovieTitle.getText().toString();
        String synopsis = this.binding.addMovieSynopsis.getText().toString();
        Log.d("text", "title: " + title + " synopsis: " + synopsis);
        this.presenter.addMovie(title, synopsis, null);
        changePage(2);
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

    }
}
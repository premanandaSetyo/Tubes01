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
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentAddMovieBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        this.presenter = MainPresenter.getMainPresenter(this);
//        this.presenter = new MainPresenter(this);
        this.adapter = FilmListAdapter.getFilmListAdapter(this.activity, this.presenter);

        this.binding.amBtnAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        String title = this.binding.addMovieTitle.getText().toString();
        String synopsis = this.binding.addMovieSynopsis.getText().toString();
        this.presenter.addMovie(title, synopsis, null);
        this.presenter.changePage(2);
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

    @Override
    public void updateSeries(List<Series> series) {

    }

    @Override
    public void resetForm() {
        this.binding.addMovieTitle.setText("");
        this.binding.addMovieSynopsis.setText("");
    }
}
package com.example.tubes01;

import android.content.Intent;
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
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.presenter = new MainPresenter(this, this.activity);
        this.adapter = FilmListAdapter.getFilmListAdapter(this.activity, this.presenter);

        this.binding.amBtnAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        String title = this.binding.addMovieTitle.getText().toString();
        String synopsis = this.binding.addMovieSynopsis.getText().toString();
        startActivityForResult(Intent.createChooser(this.presenter.getImageFromGallery(),"Select Picture"),1);
        this.presenter.addMovie(title, synopsis, null);
        this.presenter.changePage(2);
    }

    @Override
    public void updateList(List<Film> films) {
        this.adapter.update(films);
    }

    @Override
    public void changePage(int page) {
        Log.d("AMF",String.valueOf(page));
        Bundle args = new Bundle();
        args.putInt("page", page);
        this.getParentFragmentManager().setFragmentResult("changePage", args);
    }

    @Override
    public void sendData(Film currFilm, int position, int page) {

    }

    @Override
    public void updateSeries(List<Series> series) {

    }

    @Override
    public void resetForm() {
        this.binding.addMovieTitle.setText("");
        this.binding.addMovieSynopsis.setText("");
    }

    @Override
    public void makeToastMessage(String message) {

    }
}
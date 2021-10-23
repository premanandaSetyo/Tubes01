package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentSeriesListBinding;

import java.util.List;

public class SeriesListFragment extends Fragment implements View.OnClickListener, IMainActivity{
    private MainActivity activity;
    private FragmentSeriesListBinding binding;
    private MainPresenter presenter;
    private SeriesListAdapter adapter;

    public SeriesListFragment(MainActivity activity){
        this.activity = activity;
    }

    public static SeriesListFragment newInstance(MainActivity activity) {
        SeriesListFragment fragment = new SeriesListFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSeriesListBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
//        this.presenter = new MainPresenter(this);
        this.presenter = MainPresenter.getMainPresenter(this);
        this.adapter = SeriesListAdapter.getSeriesListAdapter(this.activity, this.presenter);
        this.binding.listSeries.setAdapter(this.adapter);

        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void updateList(List<Film> films) {

    }

    @Override
    public void changePage(int page) {

    }

    @Override
    public void sendData(Film currFilm, int position) {

    }

    @Override
    public void updateSeries(List<Series> series) {
        Log.d("Punyanya SeriesList", "BENER YEYY");
        this.adapter.update(series);
    }
}
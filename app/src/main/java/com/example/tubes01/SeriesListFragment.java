package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes01.databinding.FragmentSeriesListBinding;

import java.util.List;

public class SeriesListFragment extends Fragment implements View.OnClickListener, IMainActivity{
    private MainActivity activity;
    private FragmentSeriesListBinding binding;
    private MainPresenter presenter;
    private SeriesListAdapter adapter;
    private String title;

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
        this.presenter = new MainPresenter(this, this.activity);
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.adapter = SeriesListAdapter.getSeriesListAdapter(this.activity, this.presenter);
        this.binding.listSeries.setAdapter(this.adapter);

        this.getParentFragmentManager().setFragmentResultListener("viewSeriesList", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                getTitle(title);
            }
        });

//        this.presenter.getData(this.title);
        this.presenter.loadSeriesData(this.title);

        return view;
    }

    private void getTitle(String title){
        this.title = title;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void updateList(List<Film> films) {
    }

    @Override
    public void changePage(int page) {
        Log.d("SLF",String.valueOf(page));
    }

    @Override
    public void sendData(int position, String title, String synopsis, byte[] poster, int episode, Boolean status, Float rating, String review) {

    }

    @Override
    public void updateSeries(List<Series> series) {
        Log.d("Punyanya SeriesList", "BENER YEYY");
        this.adapter.update(series);
    }

    @Override
    public void resetForm() {

    }

    @Override
    public void makeToastMessage(String message) {

    }
}
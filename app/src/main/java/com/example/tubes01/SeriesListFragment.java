package com.example.tubes01;

import android.os.Bundle;
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
    private int position;

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
                load(title);
//                int position = result.getInt("Position");
//                load(position);
            }
        });

//        this.presenter.loadSeriesData();

//        this.presenter.getData(this.title);

        return view;
    }

//    private void load(int position){
//        this.position = position;
//        this.presenter.loadSeriesData(this.position);
//    }
    private void load(String title){
//        this.title = title;
//        this.presenter.sendTitle(title);
        this.presenter.loadSeriesData(title);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void updateList(List<Film> films) {
    }

    @Override
    public void changePage(int page) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        this.getParentFragmentManager().setFragmentResult("changePage", args);
    }

    @Override
    public void sendData(int position, String title, String synopsis, byte[] poster, int episode, Boolean status, Float rating, String review) {
        Bundle args = new Bundle();
        args.putInt("Position", position);
        args.putString("SeriesTitle", title);
        args.putString("SeriesSynopsis", synopsis);
        args.putByteArray("SeriesPoster", poster);
        args.putInt("SeriesEpisode", episode);
        args.putBoolean("SeriesStatus", status);
        args.putFloat("SeriesRating", rating);
        args.putString("SeriesReview", review);
        this.getParentFragmentManager().setFragmentResult("seriesReviewData", args);
        this.getParentFragmentManager().setFragmentResult("viewSeriesReviewedData", args);
    }

    @Override
    public void updateSeries(List<Series> series) {
        this.adapter.update(series);
    }

    @Override
    public void resetForm() {

    }

    @Override
    public void makeToastMessage(String message) {

    }
}
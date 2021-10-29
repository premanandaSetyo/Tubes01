package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes01.databinding.FragmentViewSeriesReviewedBinding;

import java.util.List;

public class ViewSeriesReviewedFragment extends Fragment implements View.OnClickListener, IMainActivity{
    private MainActivity activity;
    private FragmentViewSeriesReviewedBinding binding;
    private MainPresenter presenter;
    private int position;
    private String title;

    public ViewSeriesReviewedFragment(MainActivity activity){
        this.activity = activity;
    }

    public static ViewSeriesReviewedFragment newInstance(MainActivity activity) {
        ViewSeriesReviewedFragment fragment = new ViewSeriesReviewedFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentViewSeriesReviewedBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.presenter = new MainPresenter(this, this.activity);

        this.getParentFragmentManager().setFragmentResultListener("reviewSeriesData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("SeriesTitle");
                int episode = result.getInt("SeriesEpisode");
                float rating = result.getFloat("SeriesRating");
                String review = result.getString("SeriesReview");
                int position = result.getInt("Position");
                print(title, episode, rating, review);
                getPos(position, title);
            }
        });
        this.getParentFragmentManager().setFragmentResultListener("viewSeriesReviewedData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("SeriesTitle");
                int episode = result.getInt("SeriesEpisode");
                float rating = result.getFloat("SeriesRating");
                String review = result.getString("SeriesReview");
                int position = result.getInt("Position");
                print(title, episode, rating, review);
                getPos(position, title);
            }
        });


        this.binding.vsrBtnEditReview.setOnClickListener(this);

        return view;
    }

    public void print(String title, int episode, float rating, String review){
        this.binding.vsrTitle.setText(title);
        this.binding.vsrEpisode.setText("Episode" + String.valueOf(episode));
        this.binding.vsrRating.setRating(rating);
        this.binding.vsrReview.setText(review);

    }

    public void getPos(int position, String title){
        this.position = position;
        this.title = title;
    }

    @Override
    public void onClick(View view) {
//        if(view == this.binding.vfrBtnEditReview){
            this.presenter.getSeriesData(this.position, this.title);
            this.presenter.changePage(11);
//        }
//        else{
//            this.presenter.delete(this.position);
//            this.presenter.changePage(2);
//        }
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
    public void sendData(int position, String title, String synopsis, byte[] poster, int episode, int status, Float rating, String review) {
        Bundle args = new Bundle();
        args.putInt("Position", position);
        args.putString("SeriesTitle", title);
        args.putString("SeriesSynopsis", synopsis);
        args.putByteArray("SeriesPoster", poster);
        args.putInt("SeriesEpisode", episode);
        args.putInt("SeriesStatus", status);
        args.putFloat("SeriesRating", rating);
        args.putString("SeriesReview", review);
        this.getParentFragmentManager().setFragmentResult("editSeriesReviewData", args);
    }

    @Override
    public void updateSeries(List<Series> series) {

    }

    @Override
    public void resetForm() {

    }

    @Override
    public void makeToastMessage(String message) {
//        Toast.makeText(this.getContext(),message,Toast.LENGTH_LONG).show();
    }
}
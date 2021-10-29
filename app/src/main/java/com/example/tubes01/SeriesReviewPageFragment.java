package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes01.databinding.FragmentSeriesReviewPageBinding;

import java.util.List;

public class SeriesReviewPageFragment extends Fragment implements View.OnClickListener, IMainActivity {
    private FragmentSeriesReviewPageBinding binding;
    private MainActivity activity;
    private MainPresenter presenter;
    private SeriesListAdapter adapter;
    private int position;
    private String title;
    private int episode;

    public SeriesReviewPageFragment(MainActivity activity){
        this.activity = activity;
    }

    public static SeriesReviewPageFragment newInstance(MainActivity activity) {
        SeriesReviewPageFragment fragment = new SeriesReviewPageFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSeriesReviewPageBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.presenter = new MainPresenter(this, this.activity);
        this.adapter = SeriesListAdapter.getSeriesListAdapter(this.activity, this.presenter);

        this.getParentFragmentManager().setFragmentResultListener("editSeriesReviewData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("SeriesTitle");
                int episode = result.getInt("SeriesEpisode");
                Float rating = result.getFloat("SeriesRating");
                String review = result.getString("SeriesReview");
                int position = result.getInt("Position");
                print(title, episode, rating, review);
                getPos(position, title, episode);
            }
        });

        this.getParentFragmentManager().setFragmentResultListener("seriesReviewData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("SeriesTitle");
                int episode = result.getInt("SeriesEpisode");
                int position = result.getInt("Position");
                print(title, episode,0.0F, "");
                getPos(position, title, episode);
            }
        });

        this.binding.srpBtnSaveReview.setOnClickListener(this);

        return view;
    }

    public void print(String title, int episode,float rating, String review){
        this.binding.srpTitle.setText(title);
        this.binding.srpEpisode.setText("Episode " + String.valueOf(episode));
        this.binding.srpRating.setRating(rating);
        this.binding.srpReview.setText(review);
    }

    public void getPos(int position, String title, int eps){
        this.position = position;
        this.title = title;
        this.episode = eps;
    }

    @Override
    public void onClick(View view) {
        float rating = this.binding.srpRating.getRating();
        String review = this.binding.srpReview.getText().toString();
        if(rating!=0.0F && review.length()!=0){
            this.presenter.addSeriesReview(review, rating, this.position, this.title, this.episode);
            this.presenter.getSeriesData(this.position, this.title);
            this.presenter.changePage(10);
        }
        else{
            Toast.makeText(this.getContext(), "Please complete your film review !",Toast.LENGTH_LONG).show();
        }
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
        this.getParentFragmentManager().setFragmentResult("reviewSeriesData", args);
    }

    @Override
    public void updateSeries(List<Series> series) {

    }

    @Override
    public void resetForm() {
        this.binding.srpRating.setRating(0.0F);
        this.binding.srpReview.setText("");
    }

    @Override
    public void makeToastMessage(String message) {
        Toast.makeText(this.getContext(),message,Toast.LENGTH_LONG).show();
    }
}
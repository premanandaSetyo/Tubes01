package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes01.databinding.FragmentFilmListBinding;
import com.example.tubes01.databinding.FragmentReviewPageBinding;

import java.util.List;

public class ReviewPageFragment extends Fragment implements View.OnClickListener, IMainActivity {
    private FragmentReviewPageBinding binding;
    private MainActivity activity;
    private MainPresenter presenter;
    private FilmListAdapter adapter;
    private int position;

    public ReviewPageFragment(MainActivity activity){
        this.activity = activity;
    }

    public static ReviewPageFragment newInstance(MainActivity activity) {
        ReviewPageFragment fragment = new ReviewPageFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentReviewPageBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        this.presenter = MainPresenter.getMainPresenter(this);
//        this.presenter = new MainPresenter(this);
        this.adapter = FilmListAdapter.getFilmListAdapter(this.activity, this.presenter);

        
        this.getParentFragmentManager().setFragmentResultListener("viewFilmData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                int position = result.getInt("Position");
                print(title);
                getPos(position);
            }
        });

        this.binding.rpBtnSaveReview.setOnClickListener(this);
        
        return view;
    }

    public void print(String title){
        Log.d("ReviewPage title", title);
        this.binding.rpTitle.setText(title);
    }

    public void getPos(int position){
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        float rating = this.binding.rpRating.getRating();
        String review = this.binding.rpReview.getText().toString();
        this.presenter.addReview(review, rating, this.position);
        this.presenter.changePage(8);
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
    public void sendData(Film currFilm, int position) {
        Log.d("ReviewPage", "masuk");
        String title = currFilm.getTitle();
        ImageView image = currFilm.getPoster();
        String synopsis = currFilm.getSynopsis();
        int episode = currFilm.getEpisode();
        boolean status = currFilm.isCompletedStatus();
        float rating = currFilm.getRating();
        String review = currFilm.getSynopsis();
        Bundle args = new Bundle();
        args.putInt("Position", position);
        args.putString("FilmTitle", title);
        args.putString("FilmSynopsis", synopsis);
        args.putInt("FilmEpisode", episode);
        args.putBoolean("FilmStatus", status);
        args.putFloat("FilmRating", rating);
        args.putString("FilmReview", review);
        this.getParentFragmentManager().setFragmentResult("viewFilmData", args);
    }

    @Override
    public void updateSeries(List<Series> series) {

    }
}
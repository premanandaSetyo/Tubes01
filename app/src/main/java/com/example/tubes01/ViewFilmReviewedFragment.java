package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes01.databinding.FragmentViewFilmBinding;
import com.example.tubes01.databinding.FragmentViewFilmReviewedBinding;

public class ViewFilmReviewedFragment extends Fragment {
    private MainActivity activity;
    private FragmentViewFilmReviewedBinding binding;

    public ViewFilmReviewedFragment(MainActivity activity){
        this.activity = activity;
    }

    public static ViewFilmReviewedFragment newInstance(MainActivity activity) {
        ViewFilmReviewedFragment fragment = new ViewFilmReviewedFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentViewFilmReviewedBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();

        this.getParentFragmentManager().setFragmentResultListener("viewFilmData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                String synopsis = result.getString("FilmSynopsis");
                boolean status = result.getBoolean("FilmStatus");
                float rating = result.getFloat("FilmRating");
                String review = result.getString("FilmReview");

                Log.d("vfr", title);

                print(title, synopsis, status, rating, review);
            }
        });

        return view;
    }

    public void print(String title, String synopsis, boolean status, float rating, String review){
        this.binding.vfrTitle.setText(title);
        this.binding.vfrSynopsis.setText(synopsis);
        this.binding.vfrRating.setRating(rating);
        this.binding.vfrReview.setText(review);

    }
}

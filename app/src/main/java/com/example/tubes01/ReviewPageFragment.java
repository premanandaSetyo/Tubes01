package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        this.presenter = new MainPresenter(this);
        this.adapter = new FilmListAdapter(this.activity, this.presenter);
    }

//    public static ReviewPageFragment newInstance() {
//        ReviewPageFragment fragment = new ReviewPageFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentReviewPageBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();

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

    }
}
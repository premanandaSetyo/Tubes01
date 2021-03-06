package com.example.tubes01;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

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
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.presenter = new MainPresenter(this, this.activity);
        this.adapter = FilmListAdapter.getFilmListAdapter(this.activity, this.presenter);

        
        this.getParentFragmentManager().setFragmentResultListener("viewFilmData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                byte[] poster = result.getByteArray("FilmPoster");
                int position = result.getInt("Position");
                print(title, poster,0.0F, "");
                getPos(position);
            }
        });

        this.getParentFragmentManager().setFragmentResultListener("editReviewData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                byte[] poster = result.getByteArray("FilmPoster");
                Float rating = result.getFloat("FilmRating");
                String review = result.getString("FilmReview");
                int position = result.getInt("Position");
                print(title, poster, rating, review);
                getPos(position);
            }
        });

        this.binding.rpBtnSaveReview.setOnClickListener(this);
        
        return view;
    }

    public void print(String title, byte[] poster,float rating, String review){
        this.binding.rpTitle.setText(title);
        this.binding.rpPoster.setImageBitmap(this.presenter.decodeToBitmap(poster));
        this.binding.rpRating.setRating(rating);
        this.binding.rpReview.setText(review);
    }

    public void getPos(int position){
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        float rating = this.binding.rpRating.getRating();
        String review = this.binding.rpReview.getText().toString();
        if(rating!=0.0F && review.length()!=0){
            this.presenter.addReview(review, rating, this.position);
            this.presenter.getData(this.position);
            this.presenter.changePage(8);
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
        args.putString("FilmTitle", title);
        args.putString("FilmSynopsis", synopsis);
        args.putByteArray("FilmPoster", poster);
        args.putInt("FilmEpisode", episode);
        args.putInt("FilmStatus", status);
        args.putFloat("FilmRating", rating);
        args.putString("FilmReview", review);
        this.getParentFragmentManager().setFragmentResult("reviewData", args);
    }

    @Override
    public void updateSeries(List<Series> series) {

    }

    @Override
    public void resetForm() {
        this.binding.rpRating.setRating(0.0F);
        this.binding.rpReview.setText("");
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.binding.getRoot().getWindowToken(), 0);
    }

    @Override
    public void makeToastMessage(String message) {
        Toast.makeText(this.getContext(),message,Toast.LENGTH_LONG).show();
    }
}
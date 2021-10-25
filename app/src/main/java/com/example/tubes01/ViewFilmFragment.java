package com.example.tubes01;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes01.databinding.FragmentHomeBinding;
import com.example.tubes01.databinding.FragmentViewFilmBinding;

import java.util.List;

public class ViewFilmFragment extends Fragment implements View.OnClickListener, IMainActivity {
    private MainActivity activity;
    private FragmentViewFilmBinding binding;
    private MainPresenter presenter;
    private int position;

    public ViewFilmFragment(MainActivity activity){
        this.activity = activity;
    }

    public static ViewFilmFragment newInstance(MainActivity activity) {
        ViewFilmFragment fragment = new ViewFilmFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentViewFilmBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.presenter = new MainPresenter(this, this.activity);

        this.getParentFragmentManager().setFragmentResultListener("viewFilDat", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                String synopsis = result.getString("FilmSynopsis");
                print(title, synopsis);
                getPos(position);
            }
        });

        this.binding.vfBtnReview.setOnClickListener(this);
        this.binding.vfBtnDelete.setOnClickListener(this);

        return view;
    }


    public void print(String title, String synopsis){
        this.binding.vfTitle.setText(title);
        this.binding.vfSynopsis.setText(synopsis);
    }

    public void getPos(int position){
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.vfBtnReview){
            this.presenter.getData(this.position,7);
            this.presenter.changePage(7);
        }
        else{
            this.presenter.delete(this.position);
            this.presenter.changePage(2);
        }

    }

    @Override
    public void updateList(List<Film> films) {

    }

    @Override
    public void changePage(int page) {
        Log.d("VFF",String.valueOf(page));
        Bundle args = new Bundle();
        args.putInt("page", page);
        this.getParentFragmentManager().setFragmentResult("changePage", args);
    }

    @Override
    public void sendData(Film currFilm, int position, int page) {
        Log.d("ViewFilm", "viewwwwwwww");
        String title = currFilm.getTitle();
        Bitmap image = currFilm.getPoster();
        String synopsis = currFilm.getSynopsis();
        int episode = currFilm.getEpisode();
        boolean status = currFilm.isCompletedStatus();
        float rating = currFilm.getRating();
        String review = currFilm.getReview();
        Bundle args = new Bundle();
        args.putInt("Position", position);
        args.putString("FilmTitle", title);
        args.putString("FilmSynopsis", synopsis);
        args.putInt("FilmEpisode", episode);
        args.putBoolean("FilmStatus", status);
        args.putFloat("FilmRating", rating);
        args.putString("FilmReview", review);
        this.getParentFragmentManager().setFragmentResult("data", args);
    }

    @Override
    public void updateSeries(List<Series> series) {

    }

    @Override
    public void resetForm() {

    }

    @Override
    public void makeToastMessage(String message) {

    }
}

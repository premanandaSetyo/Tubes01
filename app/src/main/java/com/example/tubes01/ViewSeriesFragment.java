package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes01.databinding.FragmentViewSeriesBinding;

import java.util.List;

public class ViewSeriesFragment extends Fragment implements View.OnClickListener, IMainActivity {
    private MainActivity activity;
    private FragmentViewSeriesBinding binding;
    private MainPresenter presenter;
    private int position;

    public ViewSeriesFragment(MainActivity activity){
        this.activity = activity;
    }

    public static ViewSeriesFragment newInstance(MainActivity activity) {
        ViewSeriesFragment fragment = new ViewSeriesFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentViewSeriesBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.presenter = new MainPresenter(this, this.activity);

        this.getParentFragmentManager().setFragmentResultListener("viewSeriesList", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                byte[] poster = result.getByteArray("FilmPoster");
                int episode = result.getInt("FilmEpisode");
                String synopsis = result.getString("FilmSynopsis");
                int position = result.getInt("Position");
                print(title, poster, episode, synopsis);
                getPos(position);
            }
        });

        this.binding.vsBtnEps.setOnClickListener(this);
        this.binding.vsBtnDelete.setOnClickListener(this);

        return view;
    }

    public void print(String title, byte[] poster, int episode, String synopsis){
        this.binding.vsTitle.setText(title);
        this.binding.vsPoster.setImageBitmap(this.presenter.decodeToBitmap(poster));
        this.binding.vsEpisode.setText(String.valueOf(episode));
        this.binding.vsSynopsis.setText(synopsis);
    }

    public void getPos(int position){
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.vsBtnEps){
            this.presenter.getData(this.position);
            this.presenter.changePage(5);
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
        Log.d("VFSF",String.valueOf(page));
        Bundle args = new Bundle();
        args.putInt("page", page);
        this.getParentFragmentManager().setFragmentResult("changePage", args);
    }

    @Override
    public void sendData(int position, String title, String synopsis, byte[] poster, int episode, Boolean status, Float rating, String review) {

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
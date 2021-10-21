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

public class ViewSeriesFragment extends Fragment {
    private FragmentViewSeriesBinding binding;

//    public HomeFragment(){
//
//    }

    public static ViewSeriesFragment newInstance() {
        ViewSeriesFragment fragment = new ViewSeriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentViewSeriesBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();

        this.getParentFragmentManager().setFragmentResultListener("viewFilmData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                int episode = result.getInt("FilmEpisode");
                String synopsis = result.getString("FilmSynopsis");
                print(title, episode, synopsis);
            }
        });

        return view;
    }

    public void print(String title, int episode, String synopsis){
        this.binding.vsTitle.setText(title);
        this.binding.vsEpisode.setText(String.valueOf(episode));
        this.binding.vsSynopsis.setText(synopsis);
    }
}
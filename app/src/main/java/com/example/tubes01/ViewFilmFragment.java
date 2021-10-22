package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes01.databinding.FragmentHomeBinding;
import com.example.tubes01.databinding.FragmentViewFilmBinding;

public class ViewFilmFragment extends Fragment implements View.OnClickListener{
    private MainActivity activity;
    private FragmentViewFilmBinding binding;

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

        this.getParentFragmentManager().setFragmentResultListener("viewFilmData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                String synopsis = result.getString("FilmSynopsis");
                print(title, synopsis);
            }
        });

        this.binding.vfBtnReview.setOnClickListener(this);

        return view;
    }


    public void print(String title, String synopsis){
        this.binding.vfTitle.setText(title);
        this.binding.vfSynopsis.setText(synopsis);
    }

    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        args.putInt("page", 7);
        this.getParentFragmentManager().setFragmentResult("changePage", args);
    }
}

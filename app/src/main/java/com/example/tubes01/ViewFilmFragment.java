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

public class ViewFilmFragment extends Fragment {
    private FragmentViewFilmBinding binding;

//    public HomeFragment(){
//
//    }

    public static ViewFilmFragment newInstance() {
        ViewFilmFragment fragment = new ViewFilmFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentViewFilmBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();

        
        this.getParentFragmentManager().setFragmentResultListener("setData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                String synopsis = result.getString("FilmSynopsis");
                print(title, synopsis);
            }
        });

        return view;
    }


    public void print(String title, String synopsis){
        this.binding.vfTitle.setText(title);
        this.binding.vfSynopsis.setText(synopsis);
    }
}

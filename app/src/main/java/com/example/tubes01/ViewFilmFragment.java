package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ViewFilmFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_view_film, container, false);
        return view;
    }
}

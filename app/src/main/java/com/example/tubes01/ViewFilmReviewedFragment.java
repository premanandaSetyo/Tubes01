package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ViewFilmReviewedFragment extends Fragment {

//    public HomeFragment(){
//
//    }

    public static ViewFilmReviewedFragment newInstance() {
        ViewFilmReviewedFragment fragment = new ViewFilmReviewedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_film_reviewed, container, false);
        return view;
    }
}

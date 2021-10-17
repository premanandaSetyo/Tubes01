package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class AddSeriesFragment extends Fragment {

//    public HomeFragment(){
//
//    }

    public static AddSeriesFragment newInstance() {
        AddSeriesFragment fragment = new AddSeriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_series, container, false);
        return view;
    }
}
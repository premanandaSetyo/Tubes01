package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentSeriesListBinding;

public class SeriesListFragment extends Fragment {
    private FragmentSeriesListBinding binding;

//    public HomeFragment(){
//
//    }

    public static SeriesListFragment newInstance() {
        SeriesListFragment fragment = new SeriesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSeriesListBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();

        return view;
    }
}
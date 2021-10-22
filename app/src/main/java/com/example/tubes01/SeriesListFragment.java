package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentSeriesListBinding;

public class SeriesListFragment extends Fragment {
    private MainActivity activity;
    private FragmentSeriesListBinding binding;

    public SeriesListFragment(MainActivity activity){
        this.activity = activity;
    }

    public static SeriesListFragment newInstance(MainActivity activity) {
        SeriesListFragment fragment = new SeriesListFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSeriesListBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();

        return view;
    }
}
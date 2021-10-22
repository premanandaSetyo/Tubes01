package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentAddSeriesBinding;
import com.example.tubes01.databinding.FragmentFilmListBinding;

public class AddSeriesFragment extends Fragment {
    private MainActivity activity;
    private FragmentAddSeriesBinding binding;

    public AddSeriesFragment(MainActivity activity){
        this.activity = activity;
    }

    public static AddSeriesFragment newInstance(MainActivity activity) {
        AddSeriesFragment fragment = new AddSeriesFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentAddSeriesBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        
        return view;
    }
}
package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

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

        return view;
    }
}
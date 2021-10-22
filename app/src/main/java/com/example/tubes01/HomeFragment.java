package com.example.tubes01;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubes01.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private MainActivity activity;
    private FragmentHomeBinding binding;

    public HomeFragment(MainActivity activity){
        this.activity = activity;
    }

    public static HomeFragment newInstance(MainActivity activity) {
        HomeFragment fragment = new HomeFragment(activity);
        return fragment;
    }
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        this.binding.btnBegin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.btnBegin){
            Bundle args = new Bundle();
            args.putInt("page", 2);
            this.getParentFragmentManager().setFragmentResult("changePage", args);
        }
    }
}
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
    private FragmentHomeBinding binding;

//    public HomeFragment(){
//
//    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        this.binding.btnBegin.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.btnBegin){
//            Log.d("test", "clicked");
//            ((MainActivity)getActivity()).changePage(2);

            Bundle args = new Bundle();
            args.putInt("page", 2);
            this.getParentFragmentManager().setFragmentResult("changePage", args);
        }
    }
}
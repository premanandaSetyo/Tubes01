package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentFilmListBinding;
import com.example.tubes01.databinding.FragmentReviewPageBinding;

public class ReviewPageFragment extends Fragment {
    private FragmentReviewPageBinding binding;

//    public HomeFragment(){
//
//    }

    public static ReviewPageFragment newInstance() {
        ReviewPageFragment fragment = new ReviewPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentReviewPageBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        
        return view;
    }
}
package com.example.tubes01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentFilmListBinding;
import com.example.tubes01.databinding.FragmentHomeBinding;

public class FilmListFragment extends Fragment implements View.OnClickListener{
    private FragmentFilmListBinding binding;

//    public HomeFragment(){
//
//    }

    public static FilmListFragment newInstance(MainActivity activity) {
        FilmListFragment fragment = new FilmListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentFilmListBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        this.binding.btnAddMovie.setOnClickListener(this);
        this.binding.btnAddSeries.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.btnAddMovie){
            Bundle args = new Bundle();
            args.putInt("page", 3);
            this.getParentFragmentManager().setFragmentResult("changePage", args);
        }
        else if(view == this.binding.btnAddSeries){
            Bundle args = new Bundle();
            args.putInt("page", 4);
            this.getParentFragmentManager().setFragmentResult("changePage", args);
        }
    }
}

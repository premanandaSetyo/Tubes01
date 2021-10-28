package com.example.tubes01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentAddMovieBinding;
import com.example.tubes01.databinding.FragmentHomeBinding;

import java.io.IOException;
import java.util.List;

public class AddMovieFragment extends Fragment implements View.OnClickListener, IMainActivity{
    private MainActivity activity;
    private FilmListAdapter adapter;
    private FragmentAddMovieBinding binding;
    private MainPresenter presenter;
    private Bitmap bitmap;

    public AddMovieFragment(MainActivity activity){
        this.activity = activity;
    }

    public static AddMovieFragment newInstance(MainActivity activity) {
        AddMovieFragment fragment = new AddMovieFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentAddMovieBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.presenter = new MainPresenter(this, this.activity);
        this.adapter = FilmListAdapter.getFilmListAdapter(this.activity, this.presenter);

        this.binding.amBtnUpload.setOnClickListener(this);
        this.binding.amBtnAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.amBtnUpload){
            startActivityForResult(Intent.createChooser(this.presenter.getImageFromGallery(),"Select Picture"),1);
//            startActivityForResult(this.presenter.getImageFromGallery(), 1);
//            super.onActivityResult(1, 1, this.presenter.getImageFromGallery());
        }
        else{
            String title = this.binding.addMovieTitle.getText().toString();
            String synopsis = this.binding.addMovieSynopsis.getText().toString();
            if(title.length()!=0 && synopsis.length()!=0 && this.bitmap!=null){
                this.presenter.addMovie(title, synopsis, this.bitmap);
                this.presenter.changePage(2);
            }
            else if(title.length()==0){
                Toast.makeText(this.getContext(),"Please input film title !",Toast.LENGTH_LONG).show();
            }
            else if(synopsis.length()==0){
                Toast.makeText(this.getContext(),"Please input film synopsis !",Toast.LENGTH_LONG).show();
            }
            else if(this.bitmap==null){
                Toast.makeText(this.getContext(),"Please input film poster !",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void updateList(List<Film> films) {
        this.adapter.update(films);
    }

    @Override
    public void changePage(int page) {
        Log.d("AMF",String.valueOf(page));
        Bundle args = new Bundle();
        args.putInt("page", page);
        this.getParentFragmentManager().setFragmentResult("changePage", args);
    }

    @Override
    public void sendData(int position, String title, String synopsis, byte[] poster, int episode, Boolean status, Float rating, String review) {

    }

    @Override
    public void updateSeries(List<Series> series) {

    }

    @Override
    public void resetForm() {
        this.binding.addMovieTitle.setText("");
        this.binding.addMovieSynopsis.setText("");
    }

    @Override
    public void makeToastMessage(String message) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==this.activity.RESULT_OK && requestCode == 1){
            Uri uri = data.getData();
            try {
                this.bitmap = MediaStore.Images.Media.getBitmap(this.activity.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.binding.amPoster.setImageBitmap(this.bitmap);
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
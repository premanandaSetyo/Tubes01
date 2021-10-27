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

import com.example.tubes01.databinding.FragmentAddSeriesBinding;
import com.example.tubes01.databinding.FragmentFilmListBinding;

import java.io.IOException;
import java.util.List;

public class AddSeriesFragment extends Fragment implements View.OnClickListener, IMainActivity{
    private MainActivity activity;
    private FragmentAddSeriesBinding binding;
    private MainPresenter presenter;
    private SeriesListAdapter adapter;
    private Bitmap bitmap;

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
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.presenter = new MainPresenter(this, this.activity);
        this.adapter = SeriesListAdapter.getSeriesListAdapter(this.activity, this.presenter);

        this.binding.asBtnUpload.setOnClickListener(this);
        this.binding.asBtnAdd.setOnClickListener(this);
        
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.asBtnUpload){
            startActivityForResult(Intent.createChooser(this.presenter.getImageFromGallery(),"Select Picture"),1);
        }
        else{
            String title = this.binding.asTitle.getText().toString();
            int episode = Integer.parseInt(this.binding.asEps.getText().toString());
            String synopsis = this.binding.asSyn.getText().toString();

            Log.d("title", title);
            Log.d("eps", String.valueOf(episode));
            Log.d("title", synopsis);

            if(title.length()!=0 && String.valueOf(episode).length()!=0 && synopsis.length()!=0){
                this.presenter.addSeries(title, synopsis, this.bitmap, episode);
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
        ;
    }

    @Override
    public void changePage(int page) {
        Log.d("ASF",String.valueOf(page));
    }

    @Override
    public void sendData(int position, String title, String synopsis, byte[] poster, int episode, Boolean status, Float rating, String review) {

    }

    @Override
    public void updateSeries(List<Series> series) {
        this.adapter.update(series);
    }

    @Override
    public void resetForm() {
        this.binding.asBtnAdd.setText("");
        this.binding.asEps.setText("");
        this.binding.asSyn.setText("");
    }

    @Override
    public void makeToastMessage(String message) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Uri uri = data.getData();
            try {
                this.bitmap = MediaStore.Images.Media.getBitmap(this.activity.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.binding.asPoster.setImageBitmap(this.bitmap);
        }
    }
}
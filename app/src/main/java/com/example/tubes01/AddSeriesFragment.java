package com.example.tubes01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tubes01.databinding.FragmentAddSeriesBinding;
import com.example.tubes01.databinding.FragmentFilmListBinding;

import java.util.List;

public class AddSeriesFragment extends Fragment implements View.OnClickListener, IMainActivity{
    private MainActivity activity;
    private FragmentAddSeriesBinding binding;
    private MainPresenter presenter;
    private SeriesListAdapter adapter;

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

        }
        else{
            String title = this.binding.asBtnAdd.getText().toString();
            int episode = Integer.parseInt(this.binding.asEps.getText().toString());
            String synopsis = this.binding.asSyn.getText().toString();
            if(title.length()!=0 && String.valueOf(episode).length()!=0 && synopsis.length()!=0){
                this.presenter.addSeries(title, synopsis, null, episode);
                this.presenter.changePage(2);
            }
            else if(title.length()==0){
                Toast.makeText(this.getContext(),"Please input film title !",Toast.LENGTH_LONG).show();
            }
            else if(synopsis.length()==0){
                Toast.makeText(this.getContext(),"Please input film synopsis !",Toast.LENGTH_LONG).show();
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
    public void sendData(int position, String title, String synopsis, int episode, Boolean status, Float rating, String review) {

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
}
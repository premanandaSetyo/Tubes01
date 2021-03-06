package com.example.tubes01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes01.databinding.FragmentViewSeriesBinding;

import java.util.List;

public class ViewSeriesFragment extends Fragment implements View.OnClickListener, IMainActivity {
    private MainActivity activity;
    private FragmentViewSeriesBinding binding;
    private MainPresenter presenter;
    private int position;

    public ViewSeriesFragment(MainActivity activity){
        this.activity = activity;
    }

    public static ViewSeriesFragment newInstance(MainActivity activity) {
        ViewSeriesFragment fragment = new ViewSeriesFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentViewSeriesBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        this.presenter = new MainPresenter(this, this.activity);

        this.getParentFragmentManager().setFragmentResultListener("viewSeriesData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                byte[] poster = result.getByteArray("FilmPoster");
                int episode = result.getInt("FilmEpisode");
                String synopsis = result.getString("FilmSynopsis");
                int position = result.getInt("Position");
                print(title, poster, episode, synopsis, position);
                getPos(position);
            }
        });

        this.binding.vsBtnEps.setOnClickListener(this);
        this.binding.vsBtnDrop.setOnClickListener(this);
        this.binding.vsBtnDelete.setOnClickListener(this);

        return view;
    }

    public void print(String title, byte[] poster, int episode, String synopsis, int position){
        this.binding.vsTitle.setText(title);
        this.binding.vsPoster.setImageBitmap(this.presenter.decodeToBitmap(poster));
        this.binding.vsEpisode.setText(String.valueOf(" " + episode));
        this.binding.vsStat.setText(" " + this.presenter.printStatus(position));
        this.binding.vsSynopsis.setText(synopsis);
        this.binding.progressBar.setMax(episode);
        this.binding.progressBar.setProgress(this.presenter.completedEps(title));
    }

    public void getPos(int position){
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.vsBtnEps){
            this.presenter.getData(this.position);
            this.presenter.changePage(5);
        }
        else if(view == this.binding.vsBtnDrop){
            this.presenter.dropFilm(this.position);
        }
        else{
            openDialog();
        }
    }

    public void openDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Are you sure want to delete this film ?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteFilm();
                    }
                });
        dialog.show();
    }

    public void deleteFilm(){
        this.presenter.delete(this.position);
        this.presenter.changePage(2);
    }

    @Override
    public void updateList(List<Film> films) {

    }

    @Override
    public void changePage(int page) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        this.getParentFragmentManager().setFragmentResult("changePage", args);
    }

    @Override
    public void sendData(int position, String title, String synopsis, byte[] poster, int episode, int status, Float rating, String review) {
        Bundle args = new Bundle();
        args.putInt("Position", position);
        args.putString("FilmTitle", title);
        args.putString("FilmSynopsis", synopsis);
        args.putByteArray("FilmPoster", poster);
        args.putInt("FilmEpisode", episode);
        args.putInt("FilmStatus", status);
        args.putFloat("FilmRating", rating);
        args.putString("FilmReview", review);
        this.getParentFragmentManager().setFragmentResult("viewSeriesList", args);
    }

    @Override
    public void updateSeries(List<Series> series) {
    }

    @Override
    public void resetForm() {

    }

    @Override
    public void makeToastMessage(String message) {
        Toast.makeText(this.getContext(),message,Toast.LENGTH_LONG).show();
    }
}
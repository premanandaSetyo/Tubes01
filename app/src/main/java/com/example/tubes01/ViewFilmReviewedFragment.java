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

import com.example.tubes01.databinding.FragmentViewFilmBinding;
import com.example.tubes01.databinding.FragmentViewFilmReviewedBinding;

import java.util.List;

public class ViewFilmReviewedFragment extends Fragment implements View.OnClickListener, IMainActivity{
    private MainActivity activity;
    private FragmentViewFilmReviewedBinding binding;
    private MainPresenter presenter;
    private int position;

    public ViewFilmReviewedFragment(MainActivity activity){
        this.activity = activity;
    }

    public static ViewFilmReviewedFragment newInstance(MainActivity activity) {
        ViewFilmReviewedFragment fragment = new ViewFilmReviewedFragment(activity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentViewFilmReviewedBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
//        this.presenter = MainPresenter.getMainPresenter(this);
        this.presenter = new MainPresenter(this, this.activity);

//        this.presenter.loadFilmData();

        this.getParentFragmentManager().setFragmentResultListener("viewFilmListReviewed", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                String synopsis = result.getString("FilmSynopsis");
                byte[] poster = result.getByteArray("FilmPoster");
                float rating = result.getFloat("FilmRating");
                String review = result.getString("FilmReview");
                int position = result.getInt("Position");

                print(title, synopsis, poster, rating, review, position);
                getPos(position);
            }
        });
        this.getParentFragmentManager().setFragmentResultListener("reviewData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("FilmTitle");
                String synopsis = result.getString("FilmSynopsis");
                byte[] poster = result.getByteArray("FilmPoster");
                float rating = result.getFloat("FilmRating");
                String review = result.getString("FilmReview");
                int position = result.getInt("Position");

                print(title, synopsis, poster, rating, review, position);
                getPos(position);
            }
        });


        this.binding.vfrBtnEditReview.setOnClickListener(this);
        this.binding.vfrBtnDrop.setOnClickListener(this);
        this.binding.vfrBtnDelete.setOnClickListener(this);

        return view;
    }

    public void print(String title, String synopsis, byte[] poster,float rating, String review, int position){
        this.binding.vfrTitle.setText(title);
        this.binding.vfrPoster.setImageBitmap(this.presenter.decodeToBitmap(poster));
        this.binding.vfrSynopsis.setText(synopsis);
        this.binding.vfrStat.setText(" " + this.presenter.printStatus(position));
        this.binding.vfrRating.setRating(rating);
        this.binding.vfrReview.setText(review);

    }

    public void getPos(int position){
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.vfrBtnEditReview){
            this.presenter.getData(this.position);
            this.presenter.changePage(7);
        }
        else if(view == this.binding.vfrBtnDrop){
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
        this.getParentFragmentManager().setFragmentResult("editReviewData", args);
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

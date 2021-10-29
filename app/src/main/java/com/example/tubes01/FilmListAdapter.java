package com.example.tubes01;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes01.databinding.FragmentFilmItemBinding;

import java.util.ArrayList;
import java.util.List;

public class FilmListAdapter extends BaseAdapter {
    private List<Film> filmList;
    private MainActivity activity;
    private MainPresenter presenter;
    protected static FilmListAdapter singleton;

    public FilmListAdapter (MainActivity activity, MainPresenter presenter) {
        this.activity = activity;
        this.filmList = new ArrayList<Film>();
        this.presenter = presenter;
    }

    public static FilmListAdapter getFilmListAdapter(MainActivity activity, MainPresenter presenter){
        if(FilmListAdapter.singleton==null){
            FilmListAdapter.singleton = new FilmListAdapter(activity, presenter);
        }
        return FilmListAdapter.singleton;
    }


    @Override
    public int getCount() {
        return filmList.size();
    }

    @Override
    public Object getItem(int i) {
        return filmList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("size",String.valueOf(filmList.size()));
        FilmListAdapter.ViewHolder viewHolder;
        if(view == null) {
            FragmentFilmItemBinding binding = FragmentFilmItemBinding.inflate(this.activity.getLayoutInflater(), viewGroup, false);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding, this.presenter, i, (Film)this.getItem(i), this.activity);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView((Film)this.getItem(i), i);
        return view;
    }

    public void update(List<Film> getFilms){
        this.filmList = getFilms;
        notifyDataSetChanged();
    }

    private class ViewHolder implements View.OnClickListener {
        private MainActivity activity;
        private MainPresenter presenter;
        private FragmentFilmItemBinding binding;
        private Film currentFilm;
        private int i;

        public ViewHolder(FragmentFilmItemBinding binding, MainPresenter presenter, int i, Film currentFilm, MainActivity activity){
            this.binding = binding;
            this.presenter = presenter;
            this.i = i;
            this.currentFilm = currentFilm;
            this.activity = activity;
        }

        public void updateView(Film film, int i){
//            Log.d("vh","msk OC VH");
            this.i = i;
            this.currentFilm = film;
            this.binding.fiTitle.setText(film.getTitle());
            this.binding.fiCategory.setText(film.getCategory());
            if(film.getPoster()!=null){
                this.binding.fiPoster.setImageBitmap(this.presenter.decodeToBitmap(film.getPoster()));
            }
//            if(this.currentFilm.getRating()!=0.0F) {
                this.binding.fiStarRate.setImageResource(R.drawable.star);
                this.binding.fiRating.setText(String.valueOf(this.currentFilm.getRating()));
//            }
            this.binding.fiFilmItem.setOnClickListener(this);

//            this.activity.startActivityForResult(intent, 1);
        }

        @Override
        public void onClick(View view) {
            if(view == this.binding.fiFilmItem){
//                this.presenter.getData(this.i);
                if(this.currentFilm.getCategory().equals("movies") && this.currentFilm.getStatus()==0){
                    Log.d("vh","msk OC VH");
                    this.presenter.changePage(6);
                    this.presenter.getData(this.i);
                }
                else if(this.currentFilm.getCategory().equals("movies") && this.currentFilm.getStatus()==1){
                    this.presenter.changePage(8);
                    this.presenter.getData(this.i);
                }
                else if(this.currentFilm.getCategory().equals("series")){
                    this.presenter.changePage(9);
                    this.presenter.getData(this.i);
                }
            }
        }
    }
}
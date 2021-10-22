package com.example.tubes01;

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
        FilmListAdapter.ViewHolder viewHolder;
        if(view == null) {
            FragmentFilmItemBinding binding = FragmentFilmItemBinding.inflate(this.activity.getLayoutInflater(), viewGroup, false);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding, this.presenter, i, (Film)this.getItem(i));
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
        private MainPresenter presenter;
        private FragmentFilmItemBinding binding;
        private Film currentFilm;
        private int i;

        public ViewHolder(FragmentFilmItemBinding binding, MainPresenter presenter, int i, Film currentFilm){
            this.binding = binding;
            this.presenter = presenter;
            this.i = i;
            this.currentFilm = currentFilm;
        }

        public void updateView(Film film, int i){
            this.binding.fiTitle.setText(film.getTitle());
            this.binding.fiCategory.setText(film.getCategory());
            this.i = i;
            this.currentFilm = film;
            this.binding.fiFilmItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view == this.binding.fiFilmItem){
                this.presenter.getData(this.i);
                if(this.currentFilm.getCategory().equals("movie") && this.currentFilm.isCompletedStatus()==false){
                    this.presenter.changePage(6);
                }
                else if(this.currentFilm.getCategory().equals("movie") && this.currentFilm.isCompletedStatus()==true){
                    this.presenter.changePage(8);
                }
                else if(this.currentFilm.getCategory().equals("series")){
                    this.presenter.changePage(9);
                }
            }
        }
    }
}
package com.example.tubes01;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes01.databinding.FragmentSeriesItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SeriesListAdapter extends BaseAdapter {
    private List<Series> seriesList;
    private MainActivity activity;
    private MainPresenter presenter;
    protected static SeriesListAdapter singleton;

    public SeriesListAdapter (MainActivity activity, MainPresenter presenter) {
        this.activity = activity;
        this.seriesList = new ArrayList<Series>();
        this.presenter = presenter;
    }

    public static SeriesListAdapter getSeriesListAdapter(MainActivity activity, MainPresenter presenter){
        if(SeriesListAdapter.singleton==null){
            SeriesListAdapter.singleton = new SeriesListAdapter(activity, presenter);
        }
        return SeriesListAdapter.singleton;
    }


    @Override
    public int getCount() {
        return seriesList.size();
    }

    @Override
    public Object getItem(int i) {
        return seriesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SeriesListAdapter.ViewHolder viewHolder;
        if(view == null) {
            FragmentSeriesItemBinding binding = FragmentSeriesItemBinding.inflate(this.activity.getLayoutInflater(), viewGroup, false);
            view = binding.getRoot();
            viewHolder = new SeriesListAdapter.ViewHolder(binding, this.presenter, i, (Series)this.getItem(i));
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (SeriesListAdapter.ViewHolder) view.getTag();
        }
        viewHolder.updateView((Series)this.getItem(i), i);
        return view;
    }

    public void update(List<Series> getSeries){
        Log.d("Series Adapter", "update masuk");
        this.seriesList = getSeries;
        notifyDataSetChanged();
    }

    private class ViewHolder implements View.OnClickListener {
        private MainPresenter presenter;
        private FragmentSeriesItemBinding binding;
        private Series currentSeries;
        private int i;

        public ViewHolder(FragmentSeriesItemBinding binding, MainPresenter presenter, int i, Series currentSeries){
            this.binding = binding;
            this.presenter = presenter;
            this.i = i;
            this.currentSeries = currentSeries;
        }

        public void updateView(Series series, int i){
            this.binding.siTitle.setText("Episode " + series.getEps());
            this.i = i;
            this.currentSeries = series;
            this.binding.siSeriesItem.setOnClickListener(this);

//            if(this.currentSeries.getRating()!=0.0F) {
            this.binding.siStarRate.setImageResource(R.drawable.star);
            this.binding.siRating.setText(String.valueOf(this.currentSeries.getRating()));
//            }
            this.binding.siSeriesItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view == this.binding.siSeriesItem){
                Log.d("onclick series", "masukk");
//                this.presenter.getData(this.i);
                if(this.currentSeries.isCompletedStatus()==false){
                    this.presenter.changePage(11);
                    this.presenter.getSeriesData(this.i, this.currentSeries.getTitle());
                }
                else if(this.currentSeries.isCompletedStatus()==true){
                    this.presenter.changePage(10);
                    this.presenter.getSeriesData(this.i, this.currentSeries.getTitle());
                }
            }
        }
    }
}
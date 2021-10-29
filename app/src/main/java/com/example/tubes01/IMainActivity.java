package com.example.tubes01;

import java.util.List;

public interface IMainActivity {
    void updateList(List<Film> films);
    void changePage(int page);
    void sendData(int position, String title, String synopsis, byte[] poster, int episode, int status, Float rating, String review);
    void updateSeries(List<Series> series);
    void resetForm();
    void makeToastMessage(String message);
}

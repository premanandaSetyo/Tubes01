package com.example.tubes01;

import java.util.List;

public interface IMainActivity {
    void updateList(List<Film> films);
    void changePage(int page);
    void sendData(Film currFilm, int position, int page);
    void updateSeries(List<Series> series);
    void resetForm();
    void makeToastMessage(String message);
}

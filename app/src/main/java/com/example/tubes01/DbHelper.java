package com.example.tubes01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class DbHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final String DB_NAME = "MaMontre_db" ;

    private static final String TABLE_FILM = "table_film";
    private static final String COL1_FILM = "title";
    private static final String COL2_FILM = "synopsis";
    private static final String COL3_FILM = "poster";
    private static final String COL4_FILM = "rating";
    private static final String COL5_FILM = "review";
    private static final String COL6_FILM = "completedStatus";
    private static final String COL7_FILM = "category";
    private static final String COL8_FILM = "idx";
    private static final String COL9_FILM = "eps";;
    private static final String COL10_FILM = "dropStatus";


    private static final String TABLE_SERIES = "table_series";
    private static final String COL1_SERIES = "title";
    private static final String COL2_SERIES = "synopsis";
    private static final String COL3_SERIES = "rating";
    private static final String COL4_SERIES = "review";
    private static final String COL5_SERIES = "eps";
    private static final String COL6_SERIES = "completedStatus";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        String createTableFilm = "CREATE TABLE " + TABLE_FILM + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL1_FILM + " TEXT, "+ COL2_FILM + " TEXT, "+ COL3_FILM +" BLOB, "+ COL4_FILM + " REAL, "+ COL5_FILM + " TEXT, "+ COL6_FILM + " INTEGER, "+ COL7_FILM + " TEXT, "+ COL8_FILM + " INTEGER, "+ COL9_FILM + " INTEGER, "+ COL10_FILM + " INTEGER) ";
        String createTableSeries = "CREATE TABLE " + TABLE_SERIES + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL1_SERIES + " TEXT, "+ COL2_SERIES + " TEXT, "+ COL3_SERIES +" REAL, "+ COL4_SERIES + " TEXT, " + COL5_SERIES + " INTEGER, "+ COL6_SERIES + " INTEGER)";
        db.execSQL(createTableFilm);
        db.execSQL(createTableSeries);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        this.db = sqLiteDatabase;
        db.execSQL("DROP TABLE IF  EXISTS "+TABLE_FILM);
        db.execSQL("DROP TABLE IF  EXISTS "+TABLE_SERIES);
        onCreate(db);
    }

    public boolean addDataFilm(String title, String synopsis, byte[] poster, float rating, String review, boolean completedStatus, String category, int idx, int eps, int dropStatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1_FILM, title);
        cv.put(COL2_FILM, synopsis);
        cv.put(COL3_FILM, poster);
        cv.put(COL4_FILM, rating);
        cv.put(COL5_FILM, review);
        cv.put(COL6_FILM, completedStatus);
        cv.put(COL7_FILM, category);
        cv.put(COL8_FILM, idx);
        cv.put(COL9_FILM, eps);
        cv.put(COL10_FILM, dropStatus);

        long res = db.insert(TABLE_FILM, null, cv);

        if (res == -1) {
            return false;
        }else{
            return true;
        }
    }

    public boolean addDataSeries(String title, String synopsis, float rating, String review, int eps, boolean completedStat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1_SERIES, title);
        cv.put(COL2_SERIES, synopsis);
        cv.put(COL3_SERIES, rating);
        cv.put(COL4_SERIES, review);
        cv.put(COL5_SERIES, eps);
        cv.put(COL6_SERIES, completedStat);

        long res = db.insert(TABLE_SERIES, null, cv);

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getAllFilm(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_FILM,null);
        return res;
    }

    public Cursor getAllSeries(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_SERIES,null);
        return res;
    }

    public Cursor getSeries(String title){
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("SELECT "+ COL1_FILM +" FROM "+TABLE_FILM+" WHERE " + COL1_FILM + "='"+title+"'",null);
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_SERIES+" WHERE "+ COL1_SERIES + "='"+title+"'",null);
//        Log.d("db length", res.getString(1));
        return res;
    }

    public boolean deleteFilm(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_FILM, "title=?",new String[]{ title });
//        String query = "DELETE FROM "+TABLE_FILM+" WHERE "+ COL1_FILM + " = " + title;
//        db.rawQuery(query, null);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateDataFilm(String review, int completedStatus, float rating, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL4_FILM, rating);
        cv.put(COL5_FILM, review);
        cv.put(COL6_FILM, completedStatus);

        long res = db.update(TABLE_FILM, cv, "title=?", new String[]{ title });

        if (res == -1) {
            Log.d("return apa", "false");
            return false;
        } else {
            Log.d("return apa", "true");
            return true;
        }
    }

    public boolean drop(String position) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL10_FILM, 1);

        long res = db.update(TABLE_FILM, cv, "ID=?", new String[]{ position });

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor filterByRating(float rating){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_FILM+" WHERE  " + COL4_FILM + " = '"+rating+"'",null);
        return res;
    }

    public Cursor filterByCompletedStatus(int completedStatus){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_FILM+" WHERE  " + COL6_FILM + " = '"+completedStatus+"'",null);
        return res;
    }

    public Cursor filterByDropStatus(int dropStatus){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_FILM+" WHERE  " + COL10_FILM + " = '"+dropStatus+"'",null);
        return res;
    }

    public String checkTitle(String title){ //check if title exist in DB
        String temp = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT "+ COL1_FILM +" FROM "+TABLE_FILM+" WHERE " + COL1_FILM + "='"+title+"'",null);
        while(res.moveToNext()){
            temp = res.getString(0);
        }
        return temp;
    }


}

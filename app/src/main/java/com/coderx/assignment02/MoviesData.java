package com.coderx.assignment02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MoviesData extends SQLiteOpenHelper {
    private static final String TAG = "MoviesData";
    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static String[] FROM = { MovieConstant.ID, MovieConstant.MOVIE_TITLE, MovieConstant.YEAR, MovieConstant.DIRECTOR,
            MovieConstant.ACTORS, MovieConstant.RATING, MovieConstant.REVIEW, MovieConstant.IS_FAV,};
    private static final String ORDER_BY = " ASC";
    public MoviesData(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: creating the database and implement the table");
        /*query to implement the table*/
        sqLiteDatabase.execSQL("CREATE TABLE "+MovieConstant.TABLE_NAME + " ("
        + MovieConstant.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + MovieConstant.MOVIE_TITLE + " TEXT NOT NULL,"
        + MovieConstant.YEAR + " INTEGER,"
        + MovieConstant.DIRECTOR + " TEXT NOT NULL,"
        + MovieConstant.ACTORS + " TEXT NOT NULL,"
        + MovieConstant.RATING + " INTEGER,"
        + MovieConstant.REVIEW + " TEXT NOT NULL,"
        + MovieConstant.IS_FAV + " NUMERIC);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: upgrading the database");

    }

    /*adding data to the database*/
    public void addMovie(Movie movie,MoviesData moviesData){
        Log.d(TAG, "addMovie: adding a movie to database");
        SQLiteDatabase db = moviesData.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MovieConstant.MOVIE_TITLE, movie.getTitle());
        values.put(MovieConstant.YEAR,movie.getYear());
        values.put(MovieConstant.DIRECTOR, movie.getDirector());
        values.put(MovieConstant.ACTORS, movie.getActors());
        values.put(MovieConstant.RATING, movie.getRating());
        values.put(MovieConstant.REVIEW, movie.getReview());
        values.put(MovieConstant.IS_FAV, 0);
        db.insertOrThrow(MovieConstant.TABLE_NAME,null,values);
        Log.d(TAG, "addMovie: movie added successfully");

    }

    /*retrieving data from database*/
    public ArrayList<Movie> retrieveData(Cursor cursor){
        Log.d(TAG, "retrieveData: retrieve all movies from the database ");
        ArrayList<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int year = cursor.getInt(2);
            String director = cursor.getString(3);
            String actors = cursor.getString(4);
            int rating = cursor.getInt(5);
            String review = cursor.getString(6);
            boolean is_fav = cursor.getInt(7) == 1;

            /*creating the movie object*/
            Movie movie = new Movie(id,title,year,director,actors,rating,review,is_fav);
            /*adding the movie object to arrayList*/
            movies.add(movie);

        }
        return movies; // returning all movies from the database
    }

    public Cursor getCursor(MoviesData moviesData){
        SQLiteDatabase db = moviesData.getReadableDatabase();
        Cursor cursor = db.query(MovieConstant.TABLE_NAME,FROM,null,null,null,null,MovieConstant.MOVIE_TITLE+ORDER_BY);
        return cursor;
    }
}

package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class DisplayMoviesActivity extends AppCompatActivity {
    private RecyclerView movieRecView;
    private DisplayRecViewAdapter adapter;
    private MoviesData moviesData;
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);

        /*initializing the recycler and adapter*/
        adapter = new DisplayRecViewAdapter(this);
        movieRecView = findViewById(R.id.movieRecView);
        moviesData = new MoviesData(this);
        movies = moviesData.retrieveData(moviesData.getCursor(moviesData));

        movieRecView.setAdapter(adapter);
        movieRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setMovies(movies);

    }

}

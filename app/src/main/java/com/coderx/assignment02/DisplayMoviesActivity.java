package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayMoviesActivity extends AppCompatActivity {
    private static final String TAG = "DisplayActivity";
    private RecyclerView movieRecView;
    private Button btnFav;
    private DisplayRecViewAdapter adapter;
    private MoviesData moviesData;
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);

        /*initializing the recycler, adapter, button, movieData object and movies*/
        adapter = new DisplayRecViewAdapter(this);
        movieRecView = findViewById(R.id.movieRecView);
        btnFav = findViewById(R.id.btnFav);
        moviesData = new MoviesData(this);
        movies = moviesData.retrieveData(moviesData.getCursor(moviesData)); // loading data to the arrayList

        movieRecView.setAdapter(adapter);
        movieRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setMovies(movies);


        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btnFav Clicked!");
                adapter.updateDB(DisplayMoviesActivity.this);
                Toast.makeText(DisplayMoviesActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show(); // toast to display message
            }
        });

    }



}

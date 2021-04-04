package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    private static final String TAG = "FavouriteActivity";
    private RecyclerView favRecView;
    private Button btnSave;
    private FavouriteRecViewAdapter adapter;
    private MoviesData moviesData;
    private ArrayList<Movie> movies = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        favRecView = findViewById(R.id.favRecView); // initializing the rec
        btnSave = findViewById(R.id.btnSave);
        adapter = new FavouriteRecViewAdapter(this);
        moviesData = new MoviesData(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                movies = moviesData.favMovies(moviesData.getFavMovies());

                // updating ui
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        favRecView.setAdapter(adapter);
                        favRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                        adapter.setMovies(movies);

                    }
                });
            }
        }).start();



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btnSave Clicked!");
                // updating the db on separate thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateDB(FavouriteActivity.this);
                    }
                }).start();

                Toast.makeText(FavouriteActivity.this, "Favourites Updated!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class EditMovieActivity extends AppCompatActivity {
    private RecyclerView editRecView;
    private EditRecViewAdapter adapter;
    private MoviesData moviesData;
    private ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        editRecView = findViewById(R.id.editRecView);
        adapter = new EditRecViewAdapter(this);
        moviesData = new MoviesData(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                movies = moviesData.retrieveData(moviesData.getCursor()); // loading data to the arrayList

                // update the ui
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        editRecView.setAdapter(adapter);
                        editRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                        adapter.setMovies(movies);
                    }
                });
            }
        }).start();



    }

}

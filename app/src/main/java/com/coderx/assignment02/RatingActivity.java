package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    private RecyclerView ratingRecView;
    private Button btnFind;
    private RatingRecViewAdapter adapter;
    private MoviesData moviesData;
    private ArrayList<Movie> movies = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        //calling the initViews method
        initViews();


        new Thread(new Runnable() {
            @Override
            public void run() {
                movies = moviesData.retrieveData(moviesData.getCursor());


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ratingRecView.setAdapter(adapter);
                        adapter.setMovies(movies);
                        ratingRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    }
                });
            }
        }).start();



        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedMovie = RatingRecViewAdapter.movieTitle;
                if (selectedMovie == null){
                    Toast.makeText(RatingActivity.this, "Please Select a Movie", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(RatingActivity.this, SelectedRatingViewActivity.class);
                    intent.putExtra("movie",selectedMovie);
                    startActivity(intent);
                }

            }
        });
    }

    private void initViews(){
        ratingRecView = findViewById(R.id.ratingRecView);
        btnFind = findViewById(R.id.btnFind);

        adapter = new RatingRecViewAdapter(this);
        moviesData = new MoviesData(this);
    }


}

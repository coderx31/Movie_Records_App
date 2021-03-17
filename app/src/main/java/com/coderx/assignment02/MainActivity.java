package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Button btnRegMovie, btnDisplayMovies, btnFavourites, btnEditMovies, btnSearch, btnRatings;
    private TextView txtName, txtLicence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        /*calling the views initializing method*/
        initViews();

        /*click listeners for button to start new intent*/
        btnRegMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegMovieActivity.class);
                startActivity(intent);

            }
        });

        btnDisplayMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayMoviesActivity.class);
                startActivity(intent);
            }
        });

        btnFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        btnEditMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        btnRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }


    /*private method to initialize all the views*/
    private void initViews(){
        btnRegMovie = findViewById(R.id.btnRegMovie);
        btnDisplayMovies = findViewById(R.id.btnDisplayMovies);
        btnFavourites = findViewById(R.id.btnFavourites);
        btnEditMovies = findViewById(R.id.btnEditMovies);
        btnSearch = findViewById(R.id.btnSearch);
        btnRatings = findViewById(R.id.btnRatings);
        txtName = findViewById(R.id.txtName);
        txtLicence = findViewById(R.id.txtLicence);
    }
}

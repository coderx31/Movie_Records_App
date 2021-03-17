package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class DisplayMoviesActivity extends AppCompatActivity {
    private RecyclerView movieRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);
    }
}

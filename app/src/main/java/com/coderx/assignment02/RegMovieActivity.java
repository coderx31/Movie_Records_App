package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegMovieActivity extends AppCompatActivity {
    private EditText input_name, input_year, input_director, input_actors, input_rating, input_review;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_movie);

        /*calling the views initializing method*/
        initViews();
    }

    /*private method to initialize all the views*/
    private void initViews(){
        input_name = findViewById(R.id.name_input);
        input_year = findViewById(R.id.input_year);
        input_director = findViewById(R.id.input_director);
        input_actors = findViewById(R.id.input_actors);
        input_rating = findViewById(R.id.input_rating);
        input_review = findViewById(R.id.input_review);
        btnSave = findViewById(R.id.btnSave);
    }
}

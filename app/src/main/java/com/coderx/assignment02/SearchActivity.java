package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {
    private EditText user_input;
    private ImageButton imageButton;
    private RecyclerView resultMovieView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //calling the initViews method
        initViews();
    }

    private void initViews(){
        user_input = findViewById(R.id.user_input);
        imageButton = findViewById(R.id.imageButton);
        resultMovieView = findViewById(R.id.resultMovieView);
    }
}

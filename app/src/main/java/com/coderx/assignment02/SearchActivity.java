package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private EditText user_input;
    private ImageButton imageButton;
    private RecyclerView resultMovieView;
    private SearchRecViewAdapter adapter;
    private MoviesData moviesData;
    private ArrayList<Movie> movies = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //calling the initViews method
        initViews();

        new Thread(new Runnable() {
            @Override
            public void run() {
                movies = moviesData.retrieveData(moviesData.getCursor());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setMovies(movies);
                        resultMovieView.setAdapter(adapter);
                        resultMovieView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                    }
                });
            }
        }).start();






        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked");
                Toast.makeText(SearchActivity.this, "Filtering", Toast.LENGTH_SHORT).show();
                String user_text = user_input.getText().toString().toLowerCase(); // getting the user input
                adapter.getFilter().filter(user_text); // calling the filter method
                Log.d(TAG, "onClick: done!");
            }
        });
    }

    private void initViews(){
        user_input = findViewById(R.id.user_input);
        imageButton = findViewById(R.id.imageButton);
        resultMovieView = findViewById(R.id.resultMovieView);

        moviesData = new MoviesData(this);
        adapter = new SearchRecViewAdapter(this);
    }

}

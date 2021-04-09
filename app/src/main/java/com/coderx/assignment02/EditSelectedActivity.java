package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Calendar;

public class EditSelectedActivity extends AppCompatActivity {
    private static final String TAG = "EditSelectedActivity";
    private EditText movieTitle, movieYear, movieDirector, movieActors, movieReview;
    private RatingBar movieRating;
    private RadioButton fav, notFav;
    private RadioGroup favourite_check;
    private MoviesData moviesData;
    private Button btnUpdate;
    private boolean isFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_selected);

        moviesData = new MoviesData(this);
        // getting data from previous intent
        Bundle bundle = getIntent().getExtras();
        int id = 0;
        if (bundle != null){
            id = bundle.getInt("movieId");
        }
        initViews(); // initializing the all views
        // getting data from db and update the ui
        final int finalId1 = id;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateActivity(moviesData.selectedMovie(moviesData.getSelectedMovie(finalId1))); // updating the activity
            }
        });


        /*Text Watcher for limit the year between 1895 and current year*/
        /*https://developer.android.com/reference/android/text/TextWatcher*/
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
                int min = 1895;
                int max = Calendar.getInstance().get(Calendar.YEAR); // getting the current year
                int n;
                try{
                    n = Integer.parseInt(value);
                    if (value.length() == 4){
                        if (n < min) {
                            editable.replace(0, editable.length(), String.valueOf(min));
                        } else if (n > max) {
                            editable.replace(0, editable.length(), String.valueOf(max));
                        }
                    }

                }catch (NumberFormatException ex){
                    System.out.println(ex.getMessage());
                }

            }
        };

        movieYear.addTextChangedListener(textWatcher);

        /*clickListener for update btn*/
        final int finalId = id;
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDB(finalId);
            }
        });


    }

    private void initViews(){
        movieTitle = findViewById(R.id.movieTitle);
        movieYear = findViewById(R.id.movieYear);
        movieDirector = findViewById(R.id.movieDirector);
        movieActors = findViewById(R.id.movieActors);
        movieReview = findViewById(R.id.movieReview);
        movieRating = findViewById(R.id.movieRating);
        fav = findViewById(R.id.fav);
        notFav = findViewById(R.id.notFav);
        btnUpdate = findViewById(R.id.btnUpdate);
        favourite_check = findViewById(R.id.favourite_check);
    }

    private void updateDB(final int id){
       /*click listener for radioGroup*/
        if (fav.isChecked()){
            isFav = true;
        }
        if (notFav.isChecked()){
            isFav = false;
        }


        /*use try-catch to avoid run time exception and crashing the application*/
        try {
            Log.d(TAG, "updateDB: updating start");
            // creating movie object
            float s = movieRating.getRating();
            final Movie movie = new Movie(id,movieTitle.getText().toString(), Integer.parseInt(movieYear.getText().toString()),
                    movieDirector.getText().toString(),movieActors.getText().toString(),(int)s,movieReview.getText().toString(),
                    isFav);

            // update the db on separate thread to balance the skipped frames
            new Thread(new Runnable() {
                @Override
                public void run() {
                    moviesData.updateSelectedMovie(id,movie); // calling the update method
                }
            }).start();

            Toast.makeText(this, "Movie Updated!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "updateDB: movie updated successfully");
        }catch (Exception ex){
            Toast.makeText(this, "Error while updating the db", Toast.LENGTH_SHORT).show();
            System.out.println(ex.getMessage());
        }finally {
            moviesData.close();
        }

    }

    /*updating the edit texts with values got from db*/
    private void updateActivity(Movie movie){
        movieTitle.setText(movie.getTitle());
        movieYear.setText(String.valueOf(movie.getYear()));
        movieDirector.setText(movie.getDirector());
        movieActors.setText(movie.getActors());
        movieRating.setRating(movie.getRating());
        movieReview.setText(movie.getReview());

        if (movie.isFav()){
            fav.setChecked(true);
        }else{
            notFav.setChecked(true);
        }
    }
}

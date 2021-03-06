package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;


public class RegMovieActivity extends AppCompatActivity {
    private static final String TAG = "RegMovieActivity";
    private EditText input_name, input_year, input_director, input_actors, input_rating, input_review;
    private Button btnSave;
    private MoviesData movieData;
    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_movie);

        /*calling the views initializing method*/
        initViews();

        /*initialising the movieData*/
        movieData = new MoviesData(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMovie();
            }
        });
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


        /*setting the range to 1-10*/
        input_rating.setFilters(new InputFilter[]{new NumberRange("1","10")});

        /*Text watcher for limit year between 1895 - current year*/
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

        input_year.addTextChangedListener(textWatcher); // adding the txtWatcher
    }

    private void createMovie(){
        try {
            Log.d(TAG, "createMovie: starting");
            final Movie movie = new Movie(1,input_name.getText().toString(), Integer.parseInt(input_year.getText().toString()),
                    input_director.getText().toString(),input_actors.getText().toString(),Integer.parseInt(input_rating.getText().toString()),
                    input_review.getText().toString(),false);


            checked = false;

            // use a thread to check if the movie name currently in the database or not ?
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Movie> movies = new ArrayList<>(); // creating arrayList and retrieve data from db
                    movies = movieData.retrieveData(movieData.getCursor());

                    for (Movie movie1 : movies){
                        if (movie.getTitle().toLowerCase().equals(movie1.getTitle().toLowerCase())){
                            Log.d(TAG, "run: error! this name already in Database");
                           checked = true;
                        }
                    }

                }
            });

            thread.start();
            thread.join();

            if (!checked){
                movieData.addMovie(movie); // movie created and added to the database
                Toast.makeText(this, "Movie Added to the database", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "createMovie: Successfully added to the database");
                clearFields();
            }else{
                Toast.makeText(this, "This name is already in the database", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception ex){
            System.out.println("Error Occurred!");
            Toast.makeText(this, "Error while creating movie", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "createMovie: Error occurred!", null);
        }

    }


    // after a movie added successfully to database, all the fields will cleared
    private void clearFields(){
        Log.d(TAG, "clearFields: method called!");
        input_name.setText("");
        input_year.setText("");
        input_director.setText("");
        input_actors.setText("");
        input_rating.setText("");
        input_review.setText("");
    }


}

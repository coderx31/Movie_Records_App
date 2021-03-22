package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class RegMovieActivity extends AppCompatActivity {
    private static final String TAG = "RegMovieActivity";
    private EditText input_name, input_year, input_director, input_actors, input_rating, input_review;
    private Button btnSave;
    private MoviesData movieData;

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


        /*setting the range with input-filter*/
        /*setting the range to 1895 - current year*/
        int year = Calendar.getInstance().get(Calendar.YEAR);
       //input_year.setFilters(new InputFilter[]{new NumberRange(1895,year)});
        /* error on year input - needs to clear that*/

        /*setting the range to 1-10*/
        input_rating.setFilters(new InputFilter[]{new NumberRange("1","10")});
    }

    private void createMovie(){
        try {
            Log.d(TAG, "createMovie: starting");
            Movie movie = new Movie(1,input_name.getText().toString(), Integer.parseInt(input_year.getText().toString()),
                    input_director.getText().toString(),input_actors.getText().toString(),Integer.parseInt(input_rating.getText().toString()),
                    input_review.getText().toString(),false);

            movieData.addMovie(movie,movieData);
            Toast.makeText(this, "Movie Added to the database", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "createMovie: Successfully added to the database");
            clearFields();
        }catch (Exception ex){
            System.out.println("Error Occurred!");
            Toast.makeText(this, "Error while creating movie", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "createMovie: Error occurred!", null);
        }finally {
            movieData.close(); // closing the database connection
        }

        //DONE
    }


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

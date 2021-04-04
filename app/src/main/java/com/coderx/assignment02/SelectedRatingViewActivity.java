package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SelectedRatingViewActivity extends AppCompatActivity {
    private RecyclerView moviesRecView;
    private ApiMoviesAdapter adapter;
    private ArrayList<ApiMovieModel> movieModels = new ArrayList<>();
    private String movie_url;
    private String api_key;
    private String movie_rating_url;
    private String id;
    private String img_url;
    private String movie_title;
    private StringBuilder movie_list = new StringBuilder(); // for movies list



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_rating_view);

        moviesRecView = findViewById(R.id.moviesRecView);
        adapter = new ApiMoviesAdapter(this);

        String selected_movie = "";
        // get the bundled data
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            selected_movie = bundle.getString("movie");
        }

        movie_url = "https://imdb-api.com/en/API/SearchTitle/";  // url for get movies according to the title
        //api_key = "k_s0eheteo/"; // added a single dash
        api_key = "k_bven5e28/";
        movie_rating_url = "https://imdb-api.com/en/API/UserRatings/"; // url for get ratings using id




        final String finalSelected_movie = selected_movie;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(movie_url + api_key + finalSelected_movie);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    // building the string
                    while ((line = bufferedReader.readLine()) != null) {
                        movie_list.append(line); // appending the movie_list
                    }

                    JSONObject jsonObject = new JSONObject(movie_list.toString());
                    JSONArray movie_array = jsonObject.optJSONArray("results"); // extract the results array

                    for (int i=0; i<movie_array.length(); i++){
                        StringBuilder rating_list = new StringBuilder();
                        JSONObject object = (JSONObject) movie_array.get(i);
                        id = object.getString("id");
                        img_url = object.getString("image");
                        movie_title = object.getString("title");

                        URL rating_url = new URL(movie_rating_url+api_key+id);
                        HttpURLConnection rating_conn = (HttpURLConnection) rating_url.openConnection();

                        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(rating_conn.getInputStream()));
                        String line1;
                        while ((line1 = bufferedReader1.readLine()) != null){
                            rating_list.append(line1);
                        }

                        JSONObject rating_object = new JSONObject(rating_list.toString());
                        String rating = rating_object.getString("totalRating");

                        float movie_rating;
                        if (rating.equals("") || rating.equals("null")) {
                            movie_rating = (float) 0.0;
                        } else {
                            movie_rating = Float.parseFloat(rating);
                        }


                        ApiMovieModel movieModel = new ApiMovieModel(id,movie_title,movie_rating,img_url);
                        movieModels.add(movieModel);
                    }
                } catch (Exception e) {
                    e.getMessage();
                }


                try {
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           moviesRecView.setAdapter(adapter);
                           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectedRatingViewActivity.this);
                           linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                           moviesRecView.setLayoutManager(linearLayoutManager);
                           adapter.setMovieModels(movieModels);
                       }
                   });
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }).start();


    }


}

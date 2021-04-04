package com.coderx.assignment02;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ApiMoviesAdapter extends RecyclerView.Adapter<ApiMoviesAdapter.ViewHolder> {
    private static final String TAG = "ApiMovieAdapter";
    private ArrayList<ApiMovieModel> movieModels = new ArrayList<>();
    private Context mContext;

    public ApiMoviesAdapter(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //setting up the layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.api_movies_list, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called!");
        holder.movieTitle.setText(movieModels.get(position).getTitle()); // setting the title
        holder.movieRating.setRating(movieModels.get(position).getRating()); // setting the rating


        holder.movieTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Title Clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ImageDisplayActivity.class);
                intent.putExtra("image",movieModels.get(position).getImage()); // sending image url with intent
                mContext.startActivity(intent);
            }
        });
        
    }

    // the count for loop through
    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public void setMovieModels(ArrayList<ApiMovieModel> movieModels) {
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView movieTitle;
        private RatingBar movieRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieRating = itemView.findViewById(R.id.movieRating);
        }
    }
}

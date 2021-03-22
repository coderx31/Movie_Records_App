package com.coderx.assignment02;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouriteRecViewAdapter extends RecyclerView.Adapter<FavouriteRecViewAdapter.ViewHolder>  {
    private static final String TAG = "FavouriteRecViewAdapter";
    private ArrayList<Movie> movies = new ArrayList<>();  // creating arrayList for set the data to adapter
    private Context mContext;
    private MoviesData moviesData;

    public FavouriteRecViewAdapter(Context context){
        this.mContext = context;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_movie_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.txtTitle.setText(movies.get(position).getTitle());

        holder.favCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Movie movie = movies.get(position);
                if (!b){
                    movie.setFav(false);
                }else{
                    movie.setFav(true);
                }

                notifyItemChanged(position); // to notify that item has been changed
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView txtTitle;
        private CheckBox favCheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            favCheck = itemView.findViewById(R.id.favCheck);

            favCheck.setChecked(true);
        }
    }

    public void updateDB(Context context){
        MoviesData moviesData = new MoviesData(context);
        for (int i=0; i<movies.size(); i++){
            boolean isTicked = movies.get(i).isFav();
            if (!isTicked){
                Log.d(TAG, "updateDB: add to favourite method call");
                moviesData.updateFav(movies.get(i).getId(),moviesData);
            }else{
                Log.d(TAG, "updateDB: did nit call");
            }
        }
    }
}

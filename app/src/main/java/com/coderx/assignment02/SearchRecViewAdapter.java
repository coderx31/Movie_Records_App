package com.coderx.assignment02;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class SearchRecViewAdapter extends RecyclerView.Adapter<SearchRecViewAdapter.ViewHolder> implements Filterable {
    private static final String TAG = "SearchViewAdapter";
    private ArrayList<Movie> movies = new ArrayList<>();
    private Context mContext;
    private ArrayList<Movie> allMovies;

    public SearchRecViewAdapter(Context context){
        this.mContext = context;
        this.allMovies = new ArrayList<>(movies);
    }


    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_movie_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called!");
        holder.txtTitle.setText(movies.get(position).getTitle()); // setting the movie title to text view
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Movie> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                Log.d(TAG, "performFiltering: on start");
                // nothing to do
                // nothing to display
            }else{
                // display filtered items
                //TODO
                //needs to implement filter for director and actor names
                for (Movie movie : movies){
                    Log.d(TAG, "performFiltering: after button clicked");
                    if (movie.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(movie);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        // runs on a ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            movies.clear();
            movies.addAll((Collection<? extends Movie>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}

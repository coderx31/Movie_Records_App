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

public class DisplayRecViewAdapter extends RecyclerView.Adapter<DisplayRecViewAdapter.ViewHolder> {
    private static final String TAG = "DisplayRecViewAdapter";
    private ArrayList<Movie> movies = new ArrayList<>();  // creating arrayList for set the data to adapter
    private Context mContext;
    private MoviesData moviesData;

    public DisplayRecViewAdapter(Context mContext){
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_movie_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtTitle.setText(movies.get(position).getTitle());

        /*when user select any movie, then the is_fav set to true*/
        holder.favCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Movie movie = movies.get(position);
                if (b){
                    movie.setFav(true);
                }else{
                    movie.setFav(false);
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
        }



    }

    /*updating the db when user pressed add to favourite button*/
    public void updateDB(Context context){
        moviesData = new MoviesData(context);
        /*use a for loop to update all selected item*/
        for (int i=0; i<movies.size(); i++){
            boolean isTicked = movies.get(i).isFav();
            if (isTicked){
                Log.d(TAG, "updateDB: addFavourite Method called");
                moviesData.addFavourite(movies.get(i).getId());
            }
        }
    }
}

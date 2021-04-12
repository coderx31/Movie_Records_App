package com.coderx.assignment02;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RatingRecViewAdapter extends RecyclerView.Adapter<RatingRecViewAdapter.ViewHolder> {
    private static final String TAG = "RatingRecViewAdapter";
    public static String movieTitle;
    private ArrayList<Movie> movies = new ArrayList<>();
    private Context mContext;
    private int lastCheckedPosition = -1;

    public RatingRecViewAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called!");
        holder.txtTitle.setText(movies.get(position).getTitle()); // setting the title of each movies
        holder.titleSelect.setChecked(position == lastCheckedPosition);


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
        private RadioButton titleSelect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            titleSelect = itemView.findViewById(R.id.titleSelect);

            titleSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastCheckedPosition = getAdapterPosition();
                    movieTitle = movies.get(getAdapterPosition()).getTitle();
                    Toast.makeText(mContext, movies.get(getAdapterPosition()).getTitle()+ " Selected ", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
        }
    }
}

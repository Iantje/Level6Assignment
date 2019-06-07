package me.iantje.moviedatabase.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.iantje.moviedatabase.Models.Movie;
import me.iantje.moviedatabase.R;

public class MovieRecycler extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movieItems = new ArrayList<>();

    public List<Movie> getMovieItems() {
        return movieItems;
    }

    public void setMovieItems(List<Movie> movieItems) {
        this.movieItems = movieItems;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.viewholder_movie, viewGroup, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        TextView movieNumber = movieViewHolder.itemView.findViewById(R.id.recyclerMovieNumber);
        movieNumber.setText(String.valueOf(i + 1));

        ImageView moviePoster = movieViewHolder.itemView.findViewById(R.id.recyclerMoviePoster);
        Glide.with(movieViewHolder.itemView)
                .load("https://image.tmdb.org/t/p/w500" + movieItems.get(i).getPosterPath())
                .into(moviePoster);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }
}

package me.iantje.moviedatabase;

import com.bumptech.glide.Glide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import me.iantje.moviedatabase.Models.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_DETAIL = "movie_intent_detail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent intent = getIntent();
        if(intent == null) {
            finish();
            return;
        }

        Movie movieData = intent.getParcelableExtra(EXTRA_MOVIE_DETAIL);
        if(movieData == null) {
            finish();
            return;
        }

        ActionBar supportBar = getSupportActionBar();
        if(supportBar != null) {
            supportBar.setDisplayHomeAsUpEnabled(true);
            supportBar.setTitle(movieData.getTitle());
        }

        // Header
        ImageView header = findViewById(R.id.detailMovieHeader);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movieData.getBackdropPath())
                .into(header);
        // Poster
        ImageView poster = findViewById(R.id.detailMoviePoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movieData.getPosterPath())
                .into(poster);
        // Title
        TextView title = findViewById(R.id.detailMovieTitle);
        title.setText(movieData.getTitle());
        // Release
        TextView release = findViewById(R.id.detailMovieRelease);
        release.setText(movieData.getReleaseDate());
        // Vote average
        TextView voteAverage = findViewById(R.id.detailMovieScore);
        voteAverage.setText(String.valueOf(movieData.getVoteAverage()));
        // Description
        TextView description = findViewById(R.id.detailMovieOverview);
        description.setText(movieData.getOverview());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();

        return super.onOptionsItemSelected(item);
    }
}

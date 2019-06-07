package me.iantje.moviedatabase;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.iantje.moviedatabase.Models.Movie;
import me.iantje.moviedatabase.recycler.MovieRecycler;
import me.iantje.moviedatabase.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private MainViewModel viewModel;
    private Button viewMoviesBtn;
    private TextView yearInput;

    private RecyclerView recyclerView;
    private MovieRecycler recyclerAdapter = new MovieRecycler();

    private Observer<List<Movie>> movieObserver = new Observer<List<Movie>>() {
        @Override
        public void onChanged(@Nullable List<Movie> movies) {
            if(movies == null) return;

            Log.d("MainActivity", "Got a movie update for ya");

            recyclerAdapter.setMovieItems(movies);
            recyclerAdapter.notifyDataSetChanged();
        }
    };

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.moviesData.observe(this, movieObserver);

        viewMoviesBtn = findViewById(R.id.mainSubmitBtn);
        yearInput = findViewById(R.id.mainYearField);
        viewMoviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int year = Integer.parseInt(yearInput.getText().toString());

                    viewModel.getMoviesFromYear(year);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Try a number", Toast.LENGTH_LONG).show();
                }
            }
        });

        recyclerView = findViewById(R.id.mainRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnItemTouchListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        viewModel.moviesData.removeObservers(this);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        int itemLocation = recyclerView.getChildAdapterPosition(child);

        if(gestureDetector.onTouchEvent(motionEvent)) {
            Movie movie = recyclerAdapter.getMovieItems().get(itemLocation);

            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_DETAIL, movie);
            startActivity(intent);
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}

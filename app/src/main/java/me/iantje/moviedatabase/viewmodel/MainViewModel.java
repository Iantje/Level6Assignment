package me.iantje.moviedatabase.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import me.iantje.moviedatabase.Models.Movie;
import me.iantje.moviedatabase.repository.MovieRepository;

public class MainViewModel extends ViewModel {

    private MovieRepository movieRepo = new MovieRepository();
    public LiveData<List<Movie>> moviesData;

    public MainViewModel() {
        moviesData = movieRepo.yearMovies;
    }

    public void getMoviesFromYear(int year) {
        movieRepo.getMoviesFromYear(year);
    }

}

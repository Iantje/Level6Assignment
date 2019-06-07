package me.iantje.moviedatabase.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import me.iantje.moviedatabase.Models.DiscoverCallback;
import me.iantje.moviedatabase.Models.Movie;
import me.iantje.moviedatabase.retrofit.MovieDbService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    public MutableLiveData<List<Movie>> yearMovies = new MutableLiveData<>();

    public void getMoviesFromYear(int year) {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        MovieDbService movieService = retrofit.create(MovieDbService.class);

        Call<DiscoverCallback> request = movieService.getMoviesFromYear(year);
        request.enqueue(new Callback<DiscoverCallback>() {
            @Override
            public void onResponse(Call<DiscoverCallback> call, Response<DiscoverCallback> response) {
                if(response.body() != null) {
                    yearMovies.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<DiscoverCallback> call, Throwable t) {
                // TODO: Onfailure
                Log.e("MovieRepository", t.getMessage());
            }
        });

    }
}

package me.iantje.moviedatabase.retrofit;

import me.iantje.moviedatabase.Models.DiscoverCallback;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbService {
    String API_KEY = "1d90c563f507983795d80eb50726a1de";

    @GET("discover/movie/?api_key=" + API_KEY)
    Call<DiscoverCallback> getMoviesFromYear(@Query("primary_release_year") int releaseYear);
}

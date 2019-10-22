package com.sample.movieapp.Api;

import com.sample.movieapp.ApiResponse.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey,@Query("page") int page);


    //https://image.tmdb.org/t/p/w500 ImagePopulating Path


}

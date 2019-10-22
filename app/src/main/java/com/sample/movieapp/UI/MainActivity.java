package com.sample.movieapp.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.sample.movieapp.Adapters.MovieAdapter;
import com.sample.movieapp.Api.ApiClient;
import com.sample.movieapp.Api.ApiInterface;
import com.sample.movieapp.ApiResponse.MoviesResponse;
import com.sample.movieapp.CONSTANTS;
import com.sample.movieapp.Model.MovieModel;
import com.sample.movieapp.R;
import com.sample.movieapp.Repo.MovieRepository;
import com.sample.movieapp.ViewModel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends CustomToolbar {

    /*
    MOVIE DB CREDENTIALS

    @API_Key : d9c95dcc65e74facb6360cc7aa85ddc3

    @API_Read_Access_Token : eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkOWM5NWRjYzY1ZTc0ZmFjYjYzNjBjYzdhYTg1ZGRjMyIsInN1YiI6IjVkYTQwYzM4MTk2NzU3MDAxMWFjNjQxOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.72DUt2eTLg_sC7kGTMgcLWGxQNbnqRMNWh0_oo2DUBY


     */

    ApiInterface apiInterface;

    private MovieViewModel model;

    RecyclerView movielist;

    MovieAdapter adapter;

    List<MovieModel>movies=new ArrayList<>();

    GridLayoutManager layoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        movielist=(RecyclerView)findViewById(R.id.movielist);

        layoutManager =new GridLayoutManager(this,2);
//        movielist.setLayoutManager(layoutManager);
        movielist.setItemViewCacheSize(6);
        movielist.setDrawingCacheEnabled(true);
        movielist.setHasFixedSize(true);
        movielist.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);





        model= ViewModelProviders.of(this).get(MovieViewModel.class);

//        model.init();

        model.GetLatestPopularMovie().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(@Nullable List<MovieModel> movieModels) {

                adapter.SetProducts(movieModels);
            }
        });

        initRecyclerview();


    }

    private  void initRecyclerview()
    {
        adapter =new MovieAdapter(this,model.GetLatestPopularMovie().getValue());
        movielist.setLayoutManager(new LinearLayoutManager(this));
        movielist.setAdapter(adapter);
    }


    private void getOnlineMovies() {



        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        apiInterface.getPopularMovies(CONSTANTS.api_KEY,1).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, retrofit2.Response<MoviesResponse> response) {

                Log.e("MovieDb response",""+response.code());

                if (response.isSuccessful() && response.code() == 200) {

                    assert response.body() != null;




                    List<MoviesResponse.Result> responsebody = response.body().getResults();
                    for (int i = 0; i < responsebody.size(); i++) {

                        MovieModel model =new MovieModel(responsebody.get(i).getId(),
                                responsebody.get(i).getVoteCount(),
                                responsebody.get(i).getPosterPath(),
                                responsebody.get(i).getAdult(),
                                responsebody.get(i).getOriginalLanguage(),
                                responsebody.get(i).getOriginalTitle(),
                                responsebody.get(i).getVoteAverage(),
                                responsebody.get(i).getOverview(),
                                responsebody.get(i).getReleaseDate());

//                        newPopularMovies.add(model);


                    }


                }
            }


            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });



    }



}

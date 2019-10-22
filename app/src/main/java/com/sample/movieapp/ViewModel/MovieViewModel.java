package com.sample.movieapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sample.movieapp.Model.MovieDbModel;
import com.sample.movieapp.Model.MovieModel;
import com.sample.movieapp.Repo.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    private MutableLiveData<List<MovieModel>> New_popularmovies;
    private LiveData<List<MovieDbModel>> popularmovies;


//    public void init()
//    {
//        if(New_popularmovies!=null)
//        {
//            return;
//        }
//        MovieRepository.getInstance().getNewPopularMovies();
//    }




    public MovieViewModel(@NonNull Application application) {
        super(application);

        this.movieRepository= new MovieRepository(application);
        this.popularmovies= movieRepository.getPopularMovies();
        this.New_popularmovies=movieRepository.getNewPopularMovies();

        Log.e("MovieViewModel","Constructor");
    }


    public void AddVoucher(MovieDbModel movies)
    {
        movieRepository.AddMovies(movies);
    }

    public void UpdateVoucher(MovieDbModel movies)
    {
        movieRepository.UpdateMovie(movies);
    }

    public void DeleteVoucher(MovieDbModel movies)
    {
        movieRepository.DeleteMovie(movies);
    }

    public LiveData<List<MovieDbModel>> GetPopularMovie()
    {
        return  popularmovies;
    }


    public LiveData<List<MovieModel>> GetLatestPopularMovie()
    {
        return  New_popularmovies;
    }





}

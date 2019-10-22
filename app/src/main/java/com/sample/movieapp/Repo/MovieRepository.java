package com.sample.movieapp.Repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.sample.movieapp.Api.ApiClient;
import com.sample.movieapp.Api.ApiInterface;
import com.sample.movieapp.ApiResponse.MoviesResponse;
import com.sample.movieapp.CONSTANTS;
import com.sample.movieapp.Model.MovieDbModel;
import com.sample.movieapp.Model.MovieModel;
import com.sample.movieapp.Room.DAO.Movie_DAO;
import com.sample.movieapp.Room.MovieOfflineDb;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;



public class MovieRepository {

//    private static MovieRepository Instatnce;

    private Movie_DAO movie_dao;
    private LiveData<List<MovieDbModel>> popularMovies;

    private List<MovieModel> newPopularMovies =new ArrayList<>();


//    public static MovieRepository getInstance()
//    {
//        if(Instatnce==null)
//        {
//            Instatnce=new MovieRepository();
//        }
//        return Instatnce;
//    }
//
//
//    public MovieRepository() {
//    }

    public MovieRepository(Application application) {

        MovieOfflineDb db = MovieOfflineDb.getInstance(application);
        this.movie_dao = db.movie_dao() ;
        this.popularMovies = movie_dao.GetAllMovies();
        getOnlineMovies();
//        Log.e("MovieRepository","Constructor");

    }



    public  void  AddMovies(MovieDbModel movie)
    {

        new AddMovieAsync(movie_dao).execute(movie);
    }

    public  void  UpdateMovie(MovieDbModel movie)
    {
        new UpdateMovieAsync(movie_dao).execute(movie);
    }

    public  void  DeleteMovie(MovieDbModel movie)
    {
        new DeleteMovieAsync(movie_dao).execute(movie);
    }



    public  LiveData<List<MovieDbModel>>getPopularMovies()
    {
        return popularMovies;
    }


    public  MutableLiveData<List<MovieModel>>getNewPopularMovies()
    {

        getOnlineMovies();

        Log.e("Function Called","getNewPopularMovies");
        MutableLiveData<List<MovieModel>> movies=new MutableLiveData<>();

        movies.setValue(newPopularMovies);

        return movies;
    }




    private  static class AddMovieAsync extends AsyncTask<MovieDbModel,Void,Void> {

        private Movie_DAO voucher_dao;

        public AddMovieAsync(Movie_DAO voucher_dao) {
            this.voucher_dao = voucher_dao;
        }

        @Override
        protected Void doInBackground(MovieDbModel... movies) {

            voucher_dao.AddMovie(movies[0]);

            return null;
        }
    }


    private  static class DeleteMovieAsync extends AsyncTask<MovieDbModel,Void,Void> {

        private Movie_DAO voucher_dao;

        public DeleteMovieAsync(Movie_DAO voucher_dao) {
            this.voucher_dao = voucher_dao;
        }

        @Override
        protected Void doInBackground(MovieDbModel... movies) {

            voucher_dao.DeleteMovies(movies[0]);

            return null;
        }
    }

    private  static class UpdateMovieAsync extends AsyncTask<MovieDbModel,Void,Void> {

        private Movie_DAO voucher_dao;

        public UpdateMovieAsync(Movie_DAO voucher_dao) {
            this.voucher_dao = voucher_dao;
        }

        @Override
        protected Void doInBackground(MovieDbModel... movies) {

            voucher_dao.UpdateMovies(movies[0]);

            return null;
        }
    }






    private void getOnlineMovies() {

        Log.e("Function Called","");

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        apiInterface.getPopularMovies(CONSTANTS.api_KEY,2).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, retrofit2.Response<MoviesResponse> response) {

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

                            newPopularMovies.add(model);


                        }


                }
            }


            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });



    }




}

package com.sample.movieapp.Room.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sample.movieapp.Model.MovieDbModel;

import java.util.List;

@Dao
public interface Movie_DAO {


    @Insert
    void AddMovie(MovieDbModel movies);

    @Update
    void UpdateMovies(MovieDbModel movies);

    @Delete
    void DeleteMovies(MovieDbModel movies);


    @Query("SELECT * FROM movie_offline ORDER BY release_date DESC")
    LiveData<List<MovieDbModel>> GetAllMovies();

}

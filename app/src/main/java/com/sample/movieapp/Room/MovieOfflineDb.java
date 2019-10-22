package com.sample.movieapp.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.sample.movieapp.Model.MovieDbModel;
import com.sample.movieapp.Room.DAO.Movie_DAO;


@Database(entities = {MovieDbModel.class},version = 1)
public abstract class MovieOfflineDb extends RoomDatabase {


    private  static  MovieOfflineDb Instance;

    public abstract Movie_DAO movie_dao();


    public  static  synchronized MovieOfflineDb getInstance(Context context)
    {
        if(Instance==null)
        {
            Instance= Room.databaseBuilder(context.getApplicationContext(),MovieOfflineDb.class,"Offline_Movie_List")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return Instance;
    }


}

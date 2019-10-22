package com.sample.movieapp.Model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Movie_offline")

public class MovieDbModel {


    @PrimaryKey
    private int id;

    private  int vote_count;

    private  String poster_path;

    private  boolean adult;

    private  String original_language;

    private  String original_title;

    private  double vote_average;

    private  String overview;

    private  String release_date;


    public MovieDbModel() {
    }

    public MovieDbModel(int id, int vote_count, String poster_path, boolean adult, String original_language, String original_title, double vote_average, String overview, String release_date) {
        this.id = id;
        this.vote_count = vote_count;
        this.poster_path = poster_path;
        this.adult = adult;
        this.original_language = original_language;
        this.original_title = original_title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}

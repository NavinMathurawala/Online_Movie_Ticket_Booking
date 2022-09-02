package com.bezkoder.spring.mssql.model;

import javax.persistence.*;

@Entity
@Table(name = "MOVIE")
public class Movie {
    @Id
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "MOVIE_ID")
    private long movieId;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "MOVIE_CITY")
    private String city;



    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }


}

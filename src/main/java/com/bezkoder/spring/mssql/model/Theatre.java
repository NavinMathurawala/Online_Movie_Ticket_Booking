package com.bezkoder.spring.mssql.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "THEATRE")
public class Theatre {


    @Id
    @Column(name = "THEATRE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long theatreId;


    @Column(name = "THEATRE_CITY")
    private String theatreCity;

    @Column(name = "THEATRE_NAME")
    private String theatreName;

    @Column(name = "MOVIE_NAME")
    private String movieName;


    @Column(name = "SCREENING_TIME")
    private Time screeningTime;

    @Column(name = "SCREENING_DATE")
    private Date screeningDate;

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public Time getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(Time screeningTime) {
        this.screeningTime = screeningTime;
    }

    public Date getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Date screeningDate) {
        this.screeningDate = screeningDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(long theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreCity() {
        return theatreCity;
    }

    public void setTheatreCity(String theatreCity) {
        this.theatreCity = theatreCity;
    }
}

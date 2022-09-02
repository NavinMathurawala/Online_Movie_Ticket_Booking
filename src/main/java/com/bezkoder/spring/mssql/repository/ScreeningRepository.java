package com.bezkoder.spring.mssql.repository;

import com.bezkoder.spring.mssql.model.Screening;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface ScreeningRepository extends CrudRepository<Screening, Long> {
    //List<Screening> findByScreeningDate(Date screeningDate);
    //List<Screening> findByMovieName(String movieName);
    Screening findByMovieNameAndTheatreId(String movieName, Long  theaterID);
}

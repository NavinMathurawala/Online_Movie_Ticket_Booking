package com.online.movie.booking.repository;

import com.online.movie.booking.model.Screening;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends CrudRepository<Screening, Long> {
    //List<Screening> findByScreeningDate(Date screeningDate);
    //List<Screening> findByMovieName(String movieName);
    Screening findByMovieNameAndTheatreId(String movieName, Long  theaterID);
}

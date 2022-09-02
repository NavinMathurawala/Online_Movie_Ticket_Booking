package com.online.movie.booking.repository;

import com.online.movie.booking.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, String> {



  List<Theatre> findByScreeningDate(Date screeningDate);

  Theatre findByTheatreName(String movieName);

  Theatre findByTheatreNameAndTheatreCity(String theatreName, String theatreCity);





}

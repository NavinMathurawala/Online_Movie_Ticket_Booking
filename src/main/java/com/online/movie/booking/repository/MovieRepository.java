package com.online.movie.booking.repository;

import com.online.movie.booking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {



  List<Movie> findByCityContaining(String city);



}

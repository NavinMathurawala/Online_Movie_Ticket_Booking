package com.bezkoder.spring.mssql.repository;

import com.bezkoder.spring.mssql.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {



  List<Movie> findByCityContaining(String city);



}

package com.bezkoder.spring.mssql.controller;

import com.bezkoder.spring.mssql.model.Movie;
import com.bezkoder.spring.mssql.model.MovieScreening;
import com.bezkoder.spring.mssql.model.Screening;
import com.bezkoder.spring.mssql.model.Theatre;
import com.bezkoder.spring.mssql.repository.MovieRepository;
import com.bezkoder.spring.mssql.repository.ScreenRepository;
import com.bezkoder.spring.mssql.repository.ScreeningRepository;
import com.bezkoder.spring.mssql.repository.TheatreRepository;
import com.bezkoder.spring.mssql.service.ScreeningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DateFormat;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MovieController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	ScreeningService screeningService;


    //Get Movie Catalogues by CityName
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = true) String city) {
		try {
			List<Movie> movieList = new ArrayList<Movie>();

			if (city == null)
				movieRepository.findAll().forEach(movieList::add);
			else
				movieRepository.findByCityContaining(city).forEach(movieList::add);

			if (movieList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(movieList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/movies/theatres")
	public Set<Theatre> getMoviesByDate(@RequestParam(required = true) String dateString) {


		Set<Theatre> result = this.screeningService.getTheatreByDate(this.screeningService.parseDate(dateString));

		return result;
	}


	@RequestMapping(value = "/movies/seats" , method = RequestMethod.POST)
	public String bookSeats(@RequestBody MovieScreening movieBooking) {

		LOGGER.info(movieBooking.getMovieName());

		LOGGER.info(movieBooking.getTheatreName());
		//LOGGER.info(movieBooking.getScreeningTime());

		LOGGER.info(Integer.toString(movieBooking.getNumSeats()));

		int bookedSeats = this.screeningService.getBookedSeats(movieBooking);
		int totalSeats = this.screeningService.getTotalSeats(movieBooking);

		if ((bookedSeats+movieBooking.getNumSeats()) > totalSeats)
			return "error";

		int seats = this.screeningService.bookSeats(movieBooking, bookedSeats+movieBooking.getNumSeats());

		return String.valueOf(seats);


	}


}

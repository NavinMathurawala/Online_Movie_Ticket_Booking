package com.online.movie.booking.service;


import com.online.movie.booking.model.MovieScreening;
import com.online.movie.booking.model.Screening;
import com.online.movie.booking.model.Theatre;
import com.online.movie.booking.repository.MovieRepository;
import com.online.movie.booking.repository.ScreenRepository;
import com.online.movie.booking.repository.ScreeningRepository;
import com.online.movie.booking.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScreeningService {
    private ScreeningRepository screeningRepository;
    private MovieRepository movieRepository;
    private TheatreRepository theatreRepository;

    private ScreenRepository screenRepository;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public ScreeningService(ScreeningRepository screeningRepository, MovieRepository movieRepository, TheatreRepository theatreRepository
                            ,  ScreenRepository screenRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;

        this.screenRepository = screenRepository;
    }

    public int getBookedSeats(MovieScreening movieScreening) {
        Screening screening = getScreening(movieScreening);
        return screening.getBookedTickets();
    }

    public int getTotalSeats(MovieScreening movieScreening) {
        Screening screening = getScreening(movieScreening);
        long screenId = screening.getScreenId();
        return screenRepository.findByScreenId(screenId).getSeatsNum();
    }

    public int bookSeats(MovieScreening movieScreening, int seats) {
        Screening screening = getScreening(movieScreening);
        screening.setBookedTickets(seats);
        screeningRepository.save(screening);
        return getBookedSeats(movieScreening);
    }

    private Screening getScreening(MovieScreening movieScreening) {
        Theatre theatre = theatreRepository.findByTheatreNameAndTheatreCity(movieScreening.getTheatreName(), movieScreening.getTheatreCity());
        if (theatre == null)
            return null;
        Screening screening =  screeningRepository.findByMovieNameAndTheatreId(movieScreening.getMovieName(), theatre.getTheatreId());

        return screening;
    }


    public Set<Theatre> getTheatreByDate(Date date) {
        Iterable<Theatre> theatre = this.theatreRepository.findByScreeningDate(new java.sql.Date(date.getTime()));
        Set<Theatre> theatres = new HashSet<Theatre>();

        if (theatre != null) {
            for (Theatre theatre1 : theatre) {
                Theatre theatre2 = theatreRepository.findByTheatreName(theatre1.getTheatreName());
                theatres.add(theatre2);
            }
        }

        return theatres;
    }

    public Date parseDate(String  dateString) {

        Date date = null;
        if (dateString != null) {
            try {
                date = DATE_FORMAT.parse(dateString);

            } catch (ParseException pe) {
                date = new Date();
            }
        } else {
            date = new Date();
        }

        return date;
    }

    public void deleteMovie(String movieName) {
        movieRepository.deleteById((movieName));
    }
}

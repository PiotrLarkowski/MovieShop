package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Movie.MovieNotFoundException;
import com.example.MovieShop.Objects.Actor;
import com.example.MovieShop.Objects.Movie;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithNamesOfActorsAppeared;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutIdAndList;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutList;
import com.example.MovieShop.Repositorys.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorService actorService;

    public MovieService(MovieRepository movieRepository, ActorService actorService) {
        this.movieRepository = movieRepository;
        this.actorService = actorService;
    }

    public Movie createMovie(MovieWithoutIdAndList movieWithoutIdAndList) {
        Movie movie = Movie.builder()
                .title(movieWithoutIdAndList.getTitle())
                .review(movieWithoutIdAndList.getReview())
                .movieGenres(movieWithoutIdAndList.getMovieGenres())
                .listOfActorsInMovie(new ArrayList<>())
                .build();
        movieRepository.save(movie);
        log.info("Create new Movie");
        return movie;
    }

    public MovieWithoutList getMovieWithoutListById(Long id) {
        log.info("Returning movie by id:" + id);
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        MovieWithoutList movieWithoutList = MovieWithoutList.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .review(movie.getReview())
                .movieGenres(movie.getMovieGenres())
                .build();
        return movieWithoutList;
    }

    public List<MovieWithNamesOfActorsAppeared> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        movieRepository.findAll().forEach(movieList::add);
        List<MovieWithNamesOfActorsAppeared> movieWithNamesOfActorsAppeared = new ArrayList<>();
        movieWithNamesOfActorsAppeared = movieList.stream().map(movie -> MovieWithNamesOfActorsAppeared.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .review(movie.getReview())
                .movieGenres(movie.getMovieGenres())
                .listOfNamesActorsInMovie(movie.getListOfNamesActorsInMovie())
                .build()
        ).collect(Collectors.toList());
        log.info("Returning all movies");
        return movieWithNamesOfActorsAppeared;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    public MovieWithNamesOfActorsAppeared getMovieToSHowById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        List<String> movieListActorAppeared = new ArrayList<>(Collections.singletonList(movie.getListOfNamesActorsInMovie().toString()));
        MovieWithNamesOfActorsAppeared movieWithNamesOfActorsAppeared = MovieWithNamesOfActorsAppeared.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .review(movie.getReview())
                .movieGenres(movie.getMovieGenres())
                .listOfNamesActorsInMovie(movieListActorAppeared)
                .build();
        return movieWithNamesOfActorsAppeared;
    }

    public Movie updateMovie(MovieWithoutIdAndList movieWithoutIdAndList, Long id) {
        Movie movieById = getMovieById(id);
        movieById.setListOfActorsInMovie(new ArrayList<>());
        movieById.setTitle(movieWithoutIdAndList.getTitle());
        movieById.setReview(movieWithoutIdAndList.getReview());
        movieById.setMovieGenres(movieWithoutIdAndList.getMovieGenres());
        log.info("List of Actor for movie with id " + id + " has been cleaned");
        log.info("Updating movie");
        return movieById;
    }

    public MovieWithoutIdAndList addActorToMovie(Long actorId, Long movieId) {
        Movie movieById = getMovieById(movieId);
        Actor actorById = actorService.getActorById(actorId);
        movieById.addActorToMovie(actorById);
        actorById.addMovieToActor(movieById.getTitle());
        MovieWithoutIdAndList movieWithoutIdAndList = MovieWithoutIdAndList.builder()
                .title(movieById.getTitle())
                .review(movieById.getReview())
                .movieGenres(movieById.getMovieGenres())
                .build();
        log.info("Adding actor to movie");
        return movieWithoutIdAndList;
    }

    public MovieWithNamesOfActorsAppeared removeActorFromMovie(Long actorId, Long movieId) {
        Movie movieById = getMovieById(movieId);
        movieById.removeActorFromMovie(actorService.getActorById(actorId));
        actorService.getActorById(actorId).getMovieListActorAppeared().remove(movieById.getTitle());
        MovieWithNamesOfActorsAppeared movieWithNamesOfActorsAppeared = MovieWithNamesOfActorsAppeared.builder()
                .movieId(movieId)
                .title(movieById.getTitle())
                .review(movieById.getReview())
                .listOfNamesActorsInMovie(movieById.getListOfNamesActorsInMovie())
                .movieGenres(movieById.getMovieGenres())
                .build();
        log.info("Removing actor from movie");
        return movieWithNamesOfActorsAppeared;
    }

    public void deleteMovie(Long id) {
        Movie movieById = getMovieById(id);
        movieRepository.delete(movieById);
        log.info("Deleting actor from movie");
    }
}

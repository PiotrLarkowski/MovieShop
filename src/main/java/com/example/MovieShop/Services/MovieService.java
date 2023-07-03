package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Movie.MovieNotFoundException;
import com.example.MovieShop.Objects.Movie;
import com.example.MovieShop.Objects.MovieRent;
import com.example.MovieShop.ObjectsDto.Movie.MovieDto;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutIdAndList;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutList;
import com.example.MovieShop.Repositorys.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieRentService movieRentService;
    private final ActorService actorService;

    public MovieService(MovieRepository movieRepository, MovieRentService movieRentService, ActorService actorService) {
        this.movieRepository = movieRepository;
        this.movieRentService = movieRentService;
        this.actorService = actorService;
    }

    public Movie createMovie(MovieWithoutIdAndList movieWithoutIdAndList){
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
    public MovieWithoutList getMovieWithoutListById(Long id){
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
    public List<MovieWithoutList> getAllMovies(){
        List<Movie> movieList = new ArrayList<>();
        movieRepository.findAll().forEach(movieList::add);
        List<MovieWithoutList> movieWithoutLists = movieList.stream().map(movie -> MovieWithoutList.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .review(movie.getReview())
                .movieGenres(movie.getMovieGenres())
                .build()
        ).collect(Collectors.toList());
        log.info("Returning all movies");
        return movieWithoutLists;
    }
    private Movie getMovieById(Long id){
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }
    public Movie updateMovie(MovieWithoutIdAndList movieWithoutIdAndList, Long id){
        Movie movieById = getMovieById(id);
        movieById.setListOfActorsInMovie(new ArrayList<>());
        movieById.setTitle(movieWithoutIdAndList.getTitle());
        movieById.setReview(movieWithoutIdAndList.getReview());
        movieById.setMovieGenres(movieWithoutIdAndList.getMovieGenres());
        log.info("List of Actor for movie with id " + id +" has been cleaned");
        log.info("Updating movie");
        return movieById;
    }
    public Movie addActorToMovie(Long actorId, Long movieId){
        Movie movieById = getMovieById(movieId);
        movieById.addActorToMovie(actorService.getActorById(actorId));
        log.info("Adding actor to movie");
        return movieById;
    }
    public Movie removeActorToMovie(Long actorId, Long movieId){
        Movie movieById = getMovieById(movieId);
        movieById.removeActorToMovie(actorService.getActorById(actorId));
        log.info("Removing actor from movie");
        return movieById;
    }
    public void deleteMovie(Long id){
        Movie movieById = getMovieById(id);
        for (MovieRent movieRent: movieRentService.getAllMovieRent()) {
            if(Objects.equals(movieRent.getMovieRentalId(), id)){
                movieRentService.deleteMovieRentById(id);
            }
        }
        movieRepository.delete(movieById);
        log.info("Deleting movie");
    }
}

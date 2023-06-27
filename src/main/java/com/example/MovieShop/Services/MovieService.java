package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Movie.MovieNotFoundException;
import com.example.MovieShop.Objects.Movie;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.ObjectsDto.Movie.MovieDto;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutIdAndList;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutList;
import com.example.MovieShop.Repositorys.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Movie getMovieById(Long id){
        log.info("Returning movie by id:" + id);
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
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
    public Movie updateMovie(MovieDto movieDto, Long id){
        Movie movieById = getMovieById(id);
        movieById.setMovieGenres(movieDto.getMovieGenres());
        movieById.setTitle(movieById.getTitle());
        movieById.setReview(movieById.getReview());
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
        movieRepository.delete(movieById);
        log.info("Deleting actor from movie");
    }
}

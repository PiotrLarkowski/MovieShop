package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Movie;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutIdAndList;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutList;
import com.example.MovieShop.Services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody @Validated MovieWithoutIdAndList movieWithoutIdAndList){
        return movieService.createMovie(movieWithoutIdAndList);
    }
    @GetMapping
    public List<MovieWithoutList> getAllMovies(){
        return movieService.getAllMovies();
    }
    @GetMapping("/{id}")
    public MovieWithoutList getMovieById(@PathVariable Long id){
        return movieService.getMovieWithoutListById(id);
    }
    @PutMapping("/{id}")
    public Movie updateMovie(@RequestBody @Validated MovieWithoutIdAndList movieWithoutIdAndList, @PathVariable Long id){
        return movieService.updateMovie(movieWithoutIdAndList, id);
    }
    @PutMapping("/add/{actorId}/{movieId}")
    public MovieWithoutIdAndList addActorToMovie(@PathVariable Long actorId, @PathVariable Long movieId){
        return movieService.addActorToMovie(actorId, movieId);
    }
    @PutMapping("/remove/{actorId}/{movieId}")
    public MovieWithoutIdAndList removeActorToMovie(@PathVariable Long actorId, @PathVariable Long movieId){
        return movieService.removeActorFromMovie(actorId, movieId);
    }
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }
}

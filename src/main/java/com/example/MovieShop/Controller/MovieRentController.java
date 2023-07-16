package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.MovieRent;
import com.example.MovieShop.ObjectsDto.MovieRentDto.MovieRentDto;
import com.example.MovieShop.ObjectsDto.MovieRentDto.MovieRentToCreateDto;
import com.example.MovieShop.ObjectsDto.MovieRentDto.MovieRentToShow;
import com.example.MovieShop.Services.MovieRentService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/movieRent")
public class MovieRentController {
    private final MovieRentService movieRentService;
    public MovieRentController(MovieRentService movieRentService) {
        this.movieRentService = movieRentService;
    }
    @PostMapping("/{movieId}/{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieRentToCreateDto createMovieRent(@PathVariable Long movieId, @PathVariable Long clientId){
        return(movieRentService.createMovieRent(movieId, clientId));
    }
    @GetMapping
    public List<MovieRentToShow> getAllMovieRent(){
        return movieRentService.getAllMovieRent();
    }
    @GetMapping("{id}")
    public MovieRentToShow getMovieRentById(@PathVariable Long id){
        return movieRentService.getMovieRentById(id);
    }
    @DeleteMapping("{id}")
    public void deleteMovieRent(@PathVariable Long id){
        movieRentService.deleteMovieRentById(id);
    }
}

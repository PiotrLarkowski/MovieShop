package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.MoviesGenres;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path="/genres")
public class MoviesGenresController {
    @GetMapping
    public List<MoviesGenres> getMoviesGenresList(){
        return Arrays.asList(MoviesGenres.values());
    }
}

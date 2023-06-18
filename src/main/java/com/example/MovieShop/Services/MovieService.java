package com.example.MovieShop.Services;

import com.example.MovieShop.Objects.Actor;
import com.example.MovieShop.Objects.Movie;
import com.example.MovieShop.Objects.MoviesGenres;
import com.example.MovieShop.ObjectsDto.MovieDto;
import com.example.MovieShop.Repositorys.MovieRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Service
@Slf4j
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie CreateMovie(MovieDto movieDto){
        Movie movie = Movie.builder()
                .title(movieDto.getTitle())
                .review(movieDto.getReview())
                .movieGenres(movieDto.getMovieGenres())
                .build();
        movieRepository.save(movie);
        log.info("Create new Movie");
        return movie;
    }
}

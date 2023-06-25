package com.example.MovieShop.ObjectsDto.Movie;

import com.example.MovieShop.Objects.MoviesGenres;
import com.example.MovieShop.Objects.Actor;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDto {
    private List<Actor> listOfActorsInMovie;
    private String title;
    private String review;
    private MoviesGenres movieGenres;
}

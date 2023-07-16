package com.example.MovieShop.ObjectsDto.Movie;

import com.example.MovieShop.Objects.MoviesGenres;
import lombok.*;

import javax.persistence.ElementCollection;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieWithNamesOfActorsAppeared {
    private Long movieId;
    @ElementCollection(targetClass=String.class)
    private List<String> listOfNamesActorsInMovie;
    private String title;
    private String review;
    private MoviesGenres movieGenres;
}

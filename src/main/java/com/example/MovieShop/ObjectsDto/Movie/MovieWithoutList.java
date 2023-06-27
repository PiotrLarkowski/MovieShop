package com.example.MovieShop.ObjectsDto.Movie;

import com.example.MovieShop.Objects.MoviesGenres;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieWithoutList {
    private Long movieId;
    private String title;
    private String review;
    private MoviesGenres movieGenres;
}

package com.example.MovieShop.Objects;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @ManyToOne
    @JoinColumn(name="foreignActorId")
    private Long foreignMovieId;
    @ManyToOne
    @JoinColumn(name="foreignActorId")
    private List<Actor> listOfActorsInMovie;
    private String title;
    private String review;
    private MoviesGenres movieGenres;
}

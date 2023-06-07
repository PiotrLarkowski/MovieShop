package com.example.MovieShop.Objects;

import com.example.MovieShop.MoviesGenres;
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
    @ManyToOne
    @JoinColumn(name="movieListActorAppeared")
    @JoinColumn(name="listOfPurchasedMovies")
    private Long movieId;
    @OneToMany
    private List<Actor> listOfActorsInMovie;
    private String title;
    private String review;
    private MoviesGenres movieGenres;
}

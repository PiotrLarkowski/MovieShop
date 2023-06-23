package com.example.MovieShop.Objects;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "actorId",cascade = CascadeType.ALL)
    private List<Actor> listOfActorsInMovie;
    private String title;
    private String review;
    @Enumerated(EnumType.STRING)
    private MoviesGenres movieGenres;

    public void addActorToMovie(Actor actor){
        listOfActorsInMovie.add(actor);
    }
    public void removeActorToMovie(Actor actor){
        listOfActorsInMovie.remove(actor);
    }
}

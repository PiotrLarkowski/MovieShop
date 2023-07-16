package com.example.MovieShop.Objects;

import javax.persistence.*;

import com.example.MovieShop.ObjectsDto.Actor.ActorWithMovieTitleList;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @OneToMany(mappedBy = "actorInMovieId")
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

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", listOfActorsInMovie=" + listOfActorsInMovie +
                ", title='" + title + '\'' +
                ", review='" + review + '\'' +
                ", movieGenres=" + movieGenres +
                '}';
    }
}

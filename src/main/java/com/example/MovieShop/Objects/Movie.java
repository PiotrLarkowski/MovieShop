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
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @OneToMany(mappedBy = "actorInMovieId")
    private List<Actor> listOfActorsInMovie;
    @ElementCollection(targetClass=String.class)
    private List<String> listOfNamesActorsInMovie;
    private String title;
    private String review;
    @Enumerated(EnumType.STRING)
    private MoviesGenres movieGenres;

    public void addActorToMovie(Actor actor){
        listOfNamesActorsInMovie.add(actor.getActorFirstName() + " " + actor.getActorLastName());
    }
    public void removeActorFromMovie(Actor actor){
        listOfNamesActorsInMovie.remove(actor.getActorFirstName() + " " + actor.getActorLastName());
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

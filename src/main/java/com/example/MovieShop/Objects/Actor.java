package com.example.MovieShop.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;
    private String actorInMovieId;
    private String actorFirstName;
    private String actorLastName;
    private String description;
    @OneToMany(mappedBy = "movieId")
    private List<Movie> movieListActorAppeared;

    public void addMovieToActor(Movie movie){
        movieListActorAppeared.add(movie);
    }
    public void removeMovieFromActor(Movie movie){
        movieListActorAppeared.remove(movie);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorId=" + actorId +
                ", actorInMovieId='" + actorInMovieId + '\'' +
                ", actorFirstName='" + actorFirstName + '\'' +
                ", actorLastName='" + actorLastName + '\'' +
                ", description='" + description + '\'' +
                ", movieListActorAppeared=" + movieListActorAppeared +
                '}';
    }
}

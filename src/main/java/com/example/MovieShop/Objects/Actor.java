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
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;
    private String actorInMovieId;
    private String actorFirstName;
    private String actorLastName;
    private String description;
    @ElementCollection(targetClass=String.class)
    private List<String> movieListActorAppeared;

    public void addMovieToActor(String title){
        movieListActorAppeared.add(title);
    }
    public void removeMovieFromActor(String title){
        movieListActorAppeared.remove(title);
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

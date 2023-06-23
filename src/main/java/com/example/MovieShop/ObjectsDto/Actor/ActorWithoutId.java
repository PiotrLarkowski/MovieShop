package com.example.MovieShop.ObjectsDto.Actor;

import com.example.MovieShop.Objects.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ActorWithoutId {
    private String actorFirstName;
    private String actorLastName;
    private String description;
    private List<Movie> movieListActorAppeared;
}

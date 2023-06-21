package com.example.MovieShop.ObjectsDto.Actor;

import com.example.MovieShop.Objects.Movie;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActorDto {
    private String actorFirstName;
    private String actorLastName;
    private String description;
    private List<Movie> movieListActorAppeared;
}

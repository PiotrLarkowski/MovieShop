package com.example.MovieShop.ObjectsDto.Actor;

import com.example.MovieShop.Objects.Movie;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ActorDto {
    @NonNull
    private String actorFirstName;
    @NonNull
    private String actorLastName;
    @NonNull
    private String description;
    private List<Movie> movieListActorAppeared;
}

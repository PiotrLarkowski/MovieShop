package com.example.MovieShop.ObjectsDto;

import com.example.MovieShop.Objects.Movie;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ActorDto {
    private long actorId;
    private String actorFirstName;
    private String actorLastName;
    private String description;
    private List<Movie> movieListActorAppeared;
}

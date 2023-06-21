package com.example.MovieShop.ObjectsDto.Actor;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ActorWithoutList {
    private String actorFirstName;
    private String actorLastName;
    private String description;
}

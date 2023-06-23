package com.example.MovieShop.ObjectsDto.Actor;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ActorWithoutList {
    private Long actorId;
    private String actorFirstName;
    private String actorLastName;
    private String description;
}

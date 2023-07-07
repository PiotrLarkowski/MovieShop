package com.example.MovieShop.ObjectsDto.Actor;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ActorWithMovieTitleList {
    private Long actorId;
    private String actorFirstName;
    private String actorLastName;
    private String actorDescription;
    private List<String> actorMovieTitleAppearedList;
}

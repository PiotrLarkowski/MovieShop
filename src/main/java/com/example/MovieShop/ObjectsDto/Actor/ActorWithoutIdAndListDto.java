package com.example.MovieShop.ObjectsDto.Actor;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ActorWithoutIdAndListDto {
    @NonNull
    private String actorFirstName;
    @NonNull
    private String actorLastName;
    @NonNull
    private String description;
}

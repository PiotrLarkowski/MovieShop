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
@ToString
@EqualsAndHashCode
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;
    private Long foreignActorId;
    private String actorFirstName;
    private String actorLastName;
    private String description;
    @OneToMany(mappedBy = "foreignMovieId")
    private List<Movie> movieListActorAppeared;
}

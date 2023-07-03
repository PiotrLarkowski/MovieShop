package com.example.MovieShop.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Actor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;
    private String actorInMovieId;
    private String actorFirstName;
    private String actorLastName;
    private String description;
    @OneToMany(mappedBy = "movieId")
    @JsonIgnore
    private List<Movie> movieListActorAppeared;

}

package com.example.MovieShop.Objects;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name="actorId")
    private long actorId;
    private String actorFirstName;
    private String actorLastName;
    private String description;
    @OneToMany
    private List<Movie> movieListActorAppeared;
}

package com.example.MovieShop.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

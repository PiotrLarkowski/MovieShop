package com.example.MovieShop.Objects;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovieRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieRentalId;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client clientRentId;
    @ManyToOne
    @JoinColumn(name = "foreignMovieId")
    private Movie movieRentId;
}

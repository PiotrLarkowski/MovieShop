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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private int countOfBuy;
    private String clientFirstName;
    private String clientLastName;
    @OneToMany(mappedBy="foreignMovieId")
    private List<Movie> listOfMoviesRentByClient;
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;
}

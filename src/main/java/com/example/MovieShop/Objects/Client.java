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
    private int clientCountOfRent;
    private String clientFirstName;
    private String clientLastName;
    @OneToMany(mappedBy="movieId")
    private List<Movie> clientListOfMoviesRentByClient;
    @OneToOne
    @JoinColumn(name = "addressUUID")
    private Address address;

    public void addClientCountOfRent(){
        clientCountOfRent++;
    }
    public void loverClientCountOfRent(){
        clientCountOfRent--;
    }
}

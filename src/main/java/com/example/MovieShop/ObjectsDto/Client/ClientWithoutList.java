package com.example.MovieShop.ObjectsDto.Client;

import com.example.MovieShop.Objects.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ClientWithoutList {
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private int clientCountOfRent;
    private Address address;
    private List<String> clientTitleListOfMoviesRentByClient;

    public void addTitleToMovieList(String title){
        clientTitleListOfMoviesRentByClient.add(title);
    }
}

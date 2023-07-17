package com.example.MovieShop.ObjectsDto.Client;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientWithoutAddressId {
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private int clientCountOfRent;
    private List<String> clientTitleListOfMoviesRentByClient;
}

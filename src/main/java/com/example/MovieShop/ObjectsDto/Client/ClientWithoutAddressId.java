package com.example.MovieShop.ObjectsDto.Client;

import com.example.MovieShop.Objects.Address;
import com.example.MovieShop.ObjectsDto.Address.AddressWithoutId;
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
    private AddressWithoutId addressWithoutId;
    private List<String> clientTitleListOfMoviesRentByClient;
}

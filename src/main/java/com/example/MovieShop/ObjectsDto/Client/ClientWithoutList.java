package com.example.MovieShop.ObjectsDto.Client;

import com.example.MovieShop.Objects.Address;
import com.example.MovieShop.Objects.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ClientWithoutList {
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private Address address;
}

package com.example.MovieShop.ObjectsDto.Client;

import com.example.MovieShop.Objects.Movie;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto {
    private String clientFirstName;
    private String clientLastName;
    private List<Movie> clientListOfMoviesRentByClient;
}

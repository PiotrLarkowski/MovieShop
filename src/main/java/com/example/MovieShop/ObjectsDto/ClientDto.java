package com.example.MovieShop.ObjectsDto;

import com.example.MovieShop.Objects.Movie;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClientDto {
    private Long clientId;
    private int countOfBuy;
    private List<Movie> listOfPurchasedMovies;
    private String clientFirstName;
    private String clientLastName;
}

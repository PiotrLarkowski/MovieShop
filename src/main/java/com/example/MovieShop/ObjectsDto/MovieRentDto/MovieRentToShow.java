package com.example.MovieShop.ObjectsDto.MovieRentDto;

import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.Objects.Movie;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieRentToShow {
    private ClientWithoutList clientWithoutList;
    private Movie movieRentId;
    private boolean returned;
}

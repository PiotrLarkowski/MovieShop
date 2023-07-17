package com.example.MovieShop.ObjectsDto.MovieRentDto;

import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutList;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieRentToShow {
    private ClientWithoutList clientWithoutList;
    private MovieWithoutList movieWithoutList;
    private boolean returned;
}

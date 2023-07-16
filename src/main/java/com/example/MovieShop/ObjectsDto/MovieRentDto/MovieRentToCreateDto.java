package com.example.MovieShop.ObjectsDto.MovieRentDto;

import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithNamesOfActorsAppeared;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieRentToCreateDto {
    private ClientWithoutList clientRentId;
    private MovieWithNamesOfActorsAppeared movieRentId;
    private boolean returned;
}

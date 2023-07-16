package com.example.MovieShop.ObjectsDto.MovieRentDto;

import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.Objects.Movie;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieRentDto {
    private Client clientRentId;
    private Movie movieRentId;
    private boolean returned;
}

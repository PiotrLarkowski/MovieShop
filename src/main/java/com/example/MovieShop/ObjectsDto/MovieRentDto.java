package com.example.MovieShop.ObjectsDto;

import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.Objects.Movie;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovieRentDto {
    private Long movieRentalId;
    private Client clientRentId;
    private Movie movieRentId;
    private boolean returned;
}

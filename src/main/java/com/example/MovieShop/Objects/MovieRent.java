package com.example.MovieShop.Objects;

import javax.persistence.*;

import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithNamesOfActorsAppeared;
import com.example.MovieShop.ObjectsDto.MovieRentDto.MovieRentToShow;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieRentalId;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client clientRentId;
    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movieRentId;
    private boolean returned;

    public MovieRent changeReturnValue() {
        if(returned){
            returned = false;
        }else{
            returned = true;
        }
        return this;
    }
}

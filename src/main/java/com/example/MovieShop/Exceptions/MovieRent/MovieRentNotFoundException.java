package com.example.MovieShop.Exceptions.MovieRent;

public class MovieRentNotFoundException extends RuntimeException{
    public MovieRentNotFoundException(long id){
        super("Could not find movie rental by id: " + id);
    }
}

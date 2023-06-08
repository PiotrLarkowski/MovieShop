package com.example.MovieShop.Exceptions.Movie;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(long id){
        super("Could not find movie by id: " + id);
    }
}

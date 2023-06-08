package com.example.MovieShop.Exceptions.Actor;

public class ActorNotFoundException extends RuntimeException{
    public ActorNotFoundException(long id){
        super("Could not find actor by id: " + id);
    }
}

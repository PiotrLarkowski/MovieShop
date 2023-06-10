package com.example.MovieShop.Exceptions.MovieRent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MovieRentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MovieRentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpStatus movieRentNotFoundHandler(){
        return HttpStatus.NOT_FOUND;
    }
}

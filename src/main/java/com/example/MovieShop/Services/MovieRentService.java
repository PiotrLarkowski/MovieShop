package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Client.ClientNotFoundException;
import com.example.MovieShop.Exceptions.Movie.MovieNotFoundException;
import com.example.MovieShop.Exceptions.MovieRent.MovieRentNotFoundException;
import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.Objects.Movie;
import com.example.MovieShop.Objects.MovieRent;
import com.example.MovieShop.ObjectsDto.MovieRentDto;
import com.example.MovieShop.Repositorys.ClientRepository;
import com.example.MovieShop.Repositorys.MovieRentRepository;
import com.example.MovieShop.Repositorys.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class MovieRentService {
    private final MovieRentRepository movieRentRepository;
    private final ClientService clientService;
    private final MovieService movieService;

    public MovieRentService(MovieRentRepository movieRentRepository, ClientService clientService, MovieService movieService) {
        this.movieRentRepository = movieRentRepository;
        this.clientService = clientService;
        this.movieService = movieService;
    }

    public MovieRent createMovieRent(Long movieId, Long clientId){
        Client client = clientService.getClientById(clientId);
        Movie movie = movieService.getMovieById(movieId);
        MovieRent movieRent = MovieRent.builder()
                .clientRentId(client)
                .movieRentId(movie)
                .returned(false)
                .build();
        movieRentRepository.save(movieRent);
        clientService.addClientCountOfBuyByOne(client.getClientId());
        return movieRent;
    }
    public List<MovieRent> getAllMovieRent(){
        List<MovieRent> movieRentArrayList = new ArrayList<>();
        movieRentRepository.findAll().forEach(movieRent -> movieRentArrayList.add(movieRent));
        return movieRentArrayList;
    }
    public MovieRent getMovieRentById(Long id){
        return movieRentRepository.findById(id).orElseThrow(() -> new MovieRentNotFoundException(id));
    }
    public MovieRent updateMovieRent(@RequestBody @Validated MovieRentDto movieRentDto, @PathVariable Long id){
        MovieRent movieRent = movieRentRepository.findById(id).orElseThrow(() -> new MovieRentNotFoundException(id));
        movieRent.setClientRentId(movieRentDto.getClientRentId());
        movieRent.setMovieRentId(movieRentDto.getMovieRentId());
        movieRent.setReturned(movieRent.isReturned());
        return movieRent;
    }
    public void deleteMovieRentById(Long id){
        movieRentRepository.delete(movieRentRepository.findById(id).orElseThrow(() -> new MovieRentNotFoundException(id)));
    }

}

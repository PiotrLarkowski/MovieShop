package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Client.ClientNotFoundException;
import com.example.MovieShop.Exceptions.Movie.MovieNotFoundException;
import com.example.MovieShop.Exceptions.MovieRent.MovieRentNotFoundException;
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
    private final ClientRepository clientRepository;
    private final MovieRepository movieRepository;

    public MovieRentService(MovieRentRepository movieRentRepository, ClientRepository clientRepository, MovieRepository movieRepository) {
        this.movieRentRepository = movieRentRepository;
        this.clientRepository = clientRepository;
        this.movieRepository = movieRepository;
    }

    public MovieRent createMovieRent(Long movieId, Long clientId){
        MovieRent movieRent = MovieRent.builder()
                .clientRentId(clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId)))
                .movieRentId(movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId)))
                .returned(false)
                .build();
        movieRentRepository.save(movieRent);
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
        movieRent.setMovieRentalId(movieRentDto.getMovieRentalId());
        movieRent.setReturned(movieRent.isReturned());
        return movieRent;
    }
    public void deleteMovieRentById(Long id){
        movieRentRepository.delete(movieRentRepository.findById(id).orElseThrow(() -> new MovieRentNotFoundException(id)));
    }

}

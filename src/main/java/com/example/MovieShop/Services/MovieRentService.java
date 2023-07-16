package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.MovieRent.MovieRentNotFoundException;
import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.Objects.Movie;
import com.example.MovieShop.Objects.MovieRent;
import com.example.MovieShop.Objects.MoviesGenres;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutList;
import com.example.MovieShop.ObjectsDto.MovieRentDto.MovieRentDto;
import com.example.MovieShop.ObjectsDto.MovieRentDto.MovieRentToShow;
import com.example.MovieShop.Repositorys.MovieRentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<MovieRentToShow> getAllMovieRent(){
        List<MovieRent> movieRentArrayList = new ArrayList<>();
        movieRentRepository.findAll().forEach(movieRentArrayList::add);
        List<Client> listOfClients = new ArrayList<>();
        movieRentArrayList.forEach(movieRent -> listOfClients.add(clientService.getClientById(movieRent.getClientRentId().getClientId())));
        List<String> movieTitles = new ArrayList<>();
        listOfClients.forEach(client -> client.getClientListOfMoviesRentByClient().forEach(movie-> movieTitles.add(movie.getTitle())));
        List<ClientWithoutList> clientWithoutList = listOfClients.stream().map(client -> ClientWithoutList.builder()
                .clientId(client.getClientId())
                .clientFirstName(client.getClientFirstName())
                .clientLastName(client.getClientLastName())
                .clientCountOfRent(client.getClientCountOfRent())
                .address(client.getAddress())
                .clientTitleListOfMoviesRentByClient(movieTitles)
                .build()).collect(Collectors.toList());
        List<Movie> listOfMovies = new ArrayList<>();
        movieRentArrayList.forEach(movieRent -> listOfMovies.add(movieService.getMovieById(movieRent.getMovieRentId().getMovieId())));
        List<MovieWithoutList> movieWithoutListCollection = listOfMovies.stream().map(movie -> MovieWithoutList.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .review(movie.getReview())
                .movieGenres(movie.getMovieGenres())
                .build()).collect(Collectors.toList());
        List<MovieRentToShow> movieRentToShow = new ArrayList<>();
        for (int i = 0; i < movieWithoutListCollection.size(); i++) {
            movieRentToShow.add(new MovieRentToShow(clientWithoutList.get(i),movieWithoutListCollection.get(i),movieRentArrayList.get(i).isReturned()));
        }
        return movieRentToShow;
    }
    public MovieRentToShow getMovieRentById(Long id){
        MovieRent movieRent = movieRentRepository.findById(id).orElseThrow(() -> new MovieRentNotFoundException(id));
        List<String> movieListTitle = new ArrayList<>();
        movieRent.getClientRentId().getClientListOfMoviesRentByClient().forEach(movie -> movieListTitle.add(movie.getTitle()));
        MovieRentToShow movieRentToShow = MovieRentToShow.builder()
                .clientWithoutList(ClientWithoutList.builder()
                        .clientId(movieRent.getClientRentId().getClientId())
                        .clientFirstName(movieRent.getClientRentId().getClientFirstName())
                        .clientLastName(movieRent.getClientRentId().getClientLastName())
                        .clientCountOfRent(movieRent.getClientRentId().getClientCountOfRent())
                        .address(movieRent.getClientRentId().getAddress())
                        .clientTitleListOfMoviesRentByClient(movieListTitle)
                        .build())
                .movieWithoutList(MovieWithoutList.builder()
                        .movieId(movieRent.getMovieRentId().getMovieId())
                        .title(movieRent.getMovieRentId().getTitle())
                        .review(movieRent.getMovieRentId().getReview())
                        .movieGenres(movieRent.getMovieRentId().getMovieGenres())
                        .build())
                .returned(movieRent.isReturned())
                .build();
        return movieRentToShow;
    }
    public void deleteMovieRentById(Long id){
        movieRentRepository.delete(movieRentRepository.findById(id).orElseThrow(() -> new MovieRentNotFoundException(id)));
    }

}

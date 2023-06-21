package com.example.MovieShop;

import com.example.MovieShop.Objects.*;
import com.example.MovieShop.ObjectsDto.*;
import com.example.MovieShop.ObjectsDto.Actor.ActorDto;
import com.example.MovieShop.Services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class MovieShopApplication implements CommandLineRunner {
	private final ActorService actorService;
	private final AddressService addressService;
	private final ClientService clientService;
	private final MovieService movieService;
	private final MovieRentService movieRentService;

	public MovieShopApplication(ActorService actorService, AddressService addressService, ClientService clientService, MovieService movieService, MovieRentService movieRentService) {
		this.actorService = actorService;
		this.addressService = addressService;
		this.clientService = clientService;
		this.movieService = movieService;
		this.movieRentService = movieRentService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AddressDto addressDto = new AddressDto("Kraków","Fabryczna 13/2");
		ClientDto clientDto = new ClientDto("Piotr","Larkowski",new ArrayList<>());
		ActorDto actorDto = new ActorDto("Tom ","Cruise","Tom Cruise, właśc. (...) Za początek wielkiej kariery aktora uważa się nominowaną do Złotego Globu rolę w filmie Ryzykowny interes.", new ArrayList<>());

		Client client = clientService.createClient(clientDto);
		Address address = addressService.createAddress(addressDto, client.getClientId());
		Actor newActor = actorService.createNewActor(actorDto);

		MovieDto movieDto = new MovieDto(new ArrayList<Actor>(Arrays.asList(newActor)),"Ryzykowny Interes","Pod nieobecność rodziców nastolatek Joel poznaje kobietę lekkich obyczajów, Lanę, i za jej namową urządza w miejscu zamieszkania... dom publiczny.", MoviesGenres.COMEDY);
		Movie movie = movieService.CreateMovie(movieDto);
		movieRentService.createMovieRent(movie.getMovieId(),client.getClientId());
	}
}

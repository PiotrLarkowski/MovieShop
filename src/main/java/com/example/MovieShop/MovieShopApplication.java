package com.example.MovieShop;

import com.example.MovieShop.Objects.*;
import com.example.MovieShop.ObjectsDto.Actor.ActorWithoutIdAndListDto;
import com.example.MovieShop.ObjectsDto.Address.AddressDto;
import com.example.MovieShop.ObjectsDto.Address.AddressWithoutId;
import com.example.MovieShop.ObjectsDto.Client.ClientDto;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutAddressId;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutListIdAndAddress;
import com.example.MovieShop.ObjectsDto.Movie.MovieWithoutIdAndList;
import com.example.MovieShop.Services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class MovieShopApplication implements CommandLineRunner {
    private final ActorService actorService;
    private final AddressService addressService;
    private final ClientService clientService;
    private final MovieService movieService;
    private final MovieRentService movieRentService;

    public List<AddressWithoutId> allAddress = new ArrayList<>();
    public List<ClientWithoutList> allClients = new ArrayList<>();
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
        createExampleData();
    }

    private void createExampleData() {
        log.info("-----------------------------------------------CREATING EXAMPLE DATA-----------------------------------------------");
        String[] arrayOfCities = {"Kraków", "Szczecin", "Bydgoszcz", "Lodz", "Radom", "Warszawa", "Gdansk", "Sopot", "Opole"
                , "Poznań", "Lublin", "Białystok"};
        String[] arrayOfStreets = {"1 Maja", "3 Maja", "11 Listopada", "Akademicka", "gen. Władysława Andersa", "Armii Krajowej",
                "Balonowa", "Michała Bałuckiego", "Bankowa", "Tadeusza Kościuszki", "Chłodna", "Chmielna"};
        String[] arrayOfClientFirstNames = {"Piotr", "Pawel", "Tomek", "Krystian", "Marcin", "Sebastian", "Wiktor", "Slawek",
                "Zbyszek", "Norbert"};
        String[] arrayOfClientLastName = {"Larkowski", "Kowalski", "Rowinski", "Gut", "Baczkowski", "Baginski", "Chacinski",
                "Zarski", "Wojnarski", "Wolanowski"};

        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            addressService.createAddress(AddressDto.builder()
                            .city(arrayOfCities[rand.nextInt(10)])
                            .street(arrayOfStreets[rand.nextInt(10)])
                            .build()
            );
            clientService.createClient(ClientWithoutListIdAndAddress.builder()
                    .clientFirstName(arrayOfClientFirstNames[rand.nextInt(10)])
                    .clientLastName(arrayOfClientLastName[rand.nextInt(10)])
                    .build());
            actorService.createNewActor(ActorWithoutIdAndListDto.builder()
                    .actorFirstName(arrayOfClientFirstNames[rand.nextInt(10)])
                    .actorLastName(arrayOfClientLastName[rand.nextInt(10)])
                    .description("description")
                    .build());
        }

        Movie movie = movieService.createMovie(MovieWithoutIdAndList.builder()
                .title("Ryzykowny Interes")
                .review("Pod nieobecność rodziców nastolatek Joel poznaje kobietę lekkich obyczajów, Lanę, i za jej namową urządza w miejscu zamieszkania... dom publiczny.")
                .movieGenres(MoviesGenres.COMEDY)
                .build());

        movieRentService.createMovieRent(movie.getMovieId(), clientService.getClientWithoutListById(1L).getClientId());

        ActorWithoutIdAndListDto actorForUpdate = ActorWithoutIdAndListDto.builder()
                .actorFirstName("Robin")
                .actorLastName("Williams")
                .description("description")
                .build();

        allAddress = addressService.getAllAddress();
        allClients = clientService.getAllClients();
        for (int i = 0; i < allAddress.size(); i++) {
            clientService.updateClientAddress(allAddress.get(i).getAddressId(),allClients.get(i).getClientId());
        }

        movieService.addActorToMovie(actorService.getActorById(2L).getActorId(), movie.getMovieId());

        actorService.updateActor(actorForUpdate, 1L);

        log.info("----------------------------------------END OF CREATING EXAMPLE DATA-----------------------------------------------");
    }

    @Bean
    public Docket get() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .build().apiInfo(createApiInfo());
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("MovieShopApi",
                "Rental movie database",
                "1.00",
                "",
                new Contact("Piotr", "https://www.linkedin.com/in/piotr-larkowski/", "p.larkowski90@gmail.com"),
                "My own licence",
                "My own licence",
                Collections.emptyList()
        );
    }
}

package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Actor.ActorNotFoundException;
import com.example.MovieShop.Objects.Actor;
import com.example.MovieShop.Objects.Movie;
import com.example.MovieShop.ObjectsDto.Actor.ActorWithoutIdAndListDto;
import com.example.MovieShop.ObjectsDto.Actor.ActorWithMovieTitleList;
import com.example.MovieShop.Repositorys.ActorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @PostMapping
    public Actor createNewActor(@RequestBody @Validated ActorWithoutIdAndListDto actorWithoutIdAndListDto){
        Actor actor = Actor.builder()
                .actorInMovieId(UUID.randomUUID().toString())
                .actorFirstName(actorWithoutIdAndListDto.getActorFirstName())
                .actorLastName(actorWithoutIdAndListDto.getActorLastName())
                .description(actorWithoutIdAndListDto.getDescription())
                .movieListActorAppeared(new ArrayList<>())
                .build();
        actorRepository.save(actor);
        log.info("Actor has been created");
        return(actor);
    }
    @GetMapping()
    public List<ActorWithMovieTitleList> getAllActors(){
        List<Actor> actorList = new ArrayList<>();
        actorRepository.findAll().forEach(actorList::add);
        List<ActorWithMovieTitleList> actorWithMovieTitleLists = actorList.stream()
                .map(actorInList -> ActorWithMovieTitleList.builder()
                        .actorId(actorInList.getActorId())
                        .actorFirstName(actorInList.getActorFirstName())
                        .actorLastName(actorInList.getActorLastName())
                        .actorDescription(actorInList.getDescription())
                        .actorMovieTitleAppearedList(actorInList.getMovieListActorAppeared())
//                        .actorMovieTitleAppearedList(actorInList.getMovieListActorAppeared().stream().map(Movie::getTitle).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        log.info("All actors has been shown");
        return actorWithMovieTitleLists;
    }
    @GetMapping(path="/{id}")
    public ActorWithMovieTitleList getActorByIdWithoutList(@PathVariable Long id){
        log.info("Actor has been shown");
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
//        List<String> listOfMovieActorAppeared = new ArrayList<>();
//        actor.getMovieListActorAppeared().forEach(movie -> listOfMovieActorAppeared.add(movie.getTitle()));
        return ActorWithMovieTitleList.builder()
                .actorId(actor.getActorId())
                .actorFirstName(actor.getActorFirstName())
                .actorLastName(actor.getActorLastName())
                .actorDescription(actor.getDescription())
                .actorMovieTitleAppearedList(actor.getMovieListActorAppeared())
                .build();
    }
    public Actor getActorById(@PathVariable Long id){
        log.info("Actor has been shown");
        return actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
    }
    @PutMapping
    public Actor updateActor(@RequestBody @Validated ActorWithoutIdAndListDto actorWithoutIdAndListDto, Long id){
        Actor actor = getActorById(id);
        actor.setActorFirstName(actorWithoutIdAndListDto.getActorFirstName());
        actor.setActorLastName(actorWithoutIdAndListDto.getActorLastName());
        actor.setDescription(actorWithoutIdAndListDto.getDescription());
        log.info("Actor has been updated");
        return actor;
    }
    @DeleteMapping(path ="{id}")
    public void deleteActor(@PathVariable Long id){
        actorRepository.delete(getActorById(id));
        log.info("Actor has been deleted");
    }
}

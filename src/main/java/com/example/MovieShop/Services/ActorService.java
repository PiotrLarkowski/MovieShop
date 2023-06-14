package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Actor.ActorNotFoundException;
import com.example.MovieShop.Objects.Actor;
import com.example.MovieShop.ObjectsDto.ActorDto;
import com.example.MovieShop.Repositorys.ActorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @PostMapping
    public Actor createNewActor(@RequestBody @Validated ActorDto actorDto, @PathVariable Long id){
        Actor actor = Actor.builder()
                .actorId(id)
                .actorFirstName(actorDto.getActorFirstName())
                .actorLastName(actorDto.getActorLastName())
                .description(actorDto.getDescription())
                .movieListActorAppeared(actorDto.getMovieListActorAppeared())
                .build();
        actorRepository.save(actor);
        log.info("Actor has been created");
        return(actor);
    }
    @GetMapping()
    public List<Actor> getAllActors(){
        List<Actor> actorList = new ArrayList<>();
        actorRepository.findAll().forEach(actorList::add);
        log.info("All actors has been shown");
        return actorList;
    }
    @GetMapping(path="/{id}")
    public Actor getActorById(@PathVariable Long id){
        log.info("Actor has been shown");
        return actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
    }
    @PutMapping
    public Actor updateActor(@RequestBody @Validated ActorDto actorDto, @PathVariable Long id){
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
        actor.setActorFirstName(actorDto.getActorFirstName());
        actor.setActorLastName(actorDto.getActorLastName());
        actor.setDescription(actorDto.getDescription());
        actor.setMovieListActorAppeared(actorDto.getMovieListActorAppeared());
        log.info("Actor has been updated");
        return actor;
    }
    @DeleteMapping(path ="{id}")
    public void deleteActor(@PathVariable Long id){
        actorRepository.delete(actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id)));
        log.info("Actor has been deleted");
    }
}

package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Actor.ActorNotFoundException;
import com.example.MovieShop.Objects.Actor;
import com.example.MovieShop.ObjectsDto.Actor.ActorWithoutId;
import com.example.MovieShop.ObjectsDto.Actor.ActorWithoutIdAndListDto;
import com.example.MovieShop.ObjectsDto.Actor.ActorWithoutList;
import com.example.MovieShop.Repositorys.ActorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
    public List<ActorWithoutList> getAllActors(){
        List<Actor> actorList = new ArrayList<>();
        actorRepository.findAll().forEach(actorList::add);
        List<ActorWithoutList> actorWithoutList = actorList.stream()
                .map(actorInList -> ActorWithoutList.builder()
                        .actorId(actorInList.getActorId())
                        .actorFirstName(actorInList.getActorFirstName())
                        .actorLastName(actorInList.getActorLastName())
                        .description(actorInList.getDescription())
                        .build())
                .collect(Collectors.toList());
        log.info("All actors has been shown");
        return actorWithoutList;
    }
    @GetMapping(path="/{id}")
    public ActorWithoutList getActorByIdWithoutList(@PathVariable Long id){
        log.info("Actor has been shown");
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
        ActorWithoutList actorWithoutList = ActorWithoutList.builder()
                .actorId(actor.getActorId())
                .actorFirstName(actor.getActorFirstName())
                .actorLastName(actor.getActorLastName())
                .description(actor.getDescription())
                .build();
        return actorWithoutList;
    }
    public Actor getActorById(@PathVariable Long id){
        log.info("Actor has been shown");
        return actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
    }
    @PutMapping
    public Actor updateActor(@RequestBody @Validated ActorWithoutIdAndListDto actorWithoutIdAndListDto, Long id){
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
        actor.setActorFirstName(actorWithoutIdAndListDto.getActorFirstName());
        actor.setActorLastName(actorWithoutIdAndListDto.getActorLastName());
        actor.setDescription(actorWithoutIdAndListDto.getDescription());
        log.info("Actor has been updated");
        return actor;
    }
    @DeleteMapping(path ="{id}")
    public void deleteActor(@PathVariable Long id){
        actorRepository.delete(actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id)));
        log.info("Actor has been deleted");
    }
}

package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Actor;
import com.example.MovieShop.ObjectsDto.Actor.ActorWithoutIdAndListDto;
import com.example.MovieShop.ObjectsDto.Actor.ActorWithMovieTitleList;
import com.example.MovieShop.Services.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/actor")
public class ActorController {
    private final ActorService actorService;
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(@RequestBody @Validated ActorWithoutIdAndListDto actorWithoutIdAndListDto){
        return actorService.createNewActor(actorWithoutIdAndListDto);
    }
    @GetMapping("/{id}")
    public ActorWithMovieTitleList getActorById(@PathVariable Long id){
        return actorService.getActorByIdWithoutList(id);
    }
    @GetMapping
    public List<ActorWithMovieTitleList> getAllActors(){
        return actorService.getAllActors();
    }
    @PutMapping("/{id}")
    public Actor updateActor(@RequestBody @Validated ActorWithoutIdAndListDto actorWithoutIdAndListDto, @PathVariable Long id){
        return actorService.updateActor(actorWithoutIdAndListDto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Long id){
        actorService.deleteActor(id);
    }
}

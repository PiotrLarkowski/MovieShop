package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Actor;
import com.example.MovieShop.ObjectsDto.ActorDto;
import com.example.MovieShop.Services.ActorService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/Actor")
public class ActorController {
    private final ActorService actorService;
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @PostMapping
    public Actor createActor(@RequestBody @Validated ActorDto actorDto){
        return actorService.createNewActor(actorDto);
    }
    @GetMapping("{id}")
    public Actor getActorById(@PathVariable Long id){
        return actorService.getActorById(id);
    }
    @GetMapping
    public List<Actor> getAllActors(){
        return actorService.getAllActors();
    }
    @PutMapping("/{id}")
    public Actor updateActor(@RequestBody @Validated ActorDto actorDto, @PathVariable Long id){
        return actorService.updateActor(actorDto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Long id){
        actorService.deleteActor(id);
    }
}

package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Actor;
import com.example.MovieShop.ObjectsDto.Actor.ActorDto;
import com.example.MovieShop.ObjectsDto.Actor.ActorWithoutList;
import com.example.MovieShop.Services.ActorService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(path ="/actor")
public class ActorController {
    private final ActorService actorService;
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @PostMapping
    public Actor createActor(@RequestBody @Validated ActorWithoutList actorWithoutList){
        return actorService.createNewActor(actorWithoutList);
    }
    @GetMapping("/{id}")
    public ActorWithoutList getActorById(@PathVariable Long id){
        return actorService.getActorByIdWithoutList(id);
    }
    @GetMapping
    public List<ActorWithoutList> getAllActors(){
        return actorService.getAllActors();
    }
    @PutMapping("/{id}")
//    @RolesAllowed({"ADMIN"})
    public Actor updateActor(@RequestBody @Validated ActorDto actorDto, @PathVariable Long id){
        return actorService.updateActor(actorDto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Long id){
        actorService.deleteActor(id);
    }
}

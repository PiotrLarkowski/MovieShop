package com.example.MovieShop.Repositorys;

import com.example.MovieShop.Objects.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {
}

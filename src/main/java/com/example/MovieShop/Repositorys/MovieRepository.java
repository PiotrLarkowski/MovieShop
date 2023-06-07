package com.example.MovieShop.Repositorys;

import com.example.MovieShop.Objects.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}

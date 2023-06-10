package com.example.MovieShop.Repositorys;

import com.example.MovieShop.Objects.MovieRent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRentRepository extends CrudRepository<MovieRent, Long> {
}

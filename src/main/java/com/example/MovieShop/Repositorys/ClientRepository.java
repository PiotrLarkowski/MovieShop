package com.example.MovieShop.Repositorys;

import com.example.MovieShop.Objects.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}

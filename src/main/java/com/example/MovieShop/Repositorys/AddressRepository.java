package com.example.MovieShop.Repositorys;

import com.example.MovieShop.Objects.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}

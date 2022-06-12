package com.example.carrental.repository;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RentRepository extends CrudRepository<Rent,Long> {

    @Override
    Optional<Rent> findById(Long id);
}

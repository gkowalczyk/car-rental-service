package com.example.carrental.repository;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
}

package com.example.carrental.repository;

import com.example.carrental.domain.Equipment;
import com.example.carrental.domain.Rent;
import com.example.carrental.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

    @Override
    Optional<Equipment> findById(Long id);
}
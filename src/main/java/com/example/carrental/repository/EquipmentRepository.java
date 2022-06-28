package com.example.carrental.repository;

import com.example.carrental.domain.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
    @Override
    List<Equipment> findAll();
}
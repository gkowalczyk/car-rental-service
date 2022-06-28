package com.example.carrental.repository;

import com.example.carrental.domain.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Override
    Optional<Car> findById(Long id);

    Optional<Car> findCarByCategoryEquals(String className);

    @Override
    List<Car> findAll();

    @Query(nativeQuery = true)
    List<Car> findByDate(
            @Param("START_RENT") LocalDate start,
            @Param("END_RENT") LocalDate end,
            @Param("CAR_CLASS") long classCarId);
}

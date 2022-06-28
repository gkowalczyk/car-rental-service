package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.exception.CarNotFoundException;
import com.example.carrental.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class CarService {
    public static final String ECONOMIC = "ECONOMIC";
    public static final String MIDDLE = "MIDDLE";
    public static final String VIP = "VIP";

    private final CarRepository carRepository;


    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCarsFromDb() {
        return carRepository.findAll();
    }

    public List<Car> getAllCars(LocalDate startRent, LocalDate endRent, String className) throws Exception {
        try {
            Car carCategory = carRepository.findCarByCategoryEquals(className).orElseThrow(Exception::new);
            return carRepository.findByDate(startRent, endRent, carCategory.getId());
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }

    public Car addCar(final Car car) {
        log.info("Adding car to DB");
        return carRepository.save(car);
    }

    public Car updateStatusCar(final Car car, final Long id) throws CarNotFoundException {
        Optional<Car> optionalCar = carRepository.findById(id);
        Car updateCar = optionalCar.orElseThrow(() ->
                new CarNotFoundException("Id car:" + id + ":not found"));
        log.info("Update car to DB");
        updateCar.setAvailable(car.isAvailable());
        updateCar.setDailyCost(car.getDailyCost());
        updateCar.setCategory(car.getCategory());
        updateCar.setRegistration(car.getRegistration());
        return carRepository.save(updateCar);
    }

    public void deleteCarFromDB(final Long id) throws CarNotFoundException {
        try {
            carRepository.deleteById(id);
            log.info("Delete car from DB");
        } catch (DataAccessException e) {
            throw new CarNotFoundException("Car not found for ID=" + id);
        }
    }
}

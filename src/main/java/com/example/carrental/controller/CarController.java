package com.example.carrental.controller;

import com.example.carrental.domain.Car;
import com.example.carrental.dto.CarDto;
import com.example.carrental.exception.CarNotFoundException;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/cars")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping("/allcars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<Car> cars = carService.getAllCarsFromDb();
        return ResponseEntity.ok(carMapper.mapToCarDtoList(cars));
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam("class") String classCar) throws Exception {
        List<Car> cars = carService.getAllCars(start, end, classCar);
        return ResponseEntity.ok(carMapper.mapToCarDtoList(cars));
    }

    @PostMapping(value = "/addcar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCar(@RequestBody CarDto carDto) {
        Car car = carMapper.mapToCar(carDto);
        carService.addCar(car);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update/{carId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> updateStatusCar(@PathVariable Long carId, @RequestBody CarDto carDto) throws CarNotFoundException {
        Car car = carMapper.mapToCar(carDto);
        Car updateCar = carService.updateStatusCar(car, carId);
        return ResponseEntity.ok(carMapper.mapToCarDto(updateCar));
    }

    @DeleteMapping(value = "/delete/{carId}")
    public ResponseEntity<Void> deleteCarId(@PathVariable Long carId) throws CarNotFoundException {
        carService.deleteCarFromDB(carId);
        return ResponseEntity.ok().build();
    }
}
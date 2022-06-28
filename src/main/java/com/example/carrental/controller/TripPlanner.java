package com.example.carrental.controller;

import com.example.carrental.domain.WeatherStation;
import com.example.carrental.dto.WeatherStationDto;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.mapper.WeatherMapper;
import com.example.carrental.service.CarService;
import com.example.carrental.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/tripplanner/")
@CrossOrigin("*")
public class TripPlanner { // in progress..........


    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;
    private final CarService carService;
    private final CarMapper carMapper;


    public TripPlanner(WeatherService weatherService, WeatherMapper weatherMapper, CarService carService, CarMapper carMapper) {
        this.weatherService = weatherService;
        this.weatherMapper = weatherMapper;
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping("thebestplacetobe")
    public ResponseEntity<List<WeatherStationDto>> findTheBestCityToTrip() {

        List<WeatherStation> weatherStations = weatherMapper.mapToBoard(weatherService.fetchWeatherBoards()
                .stream()
                .filter(t ->
                        t.getTemperatura().length() >= 2 &&
                                t.getSuma_opadu().equals("0") &&
                                t.getPredkosc_wiatru().length() <= 10)
                .collect(Collectors.toList()));
        return ResponseEntity.ok(weatherMapper.mapToBoardDto(weatherStations));
    }

    @PutMapping(value = "/calculate/{carId}")//in progress.....
    public ResponseEntity<BigDecimal> calculateFuelConsumptionForYourTrip(@PathVariable Long carId, @PathVariable Long equipmentId, @RequestParam("distance") Long distance) {
        return ResponseEntity.ok().build();
    }
}
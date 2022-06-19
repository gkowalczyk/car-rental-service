package com.example.carrental.controller;


import com.example.carrental.domain.WeatherStation;
import com.example.carrental.dto.WeatherStationDto;
import com.example.carrental.mapper.WeatherMapper;
import com.example.carrental.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/tripplanner/")
@CrossOrigin("*")
public class TripPlanner {

    private WeatherStation weatherStation;
    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;


    public TripPlanner(WeatherService weatherService, WeatherMapper weatherMapper) {
        this.weatherService = weatherService;
        this.weatherMapper = weatherMapper;
    }

    @GetMapping()
    public ResponseEntity<List<WeatherStationDto>> checkWeatherCityName() {

        List<WeatherStation> weatherStations = weatherMapper.mapToBoard(weatherService.fetchWeatherBoards()
                .stream()
                .filter(t ->
                        t.getTemperatura().length() >= 2 &&
                                t.getSuma_opadu().equals("0") &&
                                t.getPredkosc_wiatru().length() <= 10)
                .collect(Collectors.toList()));
        return ResponseEntity.ok(weatherMapper.mapToBoardDto(weatherStations));
    }
}
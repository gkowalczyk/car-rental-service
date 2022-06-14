package com.example.carrental.controller;

import com.example.carrental.domain.WeatherStation;
import com.example.carrental.domain.WeatherStationDto;
import com.example.carrental.mapper.WeatherMapper;
import com.example.carrental.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/weather/")
@CrossOrigin("*")
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;

    public WeatherController(WeatherService weatherService, WeatherMapper weatherMapper) {
        this.weatherService = weatherService;
        this.weatherMapper = weatherMapper;
    }

    @GetMapping
    public ResponseEntity<List<WeatherStationDto>> checkWeatherCityName(@RequestParam(value = "city") String checkCityWeather) {
        List<WeatherStation> weatherStations = weatherMapper.mapToBoard(weatherService.fetchWeatherBoards()
                .stream().filter(w -> w.getStacja().equals(checkCityWeather))
                .collect(Collectors.toList()));
        return ResponseEntity.ok(weatherMapper.mapToBoardDto(weatherStations));
    }
}

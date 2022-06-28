package com.example.carrental.service;

import com.example.carrental.client.WeatherClient;
import com.example.carrental.dto.WeatherStationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private  WeatherClient weatherClient;

    public List<WeatherStationDto> fetchWeatherBoards() {
        return weatherClient.getWeather();
    }

}

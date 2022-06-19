package com.example.carrental.service;

import com.example.carrental.client.FuelClient;
import com.example.carrental.client.WeatherClient;
import com.example.carrental.dto.FuelStationDto;
import com.example.carrental.dto.WeatherStationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelService {

    private final FuelClient fuelClient;

    public List<FuelStationDto> fuelStationDtos() {
        return fuelClient.getFuelStation();
    }
}

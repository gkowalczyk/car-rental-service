package com.example.carrental.controller;

import com.example.carrental.domain.FuelStation;
import com.example.carrental.dto.FuelStationDto;
import com.example.carrental.mapper.FuelMapper;
import com.example.carrental.service.FuelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/stations/")
@CrossOrigin("*")
public class FuelStationController {

    private final FuelService fuelService;
    private final FuelMapper fuelMapper;

    public FuelStationController(FuelService fuelService, FuelMapper fuelMapper) {
        this.fuelService = fuelService;
        this.fuelMapper = fuelMapper;
    }


    @GetMapping("searchstation")
    public ResponseEntity<List<FuelStationDto>> findTheNearestStation(@RequestParam(value = "station") String station) {
        List<FuelStation> fuelStationList = fuelMapper.mapToFuel(fuelService.getFuelStationDto())
                .stream()
                .filter(w -> w.getMiejscowosc().equals(station))
                .collect(Collectors.toList());
        return ResponseEntity.ok(fuelMapper.mapToFuelDto(fuelStationList));

    }

    @GetMapping("allstations")
    public ResponseEntity<List<FuelStationDto>> checkAllStations() {
        List<FuelStation> fuelStationList = fuelMapper.mapToFuel(fuelService.getFuelStationDto());
        return ResponseEntity.ok(fuelMapper.mapToFuelDto(fuelStationList));
    }
}


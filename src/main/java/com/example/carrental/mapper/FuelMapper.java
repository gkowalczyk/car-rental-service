package com.example.carrental.mapper;

import com.example.carrental.domain.FuelStation;
import com.example.carrental.dto.FuelStationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelMapper {

    public List<FuelStation> mapToFuel(final List<FuelStationDto> fuelStationDtoList) {
        return fuelStationDtoList.stream()
                .map(b -> new FuelStation(
                        b.getDkn(),
                        b.getNazwa(),
                        b.getMiejscowosc(),
                        b.getNazwaStacji(),
                        b.getWspolrzedne(),
                        b.getBenzynySilnikowe(),
                        b.getOlejeNapedowe(),
                        b.getGazPlynnyLPG(),
                        b.getInfraNrLokalu(),
                        b.getInfraUlica()))
                .collect(Collectors.toList());
    }

    public List<FuelStationDto> mapToFuelDto(final List<FuelStation> fuelStationList) {
        return fuelStationList.stream()
                .map(b -> new FuelStationDto(
                        b.getDkn(),
                        b.getNazwa(),
                        b.getMiejscowosc(),
                        b.getNazwaStacji(),
                        b.getWspolrzedne(),
                        b.getBenzynySilnikowe(),
                        b.getOlejeNapedowe(),
                        b.getGazPlynnyLPG(),
                        b.getInfraNrLokalu(),
                        b.getInfraUlica()))
                .collect(Collectors.toList());
    }
}
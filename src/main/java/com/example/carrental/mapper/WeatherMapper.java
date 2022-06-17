package com.example.carrental.mapper;

import com.example.carrental.domain.WeatherStation;
import com.example.carrental.dto.WeatherStationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherMapper {

    public List<WeatherStation> mapToBoard(final List<WeatherStationDto> weatherStationDtoList) {
        return weatherStationDtoList.stream()
                .map(b -> new WeatherStation(b.getId_stacji(), b.getStacja(), b.getData_pomiaru(), b.getGodzina_pomiaru(), b.getTemperatura(), b.getPredkosc_wiatru(), b.getKierunek_wiatru(), b.getWilgotnosc_wzgledna(), b.getSuma_opadu(), b.getCisnienie()))
                .collect(Collectors.toList());
    }

    public List<WeatherStationDto> mapToBoardDto(final List<WeatherStation> weatherStation) {
        return weatherStation.stream()
                .map(b -> new WeatherStationDto(b.getId_stacji(), b.getStacja(), b.getData_pomiaru(), b.getGodzina_pomiaru(), b.getTemperatura(), b.getPredkosc_wiatru(), b.getKierunek_wiatru(), b.getWilgotnosc_wzgledna(), b.getSuma_opadu(), b.getCisnienie()))
                .collect(Collectors.toList());
    }
}
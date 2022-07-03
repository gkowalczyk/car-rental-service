package com.example.carrental.mapper;

import com.example.carrental.domain.FuelStation;
import com.example.carrental.dto.FuelStationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FuelMapperTestSuite {

    @Autowired
    private FuelMapper fuelMapper;

    @Test
    void mapToFuelStationDtoListTest() {
        //Given
        List<FuelStation> fuelStationList = new LinkedList<>();
        fuelStationList.add(new FuelStation(54334L, "Wroclaw_Orlen", "Wroclaw", "Olen Krzyki", "30",
                true, true, true, "0", "Swobodna"));

        //When
        List<FuelStationDto> fuelStationDtoList = fuelMapper.mapToFuelDto(fuelStationList);

        //Then
        assertEquals(fuelStationDtoList.get(0).getNazwa(), "Wroclaw_Orlen");
    }

    @Test
    void mapToFuelStationListTest() {
        //Given
        List<FuelStationDto> fuelStationListDto = new LinkedList<>();
        fuelStationListDto.add(new FuelStationDto(54334L, "Wroclaw_Orlen", "Wroclaw", "Olen Krzyki", "30",
                true, true, true, "0", "Swobodna"));

        //When
        List<FuelStation> fuelStationList = fuelMapper.mapToFuel(fuelStationListDto);

        //Then
        assertEquals(fuelStationList.get(0).getNazwa(), "Wroclaw_Orlen");
    }
}
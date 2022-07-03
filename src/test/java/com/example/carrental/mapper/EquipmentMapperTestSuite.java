package com.example.carrental.mapper;


import com.example.carrental.domain.Equipment;
import com.example.carrental.dto.EquipmentDto;
import com.example.carrental.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EquipmentMapperTestSuite {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Test
    void mapToEquipmentDtoListTest() {

        //Given
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(new Equipment(1L, "extra gas", new BigDecimal(10)));

        //When
        List<EquipmentDto> equipmentDtoList = equipmentMapper.mapToEquipmentDtoList(equipmentList);

        //Then
        assertEquals(equipmentDtoList.get(0).getDescription(), "extra gas");
    }

    @Test
    void mapToEquipmentTest() {
        EquipmentDto equipmentDto = new EquipmentDto(1L, "extra gas", new BigDecimal(10));

        //When
        Equipment equipment = equipmentMapper.mapToEquipment(equipmentDto);

        //Then
        assertEquals(equipment.getDescription(), "extra gas");
    }

    @Test
    void mapToEquipmentDtoTest() {
        Equipment equipment = new Equipment(1L, "extra gas", new BigDecimal(10));

        //When
        EquipmentDto equipmentDto = equipmentMapper.mapToEquipmentDto(equipment);

        //Then
        assertEquals(equipmentDto.getDescription(), "extra gas");
    }
}
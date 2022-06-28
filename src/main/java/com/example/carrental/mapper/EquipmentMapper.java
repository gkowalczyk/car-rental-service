package com.example.carrental.mapper;


import com.example.carrental.domain.Equipment;
import com.example.carrental.dto.EquipmentDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentMapper {

    public Equipment mapToEquipment(EquipmentDto equipmentDto) {
        return new Equipment(
                equipmentDto.getId(),
                equipmentDto.getDescription(),
                equipmentDto.getPrice());
    }

    public EquipmentDto mapToEquipmentDto(Equipment equipment) {
        return new EquipmentDto(
                equipment.getId(),
                equipment.getDescription(),
                equipment.getPrice());
    }

    public List<EquipmentDto> mapToEquipmentDtoList(List<Equipment> equipmentList) {
        return equipmentList.stream()
                .map(e -> mapToEquipmentDto(e))
                .collect(Collectors.toList());
    }
}
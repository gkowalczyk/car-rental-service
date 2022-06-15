package com.example.carrental.controller;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.Equipment;
import com.example.carrental.domain.Rent;
import com.example.carrental.dto.CarDto;
import com.example.carrental.dto.EquipmentDto;
import com.example.carrental.dto.RentDto;
import com.example.carrental.exception.EquipmentNotFoundException;
import com.example.carrental.exception.RentNotFoundException;
import com.example.carrental.mapper.EquipmentMapper;
import com.example.carrental.service.EquipmentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accessories")
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;

    public EquipmentController(EquipmentService equipmentService, EquipmentMapper equipmentMapper) {
        this.equipmentService = equipmentService;
        this.equipmentMapper = equipmentMapper;
    }

    @GetMapping("allaccessories")
    public ResponseEntity<List<EquipmentDto>> getAllAccessories() {
        List<Equipment> equipmentList = equipmentService.getAllAccessories();
        return ResponseEntity.ok(equipmentMapper.mapToEquipmentDtoList(equipmentList));
    }

    @DeleteMapping(value = "{equipmentId}")
    public ResponseEntity<Void> deleteAccessoriesById(@PathVariable Long equipmentId) throws EquipmentNotFoundException {
        equipmentService.deleteEquipmentFromDB(equipmentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update/{equipmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentDto> updateEquipmentId(@PathVariable Long equipmentId, @RequestBody EquipmentDto equipmentDto) throws EquipmentNotFoundException {
        Equipment equipment = equipmentMapper.mapToEquipment(equipmentDto);
        equipment = equipmentService.updateEquipment(equipment, equipmentId);
        return ResponseEntity.ok(equipmentMapper.mapToEquipmentDto(equipment));
    }

    @PostMapping(value = "/addequipment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentDto> addEquipment(@RequestBody EquipmentDto equipmentDto) {
        Equipment equipment = equipmentMapper.mapToEquipment(equipmentDto);
        equipmentService.addEquipment(equipment);
        return ResponseEntity.ok().build();
    }
}
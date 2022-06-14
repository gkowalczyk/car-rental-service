package com.example.carrental.controller;

import com.example.carrental.domain.Rent;
import com.example.carrental.dto.RentDto;
import com.example.carrental.exception.CarNotFoundException;
import com.example.carrental.exception.EquipmentNotFoundException;
import com.example.carrental.exception.RentNotFoundException;
import com.example.carrental.exception.UserNotFoundException;
import com.example.carrental.mapper.RentMapper;
import com.example.carrental.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/rents")
public class RentController {


    private final RentService rentService;
    private final RentMapper rentMapper;

    @GetMapping
    public ResponseEntity<List<RentDto>> getAllRents() {
        List<Rent> rentList = rentService.getAllRentsFromDb();
        return ResponseEntity.ok(rentMapper.mapToRentDtoList(rentList));
    }

    @GetMapping(value = "{rentId}")
    public ResponseEntity<RentDto> getRent(@PathVariable Long rentId) throws Exception {
        Rent rent = rentService.getRentById(rentId);
        return ResponseEntity.ok(rentMapper.mapToRentDto(rent));
    }

    @PostMapping(value = "/newcardrent/{userId}/{carId}")
    public ResponseEntity<RentDto> createRentCardForUserId(@PathVariable Long userId, @PathVariable Long carId) throws CarNotFoundException, UserNotFoundException {
        return ResponseEntity.ok(rentMapper.mapToRentDto(rentService.createNewCardRent(userId, carId)));
    }

    @DeleteMapping(value = "/cancelrent/{cardId}")
    public ResponseEntity<Void> cancelRentCard(@PathVariable Long cardId) throws RentNotFoundException {
        rentService.cancelCardRent(cardId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update/{rentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentDto> updateRentId(@PathVariable Long rentId, @RequestBody RentDto rentDto) throws RentNotFoundException {
        Rent rent = rentMapper.mapToRent(rentDto);
        rent = rentService.updateRent(rent, rentId);
        return ResponseEntity.ok(rentMapper.mapToRentDto(rent));
    }

    @PutMapping(value = "/calculate/{rentId}/{equipmentId}")
    public ResponseEntity<BigDecimal> calculatePriceForRentId(@PathVariable Long rentId, @PathVariable Long equipmentId, @RequestParam("category") String category) throws RentNotFoundException, EquipmentNotFoundException {
        return ResponseEntity.ok(rentService.calculateRentCostFactory(rentId, equipmentId, category));

    }
}
package com.example.carrental.dto;

import com.example.carrental.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EquipmentDto {

    private Long id;

    private String description;

    private BigDecimal price;

    private List<Car> carList;

    public EquipmentDto(Long id, String description, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }
}
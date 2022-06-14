package com.example.carrental.dto;

import com.example.carrental.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor

public class EquipmentDto {

    private Long id;

    private String description;

    private BigDecimal price;

    private List<Car> carList;
}

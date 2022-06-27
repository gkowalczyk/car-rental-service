package com.example.carrental.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class CarDto {

    private Long id;

    private String registration;

    private String model;

    private String company;

    private String category;

    private String gearBox;

    private BigDecimal dailyCost;

    private boolean isAvailable;

    private List<EquipmentDto> equipmentList;

    private List<RentDto> rentList;

    public CarDto(Long id, String registration, String model, String company, String category, String gearBox, BigDecimal dailyCost, boolean isAvailable) {
        this.id = id;
        this.registration = registration;
        this.model = model;
        this.company = company;
        this.category = category;
        this.gearBox = gearBox;
        this.dailyCost = dailyCost;
        this.isAvailable = isAvailable;
    }
}



package com.example.carrental.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Long id;

    private String registration;

    private String model;

    private String company;

    private String category;

    private String gearBox;

    private BigDecimal dailyCost;

    private boolean isAvailable;

    private List<EquipmentDto> equipmentList = new ArrayList<>();

    private List<RentDto> rentList = new ArrayList<>();

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



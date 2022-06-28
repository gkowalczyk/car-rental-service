package com.example.carrental.dto;

import com.example.carrental.domain.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor

public class RentDto {

    private Long id;

    private UserDto userDto;

    private CarDto carDto;

    private LocalDate startRent;

    private LocalDate endRent;

    private BigDecimal totalCost;

    private boolean isPaid;

    private RentStatus rentStatus;
}

package com.example.carrental.mapper;

import com.example.carrental.domain.*;
import com.example.carrental.dto.CarDto;
import com.example.carrental.dto.RentDto;
import com.example.carrental.dto.UserDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentMapperTestSuite {

    @Autowired
    private RentMapper rentMapper;

    @Test
    void mapToRentDtoListTest() {

        //Given
        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        Car car = new Car(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        List<Rent> rentList = new ArrayList<>();
        rentList.add(new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING));

        //When
        List<RentDto> rentDtoList = rentMapper.mapToRentDtoList(rentList);

        //Then
        assertEquals(rentDtoList.get(0).getUserDto().getSurname(), "Jan");
    }

    @Test
    void mapToRentTest() {

        //Given
        CarDto carDto = new CarDto(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);
        RentDto rentDto = new RentDto(1L, userDto, carDto, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200), true, RentStatus.REFRESHING);

        //When
        Rent rent = rentMapper.mapToRent(rentDto);
        BigDecimal totalCost = rent.getTotalCost();
        BigDecimal totalCostDto = rentDto.getTotalCost();

        //Then
        assertThat(totalCost, Matchers.comparesEqualTo(totalCostDto));
    }

    @Test
    void mapToRentDtoTest() {

        //Given
        Car car = new Car(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);
        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);
        Rent rent = new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200), true, RentStatus.REFRESHING);

        //When
        RentDto rentDto = rentMapper.mapToRentDto(rent);

        //Then
        assertEquals(rentDto.getUserDto().getId(), 1);
    }
}
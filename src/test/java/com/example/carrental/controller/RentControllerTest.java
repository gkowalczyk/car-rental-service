package com.example.carrental.controller;

import com.example.carrental.domain.*;
import com.example.carrental.dto.CarDto;
import com.example.carrental.dto.RentDto;
import com.example.carrental.dto.UserDto;
import com.example.carrental.exception.CarNotFoundException;
import com.example.carrental.exception.EquipmentNotFoundException;
import com.example.carrental.exception.RentNotFoundException;
import com.example.carrental.exception.UserNotFoundException;
import com.example.carrental.mapper.RentMapper;
import com.example.carrental.service.RentService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(RentController.class)
public class RentControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RentService rentService;
    @MockBean
    private RentMapper rentMapper;

    @Test
    void shouldFetchRents() throws Exception {

        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        CarDto carDto = new CarDto(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        Car car = new Car(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        List<RentDto> rentDtoList = List.of(new RentDto(1L, userDto, carDto, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING));

        List<Rent> rentList = List.of(new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING));

        when(rentService.getAllRentsFromDb()).thenReturn(rentList);
        when(rentMapper.mapToRentDtoList((rentList))).thenReturn(rentDtoList);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].totalCost", Matchers.is(200)))
                .andExpect(jsonPath("$[0].userDto.login", Matchers.is("jkowalski")));
    }

    @Test
    void shouldFetchRentId() throws Exception {

        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        CarDto carDto = new CarDto(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        Car car = new Car(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        RentDto rentDto = new RentDto(1L, userDto, carDto, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING);

        Rent rent = new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING);

        when(rentService.getRentById(1L)).thenReturn(rent);
        when(rentMapper.mapToRentDto(rent)).thenReturn(rentDto);

        mockMvc
                .perform(get("/v1/rents/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))

                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startRent", Matchers.is("2022-06-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userDto.login", Matchers.is("jkowalski")));
    }

    @Test
    void shouldCreateCardForUserIdWithCarId() throws Exception {


        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        CarDto carDto = new CarDto(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        Car car = new Car(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        RentDto rentDto = new RentDto(1L, userDto, carDto, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING);

        Rent rent = new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING);

        when(rentMapper.mapToRentDto(rent)).thenReturn(rentDto);
        when(rentService.createNewCardRent(user.getId(), car.getId())).thenReturn(rent);

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/rents/newcardrent/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentStatus", Matchers.is("REFRESHING")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userDto.login", Matchers.is("jkowalski")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isPaid", Matchers.is(new Boolean(true))));
    }

    @Test
    void shouldDeleteRent() throws Exception {

        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        Car car = new Car(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        Rent rent = new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING);

        doNothing().when(rentService).cancelCardRent(rent.getCar().getId());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/rents/cancelrent/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateRentTestSuite() throws Exception {

        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        Car car = new Car(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        CarDto carDto = new CarDto(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        Rent rent = new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING);


        Rent rentUpdate = new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.IN_PROGRESS);

        RentDto rentUpdateDto = new RentDto(1L, userDto, carDto, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.RENTING);

        when(rentService.updateRent(rent, rent.getId())).thenReturn(rentUpdate);
        when(rentMapper.mapToRentDto(rent)).thenReturn(rentUpdateDto);

        Gson gson = new Gson();
        String content = gson.toJson(rentUpdateDto);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/rents/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentStatus", Matchers.is("IN_PROGRESS")));
    }

    @Test
    void shouldCalculateCostRentTestSuite() throws Exception {

        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        Car car = new Car(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        CarDto carDto = new CarDto(1L, "DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true);

        Rent rent = new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING);

        RentDto rentDto = new RentDto(1L, userDto, carDto, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.REFRESHING);

        Rent rentUpdate = new Rent(1L, user, car, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.IN_PROGRESS);

        RentDto rentUpdateDto = new RentDto(1L, userDto, carDto, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30), new BigDecimal(200.0), true, RentStatus.RENTING);

        Equipment equipment = new Equipment(1L,"",new BigDecimal(100));

        when(rentService.calculateRentCostFactory(rent.getId(),equipment.getId(), "VIP"));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/rents/calculate/1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        //.param("VIP"))
                      .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentDto.totalCost", Matchers.is(260.0)));



    }
}
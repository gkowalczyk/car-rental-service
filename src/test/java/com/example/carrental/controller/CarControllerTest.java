package com.example.carrental.controller;

import com.example.carrental.domain.Car;
import com.example.carrental.dto.CarDto;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.service.CarService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

@SpringJUnitWebConfig
@WebMvcTest(CarControllerTest.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarService carService;
    @MockBean
    private CarMapper carMapper;

    @Test
    void shouldFetchCarDtoList() throws Exception {
        List<CarDto> carDtoList = List.of(new CarDto(1L,"DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true));
        List<Car> carList = List.of(new Car(1L,"DW123476", "XC60", "Volvo", "premium", "automatic", new BigDecimal(50), true));
        when(carService.getAllCarsFromDb()).thenReturn(carList);
when(carMapper.mapToCarDtoList(anyList())).thenReturn(carDtoList);
mockMvc
        .perform(MockMvcRequestBuilders.get("/v1/cars/allcars")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$[0].id", Matchers.is(1)))
        .andExpect(jsonPath("$[0].registration", Matchers.is("DW123476")))
        .andExpect(jsonPath("$[0].model", Matchers.is("XC60")))
        .andExpect(jsonPath("$[0].company", Matchers.is("Volvo")))
        .andExpect(jsonPath("$[0].category", Matchers.is("premium")))
        .andExpect(jsonPath("$[0].gearBox", Matchers.is("automatic")))
        .andExpect(jsonPath("$[0].dailyCost", Matchers.is(50)))
        .andExpect(jsonPath("$[0].isAvailable", Matchers.is(true)));
    }
}

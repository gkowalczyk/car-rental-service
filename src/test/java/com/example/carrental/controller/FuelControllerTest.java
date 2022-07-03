package com.example.carrental.controller;

import com.example.carrental.domain.Equipment;
import com.example.carrental.domain.FuelStation;
import com.example.carrental.domain.WeatherStation;
import com.example.carrental.dto.EquipmentDto;
import com.example.carrental.dto.FuelStationDto;
import com.example.carrental.dto.WeatherStationDto;
import com.example.carrental.mapper.EquipmentMapper;
import com.example.carrental.mapper.FuelMapper;
import com.example.carrental.service.EquipmentService;
import com.example.carrental.service.FuelService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringJUnitWebConfig
@WebMvcTest(FuelStationController.class)

public class FuelControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FuelService fuelService;
    @MockBean
    private FuelMapper fuelMapper;


    @Test
    void shouldFetchWeather() throws Exception {

        List<FuelStationDto> fuelStationDtoList = List.of(new FuelStationDto(54334L, "Wroclaw_Orlen", "Wroclaw", "Olen Krzyki", "30",
                true, true, true, "0", "Swobodna"));
        List<FuelStation> fuelStationList = List.of(new FuelStation(54334L, "Wroclaw_Orlen", "Wroclaw", "Olen Krzyki", "30",
                true, true, true, "0", "Swobodna"));
        when(fuelService.getFuelStationDto()).thenReturn(fuelStationDtoList);
        when(fuelMapper.mapToFuelDto(fuelStationList)).thenReturn(fuelStationDtoList);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/stations/allstations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                // .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].dkn", Matchers.is(54334)))
                .andExpect(jsonPath("$[0].nazwa", Matchers.is("Wroclaw_Orlen")))
                .andExpect(jsonPath("$[0].miejscowosc", Matchers.is("Wroclaw")))
                .andExpect(jsonPath("$[0].nazwaStacji", Matchers.is("Olen Krzyki")))
                .andExpect(jsonPath("$[0].wspolrzedne", Matchers.is("30")))
                .andExpect(jsonPath("$[0].benzynySilnikowe", Matchers.is(true)))
                .andExpect(jsonPath("$[0].olejeNapedowe", Matchers.is(true)))
                .andExpect(jsonPath("$[0].gazPlynnyLPG", Matchers.is(true)))
                .andExpect(jsonPath("$[0].infraNrLokalu", Matchers.is("0")))
                .andExpect(jsonPath("$[0].infraUlica", Matchers.is("Swobodna")));
    }
}

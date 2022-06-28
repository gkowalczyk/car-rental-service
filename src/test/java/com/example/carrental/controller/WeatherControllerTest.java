package com.example.carrental.controller;

import com.example.carrental.domain.WeatherStation;
import com.example.carrental.dto.WeatherStationDto;
import com.example.carrental.mapper.WeatherMapper;
import com.example.carrental.service.WeatherService;
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

import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(WeatherControllerTest.class)
public class WeatherControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private WeatherMapper weatherMapper;
    @MockBean
    private WeatherService weatherService;


    @Test
    void shouldFetchWeatherBoards() throws Exception {

        List<WeatherStationDto> weatherStationsDto = List.of(new WeatherStationDto("1", "Wroclaw", "01.06.2022", "12:00", "30",
                "10", "100", "5", "0", "1012"));
        List<WeatherStation> weatherStations = List.of(new WeatherStation("1", "Wroclaw", "01.06.2022", "12:00", "30",
                "10", "100", "5", "0", "1012"));

        when(weatherService.fetchWeatherBoards()).thenReturn(weatherStationsDto);
        //when(weatherMapper.mapToBoardDto(weatherStations)).thenReturn(weatherStationsDto);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weather/allcity")
                        .contentType(MediaType.APPLICATION_JSON))
                      //  .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));

    }
}

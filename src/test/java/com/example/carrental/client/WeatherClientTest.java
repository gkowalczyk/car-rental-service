package com.example.carrental.client;

import com.example.carrental.config.WeatherApiConfig;
import com.example.carrental.dto.WeatherStationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherClientTest {

    @InjectMocks
    private WeatherClient weatherClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherApiConfig weatherApiConfig;

    @Test
    public void shouldFetchWeather() throws URISyntaxException {

        when(weatherApiConfig.getWeatherApiEndpoint()).thenReturn("http://test.com");
        WeatherStationDto[] weatherStationDto = new WeatherStationDto[1];
        weatherStationDto[0] = new WeatherStationDto("1", "Wro", "01.06.2022", "00:00", "30", "10",
                "9", "50", "0", "1013");
        URI uri = new URI("http://test.com/data/synop");
        when(restTemplate.getForObject(uri, WeatherStationDto[].class)).thenReturn(weatherStationDto);

        //when
        List<WeatherStationDto> weatherStationDtoList = weatherClient.getWeather();
        //then
        assertEquals(1, weatherStationDtoList.size());
        assertEquals("1", weatherStationDtoList.get(0).getId_stacji());
    }
}

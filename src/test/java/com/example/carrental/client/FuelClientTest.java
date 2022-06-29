package com.example.carrental.client;

import com.example.carrental.config.FuelApiConfig;
import com.example.carrental.dto.FuelStationDto;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FuelClientTest {

    @InjectMocks
    private FuelClient fuelClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private FuelApiConfig fuelApiConfig;

    @Test
    public void shouldFetchFuelStation() throws URISyntaxException {

        when(fuelApiConfig.getFuelStationApiEndpoint()).thenReturn("http://test.com");
        FuelStationDto[] fuelStationDtos = new FuelStationDto[1];
        fuelStationDtos[0] = new FuelStationDto(1L, "Orlen-Wro123", "Wroclaw", "Orlen", "50',10'", true, true, true, "12", "Swobodna 12");
        URI uri = new URI("http://test.com/InfrastructureFuelStation");
        when(restTemplate.getForObject(uri, FuelStationDto[].class)).thenReturn(fuelStationDtos);

        //when
        List<FuelStationDto> fuelStationDtoList = fuelClient.getFuelStation();
        //then
        assertEquals(1, fuelStationDtoList.size());
        assertEquals("Wroclaw", fuelStationDtoList.get(0).getMiejscowosc());
        assertTrue(fuelStationDtoList.get(0).getGazPlynnyLPG());
        assertEquals(1, fuelStationDtoList.get(0).getDkn());
    }
}



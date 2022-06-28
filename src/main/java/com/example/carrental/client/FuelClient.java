package com.example.carrental.client;

import com.example.carrental.config.FuelApiConfig;
import com.example.carrental.dto.FuelStationDto;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Component
@RequiredArgsConstructor
@EqualsAndHashCode
public class FuelClient {

    private final RestTemplate restTemplate;
    private final FuelApiConfig fuelApiConfig;

    public List<FuelStationDto> getFuelStation() {

        URI url = UriComponentsBuilder.fromHttpUrl(fuelApiConfig.getFuelStationApiEndpoint() + "InfrastructureFuelStation")
                .build()
                .encode()
                .toUri();

        try {
            FuelStationDto[] fuelStationDtos = restTemplate.getForObject(url, FuelStationDto[].class);
            return ofNullable(fuelStationDtos)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();

        }
    }
}

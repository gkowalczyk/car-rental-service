package com.example.carrental.client;

import com.example.carrental.config.WeatherApiConfig;
import com.example.carrental.dto.WeatherStationDto;
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
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;

    public List<WeatherStationDto> getWeather() {

        URI url = UriComponentsBuilder.fromHttpUrl(weatherApiConfig.getWeatherApiEndpoint() + "/data/synop")
                .build()
                .encode()
                .toUri();

        try {
            WeatherStationDto[] weatherStationDto = restTemplate.getForObject(url, WeatherStationDto[].class);
            return ofNullable(weatherStationDto)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();

        }
    }
}

package com.example.carrental.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FuelApiConfig {

    @Value("https://api.ure.gov.pl/api/")
    private String fuelStationApiEndpoint;
}

package com.example.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherStation {
    private String id_stacji;
    private String stacja;
    private String data_pomiaru;
    private String godzina_pomiaru;
    private String temperatura;
    private String predkosc_wiatru;
    private String kierunek_wiatru;
    private String wilgotnosc_wzgledna;
    private String suma_opadu;
    private String cisnienie;
}

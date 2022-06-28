package com.example.carrental.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherStationDto {
    @JsonProperty("id_stacji")
    private String id_stacji;
    @JsonProperty("stacja")
    private String stacja;
    @JsonProperty("data_pomiaru")
    private String data_pomiaru;
    @JsonProperty("godzina_pomiaru")
    private String godzina_pomiaru;
    @JsonProperty("emperatura")
    private String temperatura;
    @JsonProperty("predkosc_wiatru")
    private String predkosc_wiatru;
    @JsonProperty("kierunek_wiatru")
    private String kierunek_wiatru;
    @JsonProperty("wilgotnosc_wzgledna")
    private String wilgotnosc_wzgledna;
    @JsonProperty("suma_opadu")
    private String suma_opadu;
    @JsonProperty("cisnienie")
    private String cisnienie;
}

package com.example.carrental.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuelStationDto {
    @JsonProperty("dkn")
    private Long dkn;
    @JsonProperty("nazwa")
    private String nazwa;
    @JsonProperty("miejscowosc")
    private String miejscowosc;
    @JsonProperty("nazwaStacji")
    private String nazwaStacji;
    @JsonProperty("wspolrzedne")
    private String wspolrzedne;
    @JsonProperty("benzynySilnikowe")
    private Boolean benzynySilnikowe;
    @JsonProperty("olejeNapedowe")
    private Boolean olejeNapedowe;
    @JsonProperty("gazPlynnyLPG")
    private Boolean gazPlynnyLPG;
    @JsonProperty("infraNrLokalu")
    private String infraNrLokalu;
    @JsonProperty("infraUlica")
    private String infraUlica;
}

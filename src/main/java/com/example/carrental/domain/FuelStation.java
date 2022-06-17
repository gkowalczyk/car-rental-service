package com.example.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelStation {
    private Long dkn;
    private String nazwa;
    private String miejscowosc;
    private String nazwaStacji;
    private String wspolrzedne;
    private Boolean benzynySilnikowe;
    private Boolean olejeNapedowe;
    private Boolean gazPlynnyLPG;
    private String infraNrLokalu;
    private String infraUlica;
}

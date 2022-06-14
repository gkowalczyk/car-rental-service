package com.example.carrental.service;

import java.math.BigDecimal;

public class LowComfortCar implements CarOrder  {
    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(100);
    }
}

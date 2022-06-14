package com.example.carrental.service;

import java.math.BigDecimal;

public class MiddleComfortCar extends AbstractCarOrderDecorator{
    protected MiddleComfortCar(CarOrder carOrder) {
        super(carOrder);
    }
    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal(30));
    }
}

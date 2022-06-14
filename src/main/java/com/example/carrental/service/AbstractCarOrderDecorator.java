package com.example.carrental.service;

import java.math.BigDecimal;

public abstract class AbstractCarOrderDecorator implements CarOrder {
    private final CarOrder carOrder;

    protected AbstractCarOrderDecorator(CarOrder carOrder) {
        this.carOrder = carOrder;
    }

    @Override
    public BigDecimal getPrice() {
        return carOrder.getPrice();
    }
}

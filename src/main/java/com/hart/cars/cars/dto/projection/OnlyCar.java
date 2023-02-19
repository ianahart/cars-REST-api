package com.hart.cars.cars.dto.projection;

public interface OnlyCar {
    Long getId();

    String getMake();

    String getModel();

    Integer getDoor();

    Double getPrice();
}

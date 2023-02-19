package com.hart.cars.cars.dto;

import com.hart.cars.driver.Driver;

public class OnlyCarDto {
    private Long id;
    private String make;
    private String model;
    private Integer door;
    private Double price;
    private Driver driver;

    public OnlyCarDto() {

    }

    public OnlyCarDto(Long id, String make, String model, Integer door, Double price, Driver driver) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.door = door;
        this.price = price;
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public Integer getDoor() {
        return door;
    }

    public String getModel() {
        return model;
    }

    public Double getPrice() {
        return price;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setDoor(Integer door) {
        this.door = door;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}

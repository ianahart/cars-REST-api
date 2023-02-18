package com.hart.cars.cars.dto;

public class UpdateCarDto {
    private String make;
    private String model;
    private Integer door;
    private Double price;

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

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDoor(Integer door) {
        this.door = door;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

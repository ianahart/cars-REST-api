package com.hart.cars.driver.dto;

import java.util.List;

import com.hart.cars.cars.Car;
import com.hart.cars.driver.Driver;

public class GetDriverDto {
    private Driver driver;
    private List<Car> driverCars;

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setDriverCars(List<Car> driverCars) {
        this.driverCars = driverCars;
    }

    public Driver getDriver() {
        return driver;
    }

    public List<Car> getDriverCars() {
        return driverCars;
    }
}

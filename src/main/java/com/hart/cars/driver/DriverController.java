package com.hart.cars.driver;

import java.util.List;
import java.util.Optional;

import com.hart.cars.cars.Car;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping(path = "/api/v1/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/")
    public void createDriver(@RequestBody Driver driver) {
        this.driverService.createDriver(driver);
    }

    @GetMapping("/")
    public List<Driver> getAllDrivers() {
        return this.driverService.getAllDrivers();
    }

    @GetMapping("/{userId}/")
    public List<Object> getDriver(@PathVariable("userId") Long userId) {
        Driver driver = this.driverService.getDriver(userId);
        Hibernate.initialize(driver.getCars());
        return List.of(driver, driver.getCars());

    }
}

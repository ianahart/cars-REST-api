package com.hart.cars.driver;

import java.util.HashMap;
import java.util.List;

import com.hart.cars.cars.Car;
import com.hart.cars.driver.dto.GetDriverDto;
import com.hart.cars.driver.dto.UpdateDriverDto;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @Autowired
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

    @GetMapping("/{driverId}/")
    public HashMap<String, Object> getDriver(@PathVariable("driverId") Long driverId) {
        Driver driver = this.driverService.getDriver(driverId);
        Hibernate.initialize(driver.getCars());

        HashMap<String, Object> hm = new HashMap<String, Object>();
        List<Object> list = List.of(driver, driver.getCars());

        hm.put("driver", list.get(0));
        hm.put("driver_cars", list.get(1));
        return hm;

    }

    @PatchMapping("/{driverId}/")
    public Driver updateDriver(@RequestBody UpdateDriverDto updateDriverDto, @PathVariable("driverId") Long driverId) {
        Driver driver = this.driverService.updateDriver(driverId, updateDriverDto);
        return driver;
    }

    @DeleteMapping("/{driverId}/")
    public void deleteDriver(@PathVariable("driverId") Long driverId) {
        this.driverService.deleteDriver(driverId);
    }
}

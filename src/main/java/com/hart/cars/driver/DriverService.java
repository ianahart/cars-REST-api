package com.hart.cars.driver;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.hart.cars.advice.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public HashMap<String, String> capitalizeName(Driver driver) {
        String lastName = driver.getLastName().substring(0, 1).toUpperCase() +
                driver.getLastName().substring(1).toLowerCase();
        String firstName = driver.getFirstName().substring(0, 1).toUpperCase() +
                driver.getFirstName().substring(1).toLowerCase();

        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("firstName", firstName);
        hm.put("lastName", lastName);

        return hm;

    }

    public void createDriver(Driver driver) {

        Optional<Driver> exists = this.driverRepository.findByEmail(driver.getEmail());

        if (exists.isPresent()) {
            throw new BadRequestException("A driver with that email already exists.");
        }

        HashMap<String, String> hm = capitalizeName(driver);
        driver.setFirstName(hm.get("firstName"));
        driver.setLastName(hm.get("lastName"));

        this.driverRepository.save(driver);
    }
}

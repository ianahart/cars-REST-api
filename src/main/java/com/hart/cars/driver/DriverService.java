package com.hart.cars.driver;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.hart.cars.advice.BadRequestException;
import com.hart.cars.advice.ForbiddenException;
import com.hart.cars.advice.NotFoundException;
import com.hart.cars.driver.dto.UpdateDriverDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver getDriver(Long driverId) {
        Boolean exists = this.driverRepository.existsById(driverId);

        if (!exists) {
            throw new NotFoundException("The driver with the id " + driverId + " was not found.");
        }

        Driver driver = this.driverRepository.findById(driverId).orElseThrow();

        return driver;

    }

    @Transactional
    public List<Driver> getAllDrivers() {
        return this.driverRepository.getAll();
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

    public Driver updateDriver(Long driverId, UpdateDriverDto updateDriverDto) {
        Boolean exists = this.driverRepository.existsById(driverId);

        if (!exists) {
            throw new NotFoundException("A driver with the id: " + driverId + " does not exist.");
        }

        if (updateDriverDto.getId() != driverId) {
            throw new ForbiddenException("Forbidden. Cannot update. Id's do not match.");
        }

        Driver driver = this.driverRepository.findById(driverId).orElseThrow();

        if (updateDriverDto.getFirstName() != null) {
            driver.setFirstName(updateDriverDto.getFirstName());
        }

        if (updateDriverDto.getLastName() != null) {
            driver.setLastName(updateDriverDto.getLastName());
        }
        return driver;
    }

    public void deleteDriver(Long driverId) {
        boolean exists = this.driverRepository.existsById(driverId);

        if (!exists) {
            throw new NotFoundException("A driver with the id: " + driverId + " does not exist.");
        }

        this.driverRepository.deleteById(driverId);
    }
}

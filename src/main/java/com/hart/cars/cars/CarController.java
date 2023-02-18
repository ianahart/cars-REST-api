package com.hart.cars.cars;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/cars")
public class CarController {
    @Autowired
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public List<Car> getCars() {
        return this.carService.getCars();
    }

    @PostMapping("/")
    public void createCar(@RequestBody Car car) {
        this.carService.createCar(car);
    }

    @GetMapping("/{carId}/")
    public Car getCar(@PathVariable("carId") Long carId) {
        return this.carService.getCar(carId);
    }
}

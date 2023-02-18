package com.hart.cars.cars;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.hart.cars.cars.dto.UpdateCarDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<? extends Car> getCars(@RequestParam Optional<Double> min,
            @RequestParam Optional<Double> max) {

        return this.carService.getCars(min, max);
    }

    @PostMapping("/")
    public void createCar(@RequestBody Car car) {
        this.carService.createCar(car);
    }

    @GetMapping("/{carId}/")
    public HashMap<String, Object> getCar(@PathVariable("carId") Long carId) {
        Car car = this.carService.getCar(carId);
        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("car", car);
        hm.put("driver", car.getDriver());
        return hm;
    }

    @PatchMapping("/{carId}/")
    public Car updateCar(
            @PathVariable("carId") Long carId,
            @RequestBody UpdateCarDto updateCarDto) {
        return this.carService.updateCar(carId, updateCarDto);
    }

    @DeleteMapping("/{carId}/")
    public void deleteCar(@PathVariable Long carId) {
        this.carService.deleteCar(carId);
    }
}

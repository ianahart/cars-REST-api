package com.hart.cars.cars;

import java.util.List;

import com.hart.cars.advice.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars() {
        return this.carRepository.findAll();
    }

    public Car getCar(Long carId) {
        Boolean exists = this.carRepository.existsById(carId);

        if (!exists) {
            throw new NotFoundException("A car with the id: " + carId + " does not exist.");
        }
        return this.carRepository.findById(carId).orElseThrow();
    }

    public void createCar(Car car) {

        System.out.println(car);
        this.carRepository.save(car);

    }
}

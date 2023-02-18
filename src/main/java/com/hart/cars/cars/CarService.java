package com.hart.cars.cars;

import java.util.List;
import java.util.Optional;

import com.hart.cars.advice.BadRequestException;
import com.hart.cars.advice.NotFoundException;
import com.hart.cars.cars.dto.UpdateCarDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars(Optional<Double> min, Optional<Double> max) {
        if (min.isPresent() && max.isPresent()) {
            return this.carRepository.getCarsBetweenPrice(min, max);
        }
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
        List<?> exists = this.carRepository.CheckForExistingCar(
                car.getDriver().getId(),
                car.getMake(),
                car.getModel());
        if (exists.size() >= 1) {
            throw new BadRequestException("You already have this car.");
        }
        this.carRepository.save(car);

    }

    public Car updateCar(Long carId, UpdateCarDto updateCarDto) {
        boolean exists = this.carRepository.existsById(carId);
        boolean needsToUpdate = false;
        if (!exists) {
            throw new NotFoundException("A car with the id: " + carId + " does not exist.");
        }

        Car car = this.carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("A car with the id: " + carId + " does not exist."));

        if (updateCarDto.getMake() != null) {
            car.setMake(updateCarDto.getMake());
            needsToUpdate = true;
        }

        if (updateCarDto.getModel() != null) {
            car.setModel(updateCarDto.getModel());
            needsToUpdate = true;
        }

        if (updateCarDto.getDoor() <= 4) {
            car.setDoor(updateCarDto.getDoor());
            needsToUpdate = true;
        } else {
            throw new BadRequestException("There is a maxium of 4 doors for a car.");
        }

        if (updateCarDto.getPrice() != null) {
            car.setPrice(updateCarDto.getPrice());
        }

        if (needsToUpdate) {
            this.carRepository.save(car);
        }

        return car;
    }

    public void deleteCar(Long carId) {
        boolean exists = this.carRepository.existsById(carId);
        if (!exists) {
            throw new NotFoundException("A car with the id: " + carId + " does not exist");
        }

        this.carRepository.deleteById(carId);
    }
}

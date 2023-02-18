package com.hart.cars.cars;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = """
            SELECT * FROM car c
            WHERE c.price
            BETWEEN :min
            AND :max
             """, nativeQuery = true)
    List<Car> getCarsBetweenPrice(
            @Param("min") Optional<Double> min,
            @Param("max") Optional<Double> max);

    @Query(value = """
                    SELECT *
                    FROM car c
                    WHERE c.driver_id =:driver_id
                    AND c.make =:make
                    AND c.model =:model
                    LIMIT 1
            """, nativeQuery = true)
    List<?> CheckForExistingCar(
            @Param("driver_id") Long driver_id,
            @Param("make") String make,
            @Param("model") String model);

}

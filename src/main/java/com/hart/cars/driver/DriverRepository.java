package com.hart.cars.driver;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);

    @Query(value = "SELECT * FROM driver", nativeQuery = true)
    List<Driver> getAll();

    @Query(value = "SELECT * FROM driver d INNER JOIN car c on d.id = c.id WHERE c.driver_id =:driver_id ", nativeQuery = true)
    List<Object> getDriverWithCars(@Param("driver_id") Long driver_id);
}

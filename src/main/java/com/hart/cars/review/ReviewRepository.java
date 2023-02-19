package com.hart.cars.review;

import java.util.List;
import java.util.Optional;

import com.hart.cars.cars.Car;
import com.hart.cars.cars.dto.projection.OnlyCar;
import com.hart.cars.review.dto.projection.OnlyReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = """
             SELECT * FROM review
            """, nativeQuery = true)
    List<OnlyReview> getAllReviews();

    @Query(value = """
               SELECT * FROM review r
               WHERE r.rating = :rating
            """, nativeQuery = true)
    List<OnlyReview> getAllReviewsByRating(@Param("rating") Optional<Integer> rating);

    @Query(value = """
            SELECT COUNT("id")
            FROM review r
            WHERE r.car_id =:carId
            AND r.driver_id =:driverId
            LIMIT 3
            """, nativeQuery = true)
    Integer carAndDriverMatch(
            @Param("driverId") Long driverId,
            @Param("carId") Long carId);
}

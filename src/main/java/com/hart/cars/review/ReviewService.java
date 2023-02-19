package com.hart.cars.review;

import java.util.List;
import java.util.Optional;

import com.hart.cars.advice.BadRequestException;
import com.hart.cars.advice.NotFoundException;
import com.hart.cars.cars.Car;
import com.hart.cars.cars.dto.UpdateCarDto;
import com.hart.cars.cars.dto.projection.OnlyCar;
import com.hart.cars.review.dto.UpdateReviewDto;
import com.hart.cars.review.dto.projection.OnlyReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review getReview(Long reviewId) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("A review with the id: " + reviewId + " does not exist."));
        return review;
    }

    public List<OnlyReview> getReviews(Optional<Integer> rating) {
        if (rating.isPresent()) {
            return this.reviewRepository.getAllReviewsByRating(rating);
        }
        return this.reviewRepository.getAllReviews();
    }

    public void createReview(Review review) {
        System.out.println(review.getDriver().getId());
        Integer match = this.reviewRepository.carAndDriverMatch(
                review.getDriver().getId(),
                review.getCar().getId());

        if (match >= 1) {
            throw new BadRequestException("You have already reviewed this car.");
        }
        this.reviewRepository.save(review);
    }

    public Review updateReview(Long reviewId, UpdateReviewDto updateReviewDto) {
        boolean exists = this.reviewRepository.existsById(reviewId);

        if (!exists) {
            throw new NotFoundException("A review with the id: " + reviewId + " does not exist.");
        }

        Review review = this.reviewRepository.findById(reviewId).orElseThrow();

        if (updateReviewDto.getRating() > 5 || updateReviewDto.getRating() < 1) {
            throw new BadRequestException("A rating must be between 1 and 5");
        }

        if (updateReviewDto.getRating() == null || updateReviewDto.getText() == null) {
            throw new BadRequestException("Please provide a rating and text.");
        }

        review.setText(updateReviewDto.getText());
        review.setRating(updateReviewDto.getRating());

        this.reviewRepository.save(review);

        return review;
    }

    public void deleteReview(Long reviewId) {
        boolean exists = this.reviewRepository.existsById(reviewId);

        if (!exists) {
            throw new NotFoundException("A review with the id " + reviewId + "does not exist.");
        }
        this.reviewRepository.deleteById(reviewId);
    }
}

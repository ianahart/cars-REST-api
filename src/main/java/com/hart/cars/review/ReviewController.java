package com.hart.cars.review;

import java.util.List;
import java.util.Optional;

import com.hart.cars.review.dto.UpdateReviewDto;
import com.hart.cars.review.dto.projection.OnlyReview;

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
@RequestMapping(path = "/api/v1/reviews")
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public List<OnlyReview> getAllReviews(@RequestParam("rating") Optional<Integer> rating) {
        return this.reviewService.getReviews(rating);
    }

    @PostMapping("/")
    public void createReview(@RequestBody Review review) {
        this.reviewService.createReview(review);
    }

    @GetMapping("/{reviewId}")
    public Review getReview(@PathVariable("reviewId") Long reviewId) {
        return this.reviewService.getReview(reviewId);
    }

    @PatchMapping("/{reviewId}")
    public Review updateReview(@PathVariable("reviewId") Long reviewId,
            @RequestBody UpdateReviewDto updateReviewDto) {
        return this.reviewService.updateReview(reviewId, updateReviewDto);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        this.reviewService.deleteReview(reviewId);
    }
}

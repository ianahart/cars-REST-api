package com.hart.cars.review.dto;

public class UpdateReviewDto {
    private Integer rating;
    private String text;

    public String getText() {

        return text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}

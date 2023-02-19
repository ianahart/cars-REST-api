package com.hart.cars.review;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hart.cars.cars.Car;
import com.hart.cars.driver.Driver;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity()
@Table(name = "review")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Review {

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false, referencedColumnName = "id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false, referencedColumnName = "id")
    private Car car;

    @Id
    @SequenceGenerator(name = "review_sequence", sequenceName = "review_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false, updatable = true, length = 200)
    private String text;

    @Column(name = "rating", nullable = false, length = 1)
    private Integer rating;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Review() {

    }

    public Review(Long id, Integer rating, String text, Driver driver, Car car) {
        this.id = id;
        this.rating = rating;
        this.text = text;
        this.driver = driver;
        this.car = car;
    }

    public Review(Integer rating, String text, Driver driver, Car car) {
        this.rating = rating;
        this.text = text;
        this.driver = driver;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getRating() {
        return rating;
    }

    public Driver getDriver() {
        return driver;
    }

    public Car getCar() {
        return car;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating='" + rating + '\'' +
                ", car=" + car + '\'' +
                ", driver=" + driver +
                ", text=" + text +
                '}';
    }

}

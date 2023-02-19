package com.hart.cars.cars;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hart.cars.driver.Driver;
import com.hart.cars.review.Review;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
// @Proxy(lazy = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Car {

    @Id
    @SequenceGenerator(name = "car_sequence", sequenceName = "car_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false, referencedColumnName = "id")
    @JsonInclude(Include.NON_NULL)
    private Driver driver;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

    @Column(name = "make", length = 75)
    private String make;

    @Column(name = "model", length = 75)
    private String model;

    @Column(name = "door", length = 10)
    private Integer door;

    @Column(name = "price", nullable = true, precision = 2)
    private Double price;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Car() {

    }

    public Car(Long id, String model, String make, Integer door, Driver driver, Double price) {
        this.id = id;
        this.model = model;
        this.make = make;
        this.door = door;
        this.driver = driver;
        this.price = price;

    }

    public Car(String model, String make, Integer door, Driver driver, Double price) {
        this.model = model;
        this.make = make;
        this.door = door;
        this.driver = driver;
        this.price = price;

    }

    public Double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public Integer getDoor() {
        return door;
    }

    public String getModel() {
        return model;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Driver getDriver() {
        return driver;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDoor(Integer door) {
        this.door = door;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void SetReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + model + '\'' +
                ", make='" + make + '\'' +
                ", door=" + door +
                ", driver=" + driver +
                ", price=" + price +
                '}';
    }
}

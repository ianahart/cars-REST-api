
# REST API with Spring Boot, Postgresql, JPA and Hibernate

# Steps to Setup

1. ### Clone the application
```
git clone https://github.com/ianahart/cars-REST-api.git
```
2. ### Create database called **cars** inside **Postgres**
```
CREATE DATABASE cars;
```
3. ### Change username and password as per your installation inside **application.properties**
> - Open src/main/resources/application.properties

> - Change spring.datasource.username and spring.datasource.password as per your postgresql installation

4. ### Build and run the app using maven

```
mvn clean install spring-boot:run
```
Alternatively you can run the app without packaging it using -
```
mvn spring-boot:run
```
The app will start running at http://localhost:8080
To View the API documentation in a ui http://localhost:8080/webjars/swagger-ui/index.html

# Explore Rest APIS

The app defines following CRUD APIS.
In order for this api to work, you must create(POST) in the following order: **driver**, **cars**, **reviews**.
```
drivers

GET /api/v1/drivers
POST /api/v1/drivers
GET /api/v1/drivers/{driverId}
PATCH /api/v1/drivers/{driverId}
 DELETE /api/v1/drivers/{driverId}

cars

 GET /api/v1/cars
POST /api/v1/cars
GET /api/v1/cars/{driverId}
PATCH /api/v1/cars/{driverId}
 DELETE /api/v1/cars/{driverId}

 reviews

 GET /api/v1/reviews
POST /api/v1/reviews
GET /api/v1/reviews/{driverId}
PATCH /api/v1/reviews/{driverId}
 DELETE /api/v1/reviews/{driverId}
```
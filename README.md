# Virtual Power Plant – Battery Aggregation System

This Spring Boot application simulates a Virtual Power Plant system. It allows storing battery details and retrieving battery statistics (sorted names, total and average capacity) based on postcode ranges.

---

## Features

- Save battery data (name, postcode, watt capacity)
- Query batteries by postcode range
- Return:
  - Sorted battery names
  - Total watt capacity
  - Average watt capacity
- RESTful API architecture
- Java Streams for aggregation
- Unit testing with 70%+ code coverage using JaCoCo

---

## Architecture

### 1. **Controller**
- `PlantController`  
  - `POST /batteries`: Save list of batteries  
  - `GET /batteries?from=xxxx&to=yyyy`: Retrieve battery info in postcode range

### 2. **Service**
- `PlantService`  
  - Business logic for persistence and aggregation  
  - Uses Java Streams to compute total/average watt capacity

### 3. **Repository**
- `BatteryRepository` extends `JpaRepository` for DB interaction

### 4. **DTOs**
- `BatteryRequestBody`: Input model  
- `BatteryResponse`: Output model

### 5. **Entity**
- `Battery`: JPA entity class for batteries

### 6. **Testing**
- Unit tests using JUnit and Mockito  
- Code coverage enforced using JaCoCo (70% threshold)

---

## Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 / MySQL (switchable)
- Maven
- JUnit 5
- Mockito
- JaCoCo

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- Git


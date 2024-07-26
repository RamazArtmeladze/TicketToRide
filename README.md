# TicketUK Project

## Overview

TicketUK is a Spring Boot application designed for managing travel tickets.
It provides functionalities for user registration, login, and managing travel segments and ticket purchases.
The application implements security features using Spring Security and uses a PostgreSQL database for data storage.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [API Endpoints](#api-endpoints)
- [Running Tests](#running-tests)

## Features

- User registration and authentication
- Travel segment management
- Ticket purchase and cost calculation
- Secure endpoints with Spring Security
- Dijkstra's algorithm for finding the shortest path between travel segments

## Technologies Used

- Java 21
- Spring Boot 3.2.3
- Spring Security 6.2.2
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- Mockito (for testing)

## Getting Started

### Prerequisites

- Java 21
- Maven
- PostgreSQL

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/TicketUK.git
   cd TicketUK
2. **Configure the PostgreSQL database:**

    Create a PostgreSQL database and update the application.properties file with your database credentials.

    properties
    Copy code

    `spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update`

3. **Install dependencies and build the project:**

    bash
    Copy code
    `mvn clean install`
    Run the application:

    bash
    Copy code
    `mvn spring-boot:run`

## API Endpoints

  - Authentication

Login: POST /login

    `Request Body: LoginUserDto { email, password }
    Response: Login status message`

User Registration: POST /userRegistration

    `Request Body: UserRegistrationDto { name, email, password }
    Response: Registered user details`

  - Travel Management

Get All Segments: GET /travel/all

    `Response: List of all travel segments`

Add Segment: POST /travel/add-segment

    `Request Body: DestinationDto { id, departure, arrival, segmentCount }
    Response: Added travel segment details`

Calculate Ticket Cost: POST /travel/find-ticket

    `Request Body: TicketSearchDto { departure, arrival }
    Response: Ticket with price details`

Save Ticket: POST /travel/save-ticket

    `Request Body: TicketDto { departure, arrival, segmentCount, price, currency, travellerAmount, traveller }
    Response: Ticket purchase status (success or failure)`


## Running Tests

To run the tests for the project, use the following command:

- bash
    Copy code
    `mvn test`


## License
This project is licensed under the Ramaz Artmeladze License.
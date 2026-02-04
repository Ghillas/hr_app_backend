# REST API – Spring Boot

This project is a REST API and the back-end of HR management application built with Spring Boot.
This project exists in two versions to illustrate its evolution from a basic API to a secured API using Spring Security and JWT.

---

## Project Structure & Versions

This repository contains two versions of the backend:

###  `main` branch (stable)
- Public REST API
- No authentication
- Focus on API design and data persistence

### `auth-jwt` branch (work in progress)
- JWT-based authentication
- Spring Security integration
- Secured endpoints

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Spring Security (auth-jwt branch)
- JWT (auth-jwt branch)

---

## Running the Project

### Prerequisites
- Java 17+
- Maven
- PostgreSQL

---

### Database Configuration

This project uses a local PostgreSQL database.

Database credentials are **not included** in the repository.

To run the project locally:

1. Create a PostgreSQL database
2. Create a new file names "application-dev.properties" in the same folder as application.properties
3. Add to application-dev.properties :
     spring.datasource.url = jdbc:postgresql://localhost:5432/name_of_your_database
     spring.datasource.username = your_username
     spring.datasource.password = your_password
4. Run the command :
     mvn spring-boot:run

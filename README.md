# Employee Management System

A simple **Employee Management REST API** built using **Spring Boot**.

This project is part of my learning journey with Spring Boot.  
The main goal is to understand backend development fundamentals and apply **clean architecture principles** in a real project.


---

## 🚀 Features

- **POST** `/api/employees/register`  
  Register a new employee with request validation

- **GET** `/api/employees`  
  Retrieve all employees

- **GET** `/api/employees/{id}`  
  Retrieve a specific employee by ID

- **PUT** `/api/employees/{id}`  
  Update employee information by ID

- **DELETE** `/api/employees/{id}`  
  Delete an employee by ID

- Input validation using **Jakarta Validation**
- Standardized API responses using a custom response wrapper

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **PostgreSQL**
- **Lombok**
- **Maven**

---

## 🧱 Architecture

The project follows a **layered architecture**:

- **Controller**: Handles HTTP requests and responses  
- **Service**: Contains business logic  
- **Repository**: Handles database operations using JPA  
- **DTO & Mapper**: Separates API models from database entities  
- **Exception**: Centralized error handling  

This structure improves readability, maintainability, and scalability.


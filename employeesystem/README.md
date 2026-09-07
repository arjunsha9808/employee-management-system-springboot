# Employee Management System

A backend REST API for managing employees and departments, built using Java and Spring Boot. The application provides CRUD operations, MySQL database integration, DTO-based API responses, exception handling, and logging.

## Features

- Create a new employee
- Get all employees
- Get employee by ID
- Update employee details
- Delete employee
- Department management
- DTO-based API responses
- Global exception handling
- Employee-not-found exception handling
- MySQL database integration
- RESTful APIs
- Logging using SLF4J
- Tested using Postman

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- REST API
- Maven
- Postman
- Git & GitHub
- IntelliJ IDEA

## Project Structure

```text
src/main/java/com/example/employeesystem
│
├── controller
│   └── EmployeeController.java
│
├── dto
│   └── EmployeeDTO.java
│
├── entity
│   ├── Employee.java
│   └── Department.java
│
├── exception
│   ├── EmployeeNotFoundException.java
│   └── GlobalExceptionHandler.java
│
├── repository
│   ├── EmployeeRepository.java
│   └── DepartmentRepository.java
│
├── service
│   └── EmployeeService.java
│
└── EmployeesystemApplication.java


## Author
** Arjun Sharma **

Java Backend DEVELOPER | Spring Boot | MySQL | REST APIs
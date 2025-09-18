# TOC Task Management System

A secure web application for managing tasks related to UK Train Operating Companies (TOCs), built with Spring Boot and Java as part of the Software Engineering & DevOps module.

## Table of Contents
1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Project Structure](#project-structure)
4. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Local Installation](#local-installation)
5. [Running with Docker](#running-with-docker)
6. [User Roles and Credentials](#user-roles-and-credentials)
7. [CI/CD Pipeline](#cicd-pipeline)

## Features
* **User Authentication:** Secure login and registration with password hashing (BCrypt).
* **Role-Based Access Control:** Two user roles are supported:
    * `REGULAR`: Can create, read, update, and delete their own TOCs, tasks, and time entries.
    * `ADMIN`: Has all `REGULAR` user permissions, plus the ability to view and delete any TOC or task in the system via a dedicated admin dashboard.
* **Full CRUD Functionality:** Complete Create, Read, Update, and Delete operations for TOCs, Tasks, and Time Entries.
* **Containerized Deployment:** Includes a `Dockerfile` and `docker-compose.yml` for easy deployment.
* **Continuous Integration:** A GitHub Actions pipeline automatically builds and tests the application on every push to the main branch.

## Technologies Used
* **Backend:** Java 21, Spring Boot 3.3.1
* **Database:** SQLite
* **Frontend:** Thymeleaf, HTML, CSS, JavaScript
* **Security:** Spring Security 6
* **Build & Dependencies:** Apache Maven
* **Testing:** JUnit 5, Mockito
* **Containerization:** Docker

## Project Structure
The project follows a standard Maven layout and is organized into a modular structure to promote separation of concerns.
.
├── .github/workflows/      # Contains the GitHub Actions CI/CD pipeline configuration.
├── data/                   # Holds the SQLite database file.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/se_devops/tocsystem/
│   │   │       ├── config/         # Spring Security configuration.
│   │   │       ├── controller/     # Handles incoming HTTP requests and user interaction.
│   │   │       ├── model/          # JPA entities that map to database tables.
│   │   │       ├── repository/     # Spring Data JPA repositories for database access.
│   │   │       └── service/        # Contains the core business logic.
│   │   └── resources
│   │       ├── static/             # CSS and JavaScript files.
│   │       ├── templates/          # Thymeleaf HTML templates for the UI.
│   │       ├── data.sql            # Initial data to populate the database.
│   │       ├── schema.sql          # The SQL schema for the database tables.
│   │       └── application.properties # Spring Boot configuration.
│   └── test/java/              # Automated unit and integration tests.
└── pom.xml                 # Maven project configuration.

## Getting Started

### Prerequisites
* Java Development Kit (JDK) 21 or later.
* Apache Maven.

### Local Installation
1.  Clone the repository to your local machine:
    ```bash
    git clone <your-repository-url>
    ```
2.  Navigate to the project's root directory:
    ```bash
    cd toc-task-management-system
    ```
3.  Build and run the application using the Maven wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  The application will start on `http://localhost:8080`.

## Running with Docker
The application is fully containerized for easy deployment and consistent environments.

1.  **Build the Docker image and run the container** using Docker Compose:
    ```bash
    docker-compose up --build
    ```
2.  The application will be accessible at `http://localhost:8080`. The `data` directory, which contains the SQLite database, is mounted as a volume to ensure data persistence.

## User Roles and Credentials
The application is pre-populated with several users for testing purposes. The credentials are listed below:

| Username  | Password        | Role      |
| :-------- |:----------------| :-------- |
| `admin`   | `Secur3Ad!min`  | `ADMIN`   |
| `user`    | `P@ssw0rdUser!` | `REGULAR` |
| `j.doe`   | `d0e_!P@ssword` | `REGULAR` |
| `s.smith` | `Sm!th_Pa55`    | `REGULAR` |
| `m.jones` | `J0nes!Secure`  | `REGULAR` |

## CI/CD Pipeline
This project uses **GitHub Actions** for its Continuous Integration (CI) pipeline. The workflow is defined in the `.github/workflows/pipeline.yml` file.

The pipeline automatically triggers on every `push` or `pull_request` to the `main` branch and performs the following steps:
1.  **Checks out** the source code.
2.  **Sets up JDK 21**.
3.  **Grants execute permissions** to the Maven wrapper.
4.  **Builds the project and runs all automated tests** using the `./mvnw clean install` command.


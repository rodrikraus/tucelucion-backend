# E-Commerce Backend Application

This is a **Spring Boot** backend application designed for the company **"TuCelucion"** e-commerce system. It includes REST APIs, database integration, server-side rendering with Thymeleaf, and containerization with Docker. The application is currently **deployed on Google Cloud** and integrates with **Supabase** as the backend database.

## 📦 Core Technologies

### 🧠 Backend Framework
- **Spring Boot 3.4.5** – Main application framework
- **Spring Web** – REST API and web layer
- **Spring Data JPA** – Simplified database access using repositories
- **Spring Boot DevTools** – Hot-reload and other development-time features

### 💻 Programming Language
- **Java 17** – Using Eclipse Temurin JDK

### 🗃️ Database & ORM
- **Supabase (PostgreSQL)** – Fully managed PostgreSQL database via Supabase
- **Hibernate** – ORM framework via Spring Data JPA
- **HikariCP** – High-performance JDBC connection pooling

### 🧩 Build & Dependency Management
- **Maven** – Build automation and dependency management
- **Maven Wrapper** – Ensures consistent builds across environments

### 🖥️ Template Engine
- **Thymeleaf** – Server-side rendering of HTML templates

### 📚 Additional Libraries
- **Lombok** – Reduces boilerplate code using annotations
- **Spring DotEnv** – Manages environment variables easily
- **Spring Boot Test** – Built-in testing framework

### 🐳 Containerization & Deployment
- **Docker** – Containerizes the application for easy deployment
- **Google Cloud Run** – Current deployment platform
- **Supabase** – PostgreSQL hosting and authentication

## 🧱 Project Structure

The application follows the **Spring MVC architecture**:

- `controllers/` – Handle HTTP requests and map them to services
- `services/` – Contain business logic
- `repositories/` – Interface with the database using Spring Data JPA
- `models/` – Entity classes mapped to database tables
- `templates/` – HTML views rendered using Thymeleaf
- `static/` – Contains static resources like CSS, JS, and images

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven
- Docker (optional for containerization)
- Supabase PostgreSQL credentials

### Run Locally

```bash
# Clone the repository
git clone https://github.com/your-username/your-project.git
cd your-project

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

## 🏁 Deployment
This project is currently deployed on Google Cloud Run using a Docker container. It connects securely to a Supabase PostgreSQL instance for all database operations.

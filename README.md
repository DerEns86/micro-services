# Spring Microservice Project with PostgreSQL

This project is part of a microservice architecture using Spring Boot. It connects to two PostgreSQL databases running in Docker containers: `ms-employees` and `ms-projects`.

---

## 🐘 PostgreSQL Setup with Docker Desktop

Two PostgreSQL containers are required:

- `ms-employees` (listening on port `5433`)
- `ms-projects` (listening on port `5432`)

> **Note:** You can run these containers using Docker Desktop manually.

### ✅ Step-by-step Instructions

1. Open **Docker Desktop**
2. Go to the **Containers** tab and click on **+ Add Container**
3. Set up each container as follows:

#### `ms-projects` Container

| Property       | Value                     |
|----------------|---------------------------|
| Image          | `postgres:15`             |
| Container Name | `ms-projects`             |
| Port           | `5432:5432`               |
| Environment    |                            |
| - `POSTGRES_DB`       | `projects`         |
| - `POSTGRES_USER`     | `username`         |
| - `POSTGRES_PASSWORD` | `password`         |

#### `ms-employees` Container

| Property       | Value                     |
|----------------|---------------------------|
| Image          | `postgres:15`             |
| Container Name | `ms-employees`            |
| Port           | `5433:5432`               |
| Environment    |                            |
| - `POSTGRES_DB`       | `employees`        |
| - `POSTGRES_USER`     | `username`         |
| - `POSTGRES_PASSWORD` | `password`         |

> ⚠️ Make sure both containers are running and accessible via their respective ports.

---

## 🚀 Running the Microservice

Once the PostgreSQL containers are up and running, you can start the Spring Boot microservice locally.

### Prerequisites

- Java 17+
- Maven
- Docker Desktop (with both DB containers running)
- Eureka server running on port `8761`
- Spring Cloud Config Server running on port `8888`

### Run the service

```bash
./mvnw spring-boot:run
```

The service will start on port `8090`.

# Task Management System — Weeks 1, 2 and 3

Java 17 + Spring Boot 3.5.7 REST API. Week 3 replaces the in-memory `ArrayList` persistence with MySQL and Spring Data JPA.

## Requirements
- JDK 17+
- Maven 3.6+
- MySQL 8+
- Postman or Swagger UI

## Database setup

Create the database (or let the configured MySQL URL create it when the MySQL user has permission):

```sql
CREATE DATABASE task_management;
```

Default connection values are `root` / `root`. Change them in `src/main/resources/application.properties`, or set environment variables:

```bash
DB_USERNAME=root
DB_PASSWORD=your_password
```

Do not commit real database credentials. `spring.jpa.hibernate.ddl-auto=update` creates/updates the `tasks` table for this learning project.

## Run

```bash
mvn clean test
mvn spring-boot:run
```

Base URL: `http://localhost:8080`  
Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Sample task JSON

```json
{
  "title": "Practice Spring Data JPA",
  "description": "Implement repositories and custom queries",
  "status": "TODO",
  "priority": "HIGH",
  "dueDate": "2026-11-20"
}
```

Allowed statuses: `TODO`, `IN_PROGRESS`, `COMPLETED`.  
Allowed priorities: `LOW`, `MEDIUM`, `HIGH`.  
`dueDate` is optional and uses `YYYY-MM-DD`.

## API endpoints

| Method | URL | Purpose |
|---|---|---|
| POST | `/api/tasks` | Create task |
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get task by ID |
| PUT | `/api/tasks/{id}` | Update task |
| DELETE | `/api/tasks/{id}` | Delete task |
| GET | `/api/tasks/search?keyword=java` | Search title/description |
| GET | `/api/tasks/status/TODO` | Filter by status |
| GET | `/api/tasks/priority/HIGH` | Filter by priority |
| GET | `/api/tasks/status/TODO/page?page=0&size=10` | Paginated status results |
| PATCH | `/api/tasks/{id}/status?status=COMPLETED` | Change task status |
| GET | `/api/tasks/summary` | Count tasks by status |

## Week 3 concepts covered

- MySQL datasource configuration
- JPA entity mapping: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, `@Enumerated`
- `@MappedSuperclass` for the shared ID in `BaseEntity`
- `JpaRepository<Task, Long>` for database CRUD
- Derived query methods such as `findByStatus` and `findByPriority`
- JPQL using `@Query` and `@Param`
- Pagination using `Pageable` and `PageRequest`
- Transaction boundaries using `@Transactional`
- H2-based repository test

## Notes

- Data is now stored in MySQL and remains after application restarts.
- The test profile uses an in-memory H2 database; tests do not require a running MySQL server.
- This package preserves the API contract described in the repository README. The source archive itself was not available to inspect in this environment, so compare package names or any local customizations before replacing your existing folder.

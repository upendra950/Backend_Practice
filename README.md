# Task Management System — Weeks 1 and 2

Beginner-friendly Java + Spring Boot REST API. It uses an in-memory `ArrayList`; no SQL database is used yet.

## Requirements
- JDK 17+
- Maven (or Maven support in your IDE)
- Postman (recommended)

## Run
Open a terminal in the project root and run:

```bash
mvn spring-boot:run
```

Or open the project in IntelliJ and run `TaskManagementApplication`.

Base URL: `http://localhost:8080`

Three sample tasks are loaded on startup. Data resets when the application restarts.

## Task JSON example

```json
{
  "title": "Prepare Java assignment",
  "description": "Practice OOP and Collections",
  "status": "TODO",
  "priority": "HIGH",
  "dueDate": "2026-11-15"
}
```

Allowed statuses: `TODO`, `IN_PROGRESS`, `COMPLETED`.
Allowed priorities: `LOW`, `MEDIUM`, `HIGH`.
The dueDate is optional and uses `YYYY-MM-DD`.

## API endpoints

| Method | URL | Purpose |
|---|---|---|
| POST | `/api/tasks` | Create task |
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get task by ID |
| PUT | `/api/tasks/{id}` | Replace task details |
| DELETE | `/api/tasks/{id}` | Delete task |
| GET | `/api/tasks/search?keyword=java` | Search title/description |
| GET | `/api/tasks/status/TODO` | Filter by status |
| GET | `/api/tasks/priority/HIGH` | Filter by priority |
| PATCH | `/api/tasks/{id}/status?status=COMPLETED` | Change task status |
| GET | `/api/tasks/summary` | Count tasks by status |

## Roadmap coverage

### Week 1
- Core Java: fields, constructors, methods, conditions
- OOP: encapsulation, inheritance (`Task` extends `BaseEntity`), abstraction (`TaskService` interface), implementation/polymorphism (`TaskServiceImpl`)
- Collections: `ArrayList`
- Streams: filter, sort, search, group and count
- Exception handling: custom `TaskNotFoundException`, centralized exception handler

### Week 2
- Spring Boot application
- REST APIs and HTTP methods: GET, POST, PUT, DELETE, PATCH
- Validation: `@Valid`, `@NotBlank`, `@NotNull`, `@Size`
- Annotations: `@SpringBootApplication`, `@RestController`, `@RequestMapping`, `@Service`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PatchMapping`, `@RequestBody`, `@PathVariable`, `@RequestParam`, `@RestControllerAdvice`, `@ExceptionHandler`
- Global exception handling

## Scope
No SQL, Spring Data JPA, microservices, event listeners, Prometheus or Grafana are included. Those are for later roadmap weeks.

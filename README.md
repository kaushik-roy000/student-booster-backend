# Student Booster Backend

AI-Powered Student Booster Application Backend built with Spring Boot 3, designed to help students enhance their learning through AI-assisted content analysis, lesson management, and generated study materials.

## Technology Stack

- **Java**: 21
- **Spring Boot**: 3.2.0
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Authentication**: JWT (JSON Web Tokens)
- **ORM**: Hibernate JPA
- **Additional Libraries**:
  - Spring Security
  - Tess4j (OCR)
  - Apache PDFBox (PDF Processing)
  - Spring AI (Ollama integration)
  - Lombok (Boilerplate reduction)

## Project Structure

```
src/main/java/com/studentbooster/
├── config/              # Configuration classes (Security, Exception Handling)
├── controller/          # REST API endpoints
├── service/             # Business logic layer
├── repository/          # Data access layer (JPA repositories)
├── entity/              # JPA entities
├── dto/                 # Data Transfer Objects
├── exception/           # Custom exception classes
├── util/                # Utility classes (JWT, etc.)
└── security/            # Security-related classes (JWT Filter, Entry Point)

src/main/resources/
└── application.yml      # Application configuration
```

## Core Entities

### User
- **id**: Unique identifier (Long)
- **username**: Unique username (String, max 50)
- **email**: Unique email address (String, max 100)
- **password**: Encrypted password (String)
- **role**: User role - STUDENT, TEACHER, ADMIN (Enum)
- **createdAt**: Account creation timestamp
- **updatedAt**: Last update timestamp

### Lesson
- **id**: Unique identifier (Long)
- **userId**: Reference to user who created the lesson (Long)
- **title**: Lesson title (String, max 200)
- **description**: Lesson description (Text)
- **createdAt**: Creation timestamp
- **updatedAt**: Last update timestamp

### LessonMaterial
- **id**: Unique identifier (Long)
- **lessonId**: Reference to parent lesson (Long)
- **fileName**: Original file name (String, max 255)
- **fileType**: File MIME type (String, max 50)
- **filePath**: Storage path to the file (Text)
- **uploadedAt**: Upload timestamp

### GeneratedContent
- **id**: Unique identifier (Long)
- **lessonMaterialId**: Reference to lesson material (Long)
- **contentType**: Type of generated content (String, max 100)
- **content**: Generated content (Text)
- **createdAt**: Generation timestamp

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login with username and password
- `GET /api/auth/health` - Health check for auth service

### Users
- `GET /api/users/{userId}` - Get user by ID
- `GET /api/users/username/{username}` - Get user by username
- `PUT /api/users/{userId}` - Update user profile

### Lessons
- `POST /api/lessons` - Create a new lesson
- `GET /api/lessons/{lessonId}` - Get lesson by ID
- `GET /api/lessons/user/{userId}` - Get all lessons for a user
- `PUT /api/lessons/{lessonId}` - Update a lesson
- `DELETE /api/lessons/{lessonId}` - Delete a lesson

### Lesson Materials
- `POST /api/materials` - Upload lesson material
- `GET /api/materials/{materialId}` - Get material by ID
- `GET /api/materials/lesson/{lessonId}` - Get all materials for a lesson
- `DELETE /api/materials/{materialId}` - Delete material

## Getting Started

### Prerequisites
- Java 21
- Maven 3.8+
- PostgreSQL 12+

### Installation

1. Clone the repository
```bash
git clone https://github.com/kaushik-roy000/student-booster-backend.git
cd student-booster-backend
```

2. Create PostgreSQL database
```sql
CREATE DATABASE student_booster;
```

3. Configure application properties
Edit `src/main/resources/application.yml` and update database credentials and JWT secret:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/student_booster
    username: your_username
    password: your_password

jwt:
  secret: your-secret-key-change-this-in-production
```

4. Build and run the application
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

## Authentication

The API uses JWT (JSON Web Token) for authentication. Include the token in the `Authorization` header:
```
Authorization: Bearer <your_jwt_token>
```

### Register Example
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "student1",
    "email": "student1@example.com",
    "password": "password123",
    "role": "STUDENT"
  }'
```

### Login Example
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "student1",
    "password": "password123"
  }'
```

## Testing

Run tests with Maven:
```bash
mvn test
```

## Development Notes

### Exception Handling
The application uses a global exception handler (`GlobalExceptionHandler`) that provides consistent error responses:
- `ResourceNotFoundException` - Returns 404 Not Found
- `DuplicateResourceException` - Returns 409 Conflict
- `UnauthorizedException` - Returns 401 Unauthorized
- `InvalidTokenException` - Returns 401 Unauthorized
- Validation errors - Returns 400 Bad Request

### Security Configuration
- Stateless session management (JWT-based)
- CSRF protection disabled for REST API
- Method-level security with `@PreAuthorize` annotations
- Role-based access control (RBAC)

### Logging
The application uses SLF4J with Logback for logging. Adjust logging levels in `application.yml`:
```yaml
logging:
  level:
    root: INFO
    com.studentbooster: DEBUG
```

## Future Enhancements

- [ ] File upload and storage service integration
- [ ] OCR implementation for image text extraction
- [ ] PDF processing and content extraction
- [ ] AI content generation with OpenAI integration
- [ ] Quiz generation from lesson materials
- [ ] Student progress tracking
- [ ] Email notifications
- [ ] API rate limiting
- [ ] Caching layer (Redis)

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For support, please open an issue in the repository.

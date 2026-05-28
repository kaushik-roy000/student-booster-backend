# Project Structure Overview

## Complete Directory Layout

```
student-booster-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/studentbooster/
│   │   │       ├── StudentBoosterApplication.java          [Main Spring Boot Application]
│   │   │       ├── config/
│   │   │       │   ├── SecurityConfig.java                 [Spring Security Configuration]
│   │   │       │   ├── GlobalExceptionHandler.java         [Centralized Exception Handling]
│   │   │       │   ├── ApplicationProperties.java          [Configuration Properties Binding]
│   │   │       │   └── WebConfig.java                      [Web Configuration - Future]
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java                 [Auth Endpoints: register, login]
│   │   │       │   ├── UserController.java                 [User Management Endpoints]
│   │   │       │   ├── LessonController.java               [Lesson Management Endpoints]
│   │   │       │   ├── LessonMaterialController.java       [Material Upload/Retrieval Endpoints]
│   │   │       │   ├── HealthController.java               [Health Check Endpoint]
│   │   │       │   └── GeneratedContentController.java     [Generated Content Endpoints - Future]
│   │   │       ├── service/
│   │   │       │   ├── AuthService.java                    [Authentication Business Logic]
│   │   │       │   ├── UserService.java                    [User Management Logic]
│   │   │       │   ├── LessonService.java                  [Lesson Management Logic]
│   │   │       │   ├── LessonMaterialService.java          [Material Management Logic]
│   │   │       │   ├── GeneratedContentService.java        [Content Generation Logic - Future]
│   │   │       │   ├── FileService.java                    [File Upload/Processing - Future]
│   │   │       │   └── AiService.java                      [AI Integration - Future]
│   │   │       ├── repository/
│   │   │       │   ├── UserRepository.java                 [User JPA Repository]
│   │   │       │   ├── LessonRepository.java               [Lesson JPA Repository]
│   │   │       │   ├── LessonMaterialRepository.java       [Material JPA Repository]
│   │   │       │   └── GeneratedContentRepository.java     [Content JPA Repository]
│   │   │       ├── entity/
│   │   │       │   ├── User.java                           [User Entity: username, email, password, role]
│   │   │       │   ├── Lesson.java                         [Lesson Entity: title, description, userId]
│   │   │       │   ├── LessonMaterial.java                 [Material Entity: fileName, fileType, filePath]
│   │   │       │   └── GeneratedContent.java               [Content Entity: contentType, content]
│   │   │       ├── dto/
│   │   │       │   ├── UserDto.java                        [User DTO for API responses]
│   │   │       │   ├── LoginRequest.java                   [Login API request]
│   │   │       │   ├── RegisterRequest.java                [Register API request]
│   │   │       │   ├── AuthResponse.java                   [Auth API response]
│   │   │       │   ├── LessonDto.java                      [Lesson DTO]
│   │   │       │   ├── LessonMaterialDto.java              [Material DTO]
│   │   │       └── GeneratedContentDto.java            [Content DTO]
│   │   │       ├── exception/
│   │   │       │   ├── ResourceNotFoundException.java       [404 Errors]
│   │   │       │   ├── DuplicateResourceException.java     [409 Conflict Errors]
│   │   │       │   ├── UnauthorizedException.java          [401 Auth Errors]
│   │   │       └── InvalidTokenException.java          [Invalid JWT Token Errors]
│   │   │       ├── security/
│   │   │       │   ├── JwtAuthenticationFilter.java        [JWT Token Validation Filter]
│   │   │       └── JwtAuthenticationEntryPoint.java   [JWT Auth Entry Point]
│   │   └── util/
│   │       ├── JwtUtil.java                        [JWT Generation and Validation]
│   │       ├── ValidationUtil.java                 [Input Validation Utilities]
│   │       └── FileUtil.java                       [File Handling Utilities]
│   │   └── resources/
│   │       ├── application.yml                     [Main Application Configuration]
│   │       └── application-dev.yml                 [Development Profile Config - Future]
│   └── test/
│       └── java/
│           └── com/studentbooster/
│               ├── StudentBoosterApplicationTests.java [Application Context Tests]
│               └── service/
│                   └── AuthServiceTest.java               [Auth Service Unit Tests]
├── pom.xml                                    [Maven Configuration]
├── README.md                                  [Project Overview]
├── API_DOCUMENTATION.md                       [Complete API Reference]
├── DEVELOPMENT.md                             [Development Setup and Guidelines]
├── .gitignore                                 [Git Ignore Rules]
└── LICENSE                                    [MIT License - Future]
```

## Key Components

### 1. Entities (JPA Models)
- **User**: Stores user credentials, roles, and profile data
- **Lesson**: Groups learning materials and assignments
- **LessonMaterial**: Holds uploaded files (PDFs, images, etc.)
- **GeneratedContent**: AI-generated study materials

### 2. Services (Business Logic)
- **AuthService**: Handles user registration and login
- **UserService**: User profile management
- **LessonService**: Lesson CRUD operations
- **LessonMaterialService**: File upload and retrieval

### 3. Controllers (REST Endpoints)
- **AuthController**: `/auth/**` - Public endpoints
- **UserController**: `/users/**` - User management
- **LessonController**: `/lessons/**` - Lesson management
- **LessonMaterialController**: `/materials/**` - File management
- **HealthController**: `/health` - Health checks

### 4. Security
- **JwtUtil**: Token generation and validation
- **JwtAuthenticationFilter**: Intercepts requests and validates tokens
- **SecurityConfig**: Spring Security configuration
- **JwtAuthenticationEntryPoint**: Handles auth errors

### 5. Data Access
- **Repositories**: Spring Data JPA interfaces for database queries
- **DTOs**: Data transfer objects for API communication
- **Entities**: JPA entities mapped to database tables

## Future Enhancements

### Planned Components
- **GeneratedContentService**: AI content generation
- **FileService**: Advanced file handling
- **AiService**: OpenAI and Ollama integration
- **NotificationService**: Email and push notifications
- **AnalyticsService**: Student progress tracking
- **CacheService**: Redis caching layer
- **SearchService**: Full-text search functionality

### Planned Features
- OCR for image text extraction (using Tess4j)
- PDF content extraction (using PDFBox)
- Quiz generation from materials
- Email notifications
- WebSocket for real-time updates
- File streaming and chunked uploads
- Advanced search and filtering
- API rate limiting
- Caching strategies

## Database Schema

### Users Table
```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
```

### Lessons Table
```sql
CREATE TABLE lessons (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### Lesson Materials Table
```sql
CREATE TABLE lesson_materials (
    id BIGSERIAL PRIMARY KEY,
    lesson_id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(50) NOT NULL,
    file_path TEXT NOT NULL,
    uploaded_at TIMESTAMP NOT NULL,
    FOREIGN KEY (lesson_id) REFERENCES lessons(id)
);
```

### Generated Content Table
```sql
CREATE TABLE generated_content (
    id BIGSERIAL PRIMARY KEY,
    lesson_material_id BIGINT NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (lesson_material_id) REFERENCES lesson_materials(id)
);
```

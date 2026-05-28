# Development Guide

## Prerequisites

- Java 21 JDK
- Maven 3.8+
- PostgreSQL 12+
- Git
- An IDE (IntelliJ IDEA, VS Code, or Eclipse)

## Local Development Setup

### 1. Clone the Repository
```bash
git clone https://github.com/kaushik-roy000/student-booster-backend.git
cd student-booster-backend
```

### 2. Database Setup

#### Using PostgreSQL Command Line
```bash
psql -U postgres
```

```sql
CREATE DATABASE student_booster;
 CREATE USER student_user WITH PASSWORD 'student_password';
ALTER ROLE student_user SET client_encoding TO 'utf8';
ALTER ROLE student_user SET default_transaction_isolation TO 'read committed';
ALTER ROLE student_user SET default_transaction_deferrable TO on;
ALTER ROLE student_user SET timezone TO 'UTC';
GRANT ALL PRIVILEGES ON DATABASE student_booster TO student_user;
```

### 3. Configuration

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/student_booster
    username: student_user
    password: student_password
  jpa:
    hibernate:
      ddl-auto: create-drop  # Use 'create-drop' for development, 'validate' for production

jwt:
  secret: your-development-secret-key-min-256-bits-for-production
  expiration: 86400000
```

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

## IDE Setup

### IntelliJ IDEA

1. Open the project root folder
2. IntelliJ will detect it as a Maven project
3. Wait for indexing to complete
4. Configure JDK 21 in Project Settings → Project
5. Run → Edit Configurations
6. Add Spring Boot configuration
7. Click Run

### VS Code

1. Install extensions:
   - Extension Pack for Java
   - Spring Boot Extension Pack
   - Maven for Java

2. Open project root
3. IntelliCode will suggest setup
4. Press Ctrl+F5 to run

## Project Structure

```
.
├── pom.xml                    # Maven configuration
├── README.md                  # Project overview
├── API_DOCUMENTATION.md       # API reference
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/studentbooster/
│   │   │       ├── StudentBoosterApplication.java
│   │   │       ├── config/             # Spring configurations
│   │   │       ├── controller/         # REST controllers
│   │   │       ├── service/            # Business logic
│   │   │       ├── repository/         # Data access
│   │   │       ├── entity/             # JPA entities
│   │   │       ├── dto/                # Data transfer objects
│   │   │       ├── exception/          # Custom exceptions
│   │   │       ├── util/               # Utility classes
│   │   │       └── security/           # Security classes
│   │   └── resources/
│   │       └── application.yml         # Application configuration
│   └── test/
│       └── java/
│           └── com/studentbooster/
│               └── service/            # Service tests
└── .gitignore
```

## Code Style and Conventions

### Naming Conventions
- Classes: PascalCase (e.g., `UserService`)
- Methods: camelCase (e.g., `getUserById`)
- Constants: UPPER_SNAKE_CASE (e.g., `MAX_FILE_SIZE`)
- Variables: camelCase (e.g., `userName`)

### Annotations
- Use `@Slf4j` for logging in all classes
- Use `@RequiredArgsConstructor` for constructor injection
- Use `@Transactional` for service methods that modify data
- Use `@PreAuthorize` for method-level security

### Exception Handling
- Create custom exceptions for domain-specific errors
- Use global exception handler for consistent responses
- Log all exceptions with proper context

### Documentation
- Add JavaDoc for public methods and classes
- Add inline comments for complex logic only
- Keep README and API documentation updated

## Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AuthServiceTest

# Run tests with coverage
mvn test jacoco:report
```

### Writing Tests

- Use JUnit 5 and Mockito
- Mock external dependencies
- Test both success and failure scenarios
- Place tests in `src/test/java` parallel to source

### Example Test Structure

```java
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private AuthService authService;
    
    @BeforeEach
    void setUp() {
        // Initialize test data
    }
    
    @Test
    void testLoginSuccess() {
        // Arrange
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        
        // Act
        AuthResponse response = authService.login(loginRequest);
        
        // Assert
        assertNotNull(response);
    }
}
```

## Debugging

### Setting Breakpoints
1. Click on the left margin of the line number
2. Press Ctrl+F5 (or Debug → Run)
3. Use the Debug panel to step through code

### Logging

Add to `application.yml` for debug logging:
```yaml
logging:
  level:
    root: INFO
    com.studentbooster: DEBUG
    org.springframework.security: DEBUG
```

## Database Migrations (Future)

When implementing Flyway or Liquibase:

```
src/main/resources/db/migration/
├── V1__initial_schema.sql
└── V2__add_new_tables.sql
```

## Performance Optimization

### Caching (Future)
```java
@Cacheable(value = "users", key = "#id")
public UserDto getUserById(Long id) {
    // Implementation
}
```

### Lazy Loading
```java
@OneToMany(fetch = FetchType.LAZY)
private List<Lesson> lessons;
```

## Deployment

### Build JAR
```bash
mvn clean package
```

### Run JAR
```bash
java -jar target/student-booster-backend-1.0.0.jar
```

## Troubleshooting

### Port Already in Use
```bash
# Kill process on port 8080
lsof -ti:8080 | xargs kill -9  # macOS/Linux
netstat -ano | findstr :8080   # Windows
```

### Database Connection Issues
- Verify PostgreSQL is running
- Check credentials in `application.yml`
- Verify database exists: `\l` in psql

### Maven Build Issues
```bash
mvn clean
mvn dependency:resolve
mvn compile
```

## Contributing

When contributing:
1. Create a feature branch
2. Write tests for new features
3. Follow code style conventions
4. Update documentation
5. Submit a pull request

## Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/guides/gs/accessing-data-jpa/)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [JWT Guide](https://auth0.com/blog/jwt-guide/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

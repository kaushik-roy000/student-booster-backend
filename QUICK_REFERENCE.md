# Quick Reference Card

## Project Info
- **Name**: Student Booster Backend
- **Framework**: Spring Boot 3.2.0
- **Language**: Java 21
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Repository**: https://github.com/kaushik-roy000/student-booster-backend

## Quick Commands

### Setup
```bash
git clone https://github.com/kaushik-roy000/student-booster-backend.git
cd student-booster-backend
mvn clean install
mvn spring-boot:run
```

### Database
```sql
CREATE DATABASE student_booster;
```

### Build
```bash
mvn clean package
mvn clean install
```

### Run
```bash
mvn spring-boot:run
java -jar target/student-booster-backend-1.0.0.jar
```

### Tests
```bash
mvn test
mvn test -Dtest=AuthServiceTest
```

## API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Authentication
```
POST   /auth/register          (No auth needed)
POST   /auth/login             (No auth needed)
GET    /auth/health            (No auth needed)
```

### Users
```
GET    /users/{userId}         (Auth required)
GET    /users/username/{username}
PUT    /users/{userId}
```

### Lessons
```
POST   /lessons                (Auth required)
GET    /lessons/{lessonId}
GET    /lessons/user/{userId}
PUT    /lessons/{lessonId}
DELETE /lessons/{lessonId}
```

### Materials
```
POST   /materials              (Auth required)
GET    /materials/{materialId}
GET    /materials/lesson/{lessonId}
DELETE /materials/{materialId}
```

### Health
```
GET    /health                 (No auth needed)
```

## Authentication Header
```
Authorization: Bearer <jwt_token>
```

## User Roles
- **STUDENT** - Can create lessons and materials
- **TEACHER** - Can update/delete own content
- **ADMIN** - Full access

## Configuration File
```
src/main/resources/application.yml
```

Key settings:
- Database URL
- JWT Secret (change in production!)
- JWT Expiration time
- Logging levels
- Upload directory

## Project Structure
```
src/main/java/com/studentbooster/
├── config/              Spring Security, Exception Handling
├── controller/          REST endpoints
├── service/             Business logic
├── repository/          Database access
├── entity/              JPA entities
├── dto/                 Data Transfer Objects
├── exception/           Custom exceptions
├── security/            JWT components
└── util/                Utilities
```

## Dependencies (Key)
- spring-boot-starter-web
- spring-boot-starter-security
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- postgresql
- jjwt (JWT library)
- lombok
- tess4j (OCR)
- pdfbox (PDF processing)
- spring-ai-ollama

## Important Files

| File | Purpose |
|------|--------|
| pom.xml | Maven configuration |
| application.yml | App configuration |
| SecurityConfig.java | Spring Security setup |
| JwtUtil.java | JWT token operations |
| GlobalExceptionHandler.java | Error handling |

## Common Tasks

### Create new endpoint
1. Create method in service class
2. Create method in controller class
3. Add @RequestMapping or @GetMapping/@PostMapping
4. Add @PreAuthorize if authentication needed
5. Test the endpoint

### Add new entity
1. Create entity class in `entity/` package
2. Add @Entity and @Table annotations
3. Create repository in `repository/` package
4. Run application to auto-generate table

### Debug
- Check logs: `logs/application.log`
- Enable DEBUG logging for your package
- Use IDE debugger (F5 in VS Code)
- Check database tables with PostgreSQL client

## Troubleshooting

### Port 8080 already in use
```bash
lsof -ti:8080 | xargs kill -9      # Mac/Linux
netstat -ano | findstr :8080       # Windows
```

### Database connection error
- Check PostgreSQL is running
- Verify credentials in application.yml
- Verify database exists

### Maven build error
```bash
mvn clean
mvn dependency:resolve
mvn compile
```

### JWT token issues
- Check token in header: `Authorization: Bearer <token>`
- Verify token hasn't expired
- Check JWT secret matches in config

## Resources

- Full documentation: README.md
- API reference: API_DOCUMENTATION.md
- Development guide: DEVELOPMENT.md
- Project structure: PROJECT_STRUCTURE.md
- Spring Boot: https://spring.io/projects/spring-boot
- Spring Security: https://spring.io/projects/spring-security
- JWT: https://auth0.com/blog/jwt-guide/

## Deployment

### Build Production JAR
```bash
mvn clean package -DskipTests
```

### Run JAR
```bash
java -Dspring.profiles.active=prod -jar app.jar
```

### Environment Variables (Production)
```
DATABASE_URL=jdbc:postgresql://prod-host:5432/db
DATABASE_USER=prod_user
DATABASE_PASSWORD=secure_password
JWT_SECRET=very-long-secure-secret-key
```

## Performance Tips

- Use connection pooling (configured in application.yml)
- Enable caching for repeated queries
- Use lazy loading for relationships
- Add indexes on frequently queried columns
- Monitor with actuator endpoints: `/actuator/metrics`

## Security Checklist

- [ ] Change JWT secret before production
- [ ] Use HTTPS in production
- [ ] Set secure database credentials
- [ ] Enable CORS only for trusted origins
- [ ] Implement rate limiting
- [ ] Add input validation
- [ ] Use prepared statements (JPA does this)
- [ ] Implement audit logging
- [ ] Regular security updates

---
**Version**: 1.0.0 | **Last Updated**: 2024 | **Status**: Ready for Development

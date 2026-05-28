# API Documentation

## Base URL
```
http://localhost:8080/api
```

## Authentication

All protected endpoints require a JWT token in the `Authorization` header:
```
Authorization: Bearer <jwt_token>
```

## Endpoints

### Authentication Endpoints

#### 1. Register User
```http
POST /auth/register
Content-Type: application/json

{
  "username": "student1",
  "email": "student1@example.com",
  "password": "password123",
  "role": "STUDENT"
}
```

**Response (201 Created):**
```json
{
  "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9...",
  "username": "student1",
  "email": "student1@example.com",
  "role": "STUDENT"
}
```

#### 2. Login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "student1",
  "password": "password123"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9...",
  "username": "student1",
  "email": "student1@example.com",
  "role": "STUDENT"
}
```

#### 3. Health Check
```http
GET /auth/health
```

**Response (200 OK):**
```
Auth service is healthy
```

### User Endpoints

#### 1. Get User by ID
```http
GET /users/{userId}
Authorization: Bearer <jwt_token>
```

**Response (200 OK):**
```json
{
  "id": 1,
  "username": "student1",
  "email": "student1@example.com",
  "role": "STUDENT",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

#### 2. Get User by Username
```http
GET /users/username/{username}
Authorization: Bearer <jwt_token>
```

#### 3. Update User Profile
```http
PUT /users/{userId}
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "id": 1,
  "username": "student1_updated",
  "email": "updated@example.com",
  "role": "STUDENT"
}
```

### Lesson Endpoints

#### 1. Create Lesson
```http
POST /lessons
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "userId": 1,
  "title": "Mathematics 101",
  "description": "Introduction to Calculus"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "userId": 1,
  "title": "Mathematics 101",
  "description": "Introduction to Calculus",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

#### 2. Get Lesson by ID
```http
GET /lessons/{lessonId}
Authorization: Bearer <jwt_token>
```

#### 3. Get User's Lessons
```http
GET /lessons/user/{userId}
Authorization: Bearer <jwt_token>
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "userId": 1,
    "title": "Mathematics 101",
    "description": "Introduction to Calculus",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
  }
]
```

#### 4. Update Lesson
```http
PUT /lessons/{lessonId}
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "userId": 1,
  "title": "Mathematics 101 - Updated",
  "description": "Advanced Calculus Topics"
}
```

#### 5. Delete Lesson
```http
DELETE /lessons/{lessonId}
Authorization: Bearer <jwt_token>
```

**Response (204 No Content)**

### Lesson Material Endpoints

#### 1. Upload Material
```http
POST /materials
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "lessonId": 1,
  "fileName": "calculus_notes.pdf",
  "fileType": "application/pdf",
  "filePath": "/uploads/calculus_notes.pdf"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "lessonId": 1,
  "fileName": "calculus_notes.pdf",
  "fileType": "application/pdf",
  "filePath": "/uploads/calculus_notes.pdf",
  "uploadedAt": "2024-01-15T10:30:00"
}
```

#### 2. Get Material by ID
```http
GET /materials/{materialId}
Authorization: Bearer <jwt_token>
```

#### 3. Get Lesson Materials
```http
GET /materials/lesson/{lessonId}
Authorization: Bearer <jwt_token>
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "lessonId": 1,
    "fileName": "calculus_notes.pdf",
    "fileType": "application/pdf",
    "filePath": "/uploads/calculus_notes.pdf",
    "uploadedAt": "2024-01-15T10:30:00"
  }
]
```

#### 4. Delete Material
```http
DELETE /materials/{materialId}
Authorization: Bearer <jwt_token>
```

**Response (204 No Content)**

### Health Check

#### Get Application Health
```http
GET /health
```

**Response (200 OK):**
```json
{
  "status": "UP",
  "message": "Student Booster Backend is running",
  "timestamp": "2024-01-15T10:30:00"
}
```

## Error Responses

### 400 Bad Request
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed: Username must be between 3 and 50 characters",
  "path": "/api/auth/register"
}
```

### 401 Unauthorized
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid JWT token",
  "path": "/api/users/1"
}
```

### 404 Not Found
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with id: 999",
  "path": "/api/users/999"
}
```

### 409 Conflict
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 409,
  "error": "Conflict",
  "message": "Username already exists: student1",
  "path": "/api/auth/register"
}
```

## User Roles and Permissions

### STUDENT
- View own profile
- Create lessons
- Upload lesson materials
- View own lessons and materials

### TEACHER
- All STUDENT permissions
- Update own lessons
- Delete own lessons
- Update/delete own materials

### ADMIN
- All permissions
- Can manage all users, lessons, and materials

## Rate Limiting

Currently, there is no rate limiting implemented. Future versions will include rate limiting per user.

## Pagination

Currently, pagination is not implemented. Future versions will support pagination for list endpoints.

## Filtering and Sorting

Currently, filtering and sorting are not implemented. Future versions will support these features.

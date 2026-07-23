# 🚀 Task Flow API

<div align="center">

# 📋 Task Flow API

### Secure Task Management REST API built with Spring Boot

<p align="center">

<img src="https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=openjdk&logoColor=white"/>

<img src="https://img.shields.io/badge/Spring_Boot-3.5-green?style=for-the-badge&logo=springboot"/>

<img src="https://img.shields.io/badge/Spring_Security-6-green?style=for-the-badge&logo=springsecurity"/>

<img src="https://img.shields.io/badge/JWT-Authentication-orange?style=for-the-badge"/>

<img src="https://img.shields.io/badge/PostgreSQL-Neon-blue?style=for-the-badge&logo=postgresql"/>

<img src="https://img.shields.io/badge/Railway-Deployed-purple?style=for-the-badge&logo=railway"/>

<img src="https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven"/>

<img src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger"/>

<img src="https://img.shields.io/badge/REST-API-success?style=for-the-badge"/>

<img src="https://img.shields.io/badge/OAuth2-Google-blue?style=for-the-badge&logo=google"/>

</p>

</div>

---

# 📖 Overview

Task Flow API is a production-ready **Task Management REST API** built using **Spring Boot**.

The application allows **Admins** to manage projects, tasks and users while authenticated **Users** can securely manage their assigned work.

The project follows a layered architecture with secure authentication using **JWT Access Token + Refresh Token**, Role-Based Authorization using Spring Security and clean REST API design.

This project is designed with a backend-first architecture. A React frontend can be integrated in the future to transform it into a complete production-ready Full Stack Task Management Application.

It is deployed on **Railway** with **Neon PostgreSQL** database.

---

# 🌐 Live Demo

## 🚀API Base URL

https://task-flow-api-production-9465.up.railway.app

---

## 📚 Swagger UI

https://task-flow-api-production-9465.up.railway.app/swagger-ui/index.html

---

# ✨ Features

## 🔐 Authentication

- User Registration
- Secure Login
- JWT Authentication
- Refresh Token
- Logout
- BCrypt Password Encryption
- Google OAuth2 Login
---

## 👥 User Management

- Get All Users (Admin)
- Get User By ID (Admin)

---

## 📁 Project Management

- Create Project
- Update Project
- Delete Project
- Get All Projects
- Get Project By ID
- Search Projects
- Pagination
- Sorting

---

## ✅ Task Management

- Assign Task
- Update Task
- Delete Task
- Get All Tasks
- Get Task By ID
- Search Tasks
- Update Task Status
- 📧 Automatic Email Notification when a task is assigned to a user.
---

## 💬 Comment Management

- Add Comment
- Delete Comment
- Get All Comments
- Pagination Support

Business Logic

- Admin can view all comments.
- Users can only view their own comments.
- Admin can delete any comment.
- Users can delete only their own comments.

---

## 🔒 Security

- Spring Security
- JWT Authentication
- Refresh Token Authentication
- Role-Based Authorization
- Method Level Security using `@PreAuthorize`
- Google OAuth2 Login
---

# 🛠 Tech Stack

| Technology | Used |
|------------|------|
| Java 21 | ✅ |
| Spring Boot | ✅ |
| Spring Security | ✅ |
| OAuth2 |  ✅ |
| Spring Mail | ✅ |
| Spring Data JPA | ✅ |
| Hibernate | ✅ |
| PostgreSQL (Neon) | ✅ |
| JWT | ✅ |
| Maven | ✅ |
| Railway | ✅ |
| Swagger OpenAPI | ✅ |
| Lombok | ✅ |
| Bean Validation | ✅ |

---

# 🧩 Architecture

```
Controller
      ↓
Service
      ↓
Repository
      ↓
PostgreSQL Database
```

---

# 🔑 Authorization

The project uses Role Based Access Control.

| Role | Permissions |
|-------|-------------|
| ADMIN | Full Access |
| USER | Limited Access |

Authentication is handled using JWT Bearer Token.

All secured APIs require:

```text
Authorization: Bearer YOUR_ACCESS_TOKEN
```

# 📡 REST API Documentation

## 🔐 Authentication APIs

| Method | Endpoint | Access | Description |
|---------|----------|--------|-------------|
| POST | `/auth/register` | Public | Register a new user |
| POST | `/auth/login` | Public | Login and receive Access Token + Refresh Token |
| POST | `/auth/refresh` | Public | Generate a new Access Token using Refresh Token |
| POST | `/auth/logout` | Authenticated | Logout by invalidating Refresh Token |

---

## 👥 User APIs

| Method | Endpoint | Access | Description |
|---------|----------|--------|-------------|
| GET | `/users` | ADMIN | Get all registered users |
| GET | `/users/{id}` | ADMIN | Get user details by ID |

---

## 📁 Project APIs

| Method | Endpoint | Access | Description |
|---------|----------|--------|-------------|
| POST | `/projects` | ADMIN | Create a new project |
| GET | `/projects` | ADMIN, USER | Get all projects |
| GET | `/projects/{id}` | ADMIN, USER | Get project by ID |
| PUT | `/projects/{id}` | ADMIN | Update project |
| DELETE | `/projects/{id}` | ADMIN | Delete project |
| GET | `/projects/all?page=0&size=5&sortBy=projectName` | ADMIN, USER | Pagination & Sorting |
| GET | `/projects/search?keyword=java` | ADMIN, USER | Search projects |

---

## ✅ Task APIs

| Method | Endpoint | Access | Description |
|---------|----------|--------|-------------|
| POST | `/tasks/assign` | ADMIN | Assign task to user |
| GET | `/tasks` | ADMIN, USER | Get all tasks |
| GET | `/tasks/{id}` | ADMIN, USER | Get task by ID |
| PUT | `/tasks/{id}` | ADMIN | Update task |
| DELETE | `/tasks/{id}` | ADMIN | Delete task |
| PATCH | `/tasks/{id}/status` | ADMIN, USER | Update task status |
| GET | `/tasks/search?keyword=bug` | ADMIN, USER | Search tasks |

---

## 💬 Comment APIs

| Method | Endpoint | Access | Description |
|---------|----------|--------|-------------|
| POST | `/comments` | ADMIN, USER | Add comment |
| GET | `/comments` | ADMIN, USER | Get comments (Role Based) |
| GET | `/comments/all?page=0&size=10` | ADMIN, USER | Paginated comments |
| DELETE | `/comments/{id}` | ADMIN, USER | Delete own comment or any comment (Admin) |

---

# 🔒 API Security

Every protected endpoint uses

- JWT Bearer Authentication
- Spring Security
- Method Level Authorization using `@PreAuthorize`

---

## Authorization Rules

### 👑 ADMIN

- Full access to all APIs
- Create / Update / Delete Projects
- Assign Tasks
- Manage Users
- View every Comment
- Delete every Comment

---

### 👤 USER

- Login
- Refresh Token
- Logout
- View Projects
- View Tasks
- Update Task Status
- Add Comments
- View only their own Comments
- Delete only their own Comments
- 
---

# 🛡 Security Features

- JWT Access Token Authentication
- Refresh Token Authentication
- BCrypt Password Encryption
- Role Based Authorization
- Spring Security Filters
- Custom UserDetails
- Authentication Entry Point
- Global Exception Handling
- Bean Validation
- Protected REST Endpoints
- Google OAuth2 Authentication
---

# 📂 Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 │     ├── request
 │     └── response
 ├── entity
 ├── enums
 ├── exception
 ├── repository
 ├── security
 ├── service
 │     ├── impl
 ├── util
 └── TaskFlowApiApplication
```

---

# 🗄 Database

The application uses **Neon PostgreSQL**.

Main Tables

- users
- refresh_tokens
- projects
- tasks
- comments

Relationships

```
User
 │
 ├──────< Project
 │
 ├──────< Task
 │
 └──────< Comment

Task
 │
 └──────< Comment
```

---

# 🔄 Request Flow

```
Client

   │

   ▼

Controller

   │

   ▼

Service

   │

   ▼

Repository

   │

   ▼

PostgreSQL Database
```

---

# 🧪 API Testing

The API has been tested using:

- Swagger UI
- Browser (Swagger UI)
- Postman

All secured endpoints require a valid JWT Bearer Token.

---

# 📈 Production Deployment

| Service | Status |
|----------|--------|
| Railway Deployment | ✅ |
| Neon PostgreSQL | ✅ |
| Swagger UI | ✅ |
| JWT Security | ✅ |
| Refresh Token | ✅ |
| REST APIs | ✅ |
| Role Based Authorization | ✅ |
---

# ⚙️ Getting Started

## 1️⃣ Clone Repository

```bash
git clone https://github.com/jeevan-kaware/task-flow-api.git
```

```bash
cd task-flow-api
```

---

## 2️⃣ Configure Database

Create your PostgreSQL database (Neon or Local PostgreSQL).

Update your `application.properties`

```properties
spring.datasource.url=<YOUR_POSTGRESQL_DATABASE_URL>
spring.datasource.username=<YOUR_POSTGRESQL_USERNAME>
spring.datasource.password=<YOUR_POSTGRESQL_PASSWORD>
```

---

## 3️⃣ JWT Configuration

```properties
jwt.secret=<YOUR_SECRET_KEY>
jwt.expiration=86400000
jwt.refresh-expiration=604800000
```

---

## 4️⃣ Run the Project

Using Maven

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

# 📖 Open Swagger

```
http://localhost:8080/swagger-ui/index.html
```

Production

```
https://task-flow-api-production-9465.up.railway.app/swagger-ui/index.html
```

---

# 🌍 Live Deployment

| Service | URL |
|---------|-----|
| Railway | https://task-flow-api-production-9465.up.railway.app |
| Swagger UI | https://task-flow-api-production-9465.up.railway.app/swagger-ui/index.html |

---

# 📸 Screenshots

All screenshots were captured while testing the API using Swagger UI and demonstrate the major features of the application.

---

## 🔐 User Registration

![Register API](Screenshots/01-register-api.png)

---

## 🔑 Login & JWT Authentication

![Login API](Screenshots/02-login-api.png)

---

## 🌐 Google OAuth2 Login

![Google OAuth2](Screenshots/05-google-oauth-login.png)

---

## 📁 Create Project

![Create Project](Screenshots/06-create-project.png)

---

## 📋 Get All Projects

![Projects](Screenshots/07-get-all-projects.png)

---

## ✅ Assign Task

![Assign Task](Screenshots/10-assign-task.png)

---

## 📝 Task Management

![Tasks](Screenshots/11-get-all-tasks.png)

---

## 💬 Comment Management

![Comments](Screenshots/16-get-comments.png)

---

## 📧 Email Notification

![Task Assignment Email](Screenshots/19-task-assignment-email.png)

---

## 📖 Swagger UI Documentation

![Swagger UI](Screenshots/18-swagger-ui.png)

---

## 🗄 PostgreSQL Database

![Database](Screenshots/20-postgresql-tables.png)

---

## 🔐 Google OAuth2 User Interface

![Google OAuth2 UI](Screenshots/21-google-oauth-ui.png)

# 🚀 Future Improvements

- React Frontend
- Docker Support
- Unit Testing
- Integration Testing
- Redis Cache
- CI/CD Pipeline
- API Rate Limiting
- Audit Logging
- Dashboard Analytics
---

# 💡 Learning Outcomes

This project helped me gain practical experience with

- Spring Boot
- Spring Security
- JWT Authentication
- Refresh Token Flow
- Google OAuth2
- Role-Based Authorization
- REST API Development
- PostgreSQL
- Spring Data JPA
- Hibernate
- Maven
- Swagger OpenAPI
- Railway Deployment
- Neon PostgreSQL
- Exception Handling
- Bean Validation
- Clean Layered Architecture

---

# 👨‍💻 Author

**Jeevan Kaware**

Java Backend Developer

GitHub:
https://github.com/jeevan-kaware/task-flow-api

LinkedIn:
https://www.linkedin.com/in/jeevan-kaware-080643355

Portfolio:
https://smart-portfolio-kappa-eight.vercel.app/

---

# ⭐ If you like this project

If you found this project useful, consider giving it a ⭐ to support my work.

It motivates me to build more production-ready backend projects.

---

<div align="center">

### 🚀 Built with Java, Spring Boot, Spring Security, PostgreSQL, OAuth2, JWT and ❤️

**Thank you for visiting this repository.**

</div>

#  Finance Management System (Spring Boot + JWT)

##  Overview
This is a backend Finance Management System built using Spring Boot.  
It allows users to manage income, expenses, budgets, and view analytics securely using JWT authentication.

---

##  Architecture

The project follows a **layered architecture**:

Controller → Service → Repository → Database

###  Layers:

- ### Controller Layer
  - Handles HTTP requests
  
  - **UserController**  
  Handles user-related APIs such as creating users, updating budget, and fetching user details.

- **RecordController**  
  Manages income and expense operations including adding records, viewing records, and exporting data.

- **DashboardController**  
  Provides analytics endpoints like total income, total expenses, balance, and budget status.

- **AuthController**  
  Handles authentication by validating user credentials and generating JWT tokens.

- **Service Layer**
  - Contains business logic
  - Example: UserService, DashboardService , RecordService , AuthService , CustomUserDetailsService

- **Repository Layer**
  - Handles database operations using JPA
  - Example: UserRepository, RecordRepository , RoleRepository

- **Security Layer**
  - JWT authentication & authorization
  - JwtFilter, JwtUtil, SecurityConfig

- **DTO Layer**
  - Data transfer between client and server
  - LoginRequestDTO, DashboardResponseDTO , LoginResponseDTO , RecordDTO , UserRequestDTO , UserResponseDTO , BudgetDTO

- **Mapper**
- RecordMapper , UserMapper
---

##  Tech Stack

| Technology | Usage |
|----------|------|
| Java 17 | Core language |
| Spring Boot | Backend framework |
| Spring Security | Authentication & Authorization |
| JWT (jjwt) | Token-based security |
| Spring Data JPA | Database operations |
| PostgreSQL | Database |
| Maven | Dependency management |

---

##  Security (JWT)

- User logs in via `/auth/login`
- Server generates JWT token
- Token is sent in headers:


- JwtFilter validates token on every request
- Role-based access control is applied

---

##  Features

###  User Management
- Create user
- Assign roles (ADMIN, ANALYST, VIEWER)

###  Records Management
- Add income/expense
- Categorize transactions

###  Dashboard
- Total income
- Total expense
- Balance

###  Budget Tracking
- Set monthly budget
- Warning when exceeded

### Export Data
- Export records to CSV

###  Exception Handling
- Clean error responses using GlobalExceptionHandler

---

##  How System Works

1. User registers / created by admin
2. User logs in → receives JWT token
3. Token is used in every request
4. JwtFilter validates token
5. Request reaches controller → service → database
6. Response sent back to client

---


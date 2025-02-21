# e-Library

## Description
The **e-Library** is a Spring Boot-based project that provides book management, secure lending and tracking of online books. It supports Role-Based Access Control (RBAC) with two authentication mechanisms:
1. **JWT Authentication** - Uses JSON Web Tokens for secure user authentication and role management.
2. **OAuth2 GitHub Authentication** - Allows users to authenticate via GitHub and assigns roles accordingly.

The system includes features such as caching with Redis, issuing and managing books, and tracking membership details.

---

## Features
- **Book Issuing & Management**: Add, fetch, and manage books.
- **User Authentication & Authorization**:
    - JWT-based authentication and authorization.
    - OAuth2 authentication via GitHub.
    - Role-Based Access Control (RBAC) using Spring Security.
- **Caching**: Redis is used to improve performance by caching book data.
- **Membership Management**: Add members, fetch members data based on role etc.
- **Update Book Issue Status**: Change issue status of a member when their book access expires.
- **Unit Testing**: Includes unit tests for book services and member controllers.

---

## Technologies Used
- **Spring Boot** (Backend framework)
- **Spring Security** (Authentication and RBAC implementation)
- **JWT (JSON Web Token)** (Token-based authentication)
- **OAuth2** (GitHub authentication)
- **Redis** (Caching mechanism)
- **JPA & Hibernate** (Database interaction)
- **PostgreSQL (Database)** 
- **JUnit & Mockito** (Unit testing framework)

---

## Installation & Setup

### Prerequisites
- Java 17+
- Maven
- Redis (For caching functionality)
- PostgreSQL (Database)

---

## API Endpoints

### Authentication
- **Login with JWT:**
  ```sh
  POST /auth/login
  ```
    - **Body:**
      ```json
      {
        "username": "manas",
        "password": "password"
      }
      ```
- **GitHub OAuth2 Login:**
  ```sh
  GET /oauth2/authorization/github
  ```
- **Access Secure Endpoints with JWT:**
    - Include `Authorization: Bearer <token>` in the request header.

### Book Management
- **Add a Book:**
  ```sh
  POST /book/add
  ```
    - **Body:**
      ```json
        {
            "name": "The Hobbit",
            "author": "J.R.R. Tolkien",
            "isbn": "72384",
            "price": 775.0,
            "description": "A fantasy adventure about Bilbo Baggins, a hobbit who embarks on an unexpected journey with a group of dwarves to reclaim a treasure from a dragon.",
            "category": "FICTION"
        }
      ```
- **Fetch All Books:**
  ```sh
  GET /book/list
  ```
    - **Headers:** `Authorization: Bearer password...`
- **Fetch a Specific Book by ID:**
  ```sh
  GET /book/{bookId}
  ```
    - Example:
      ```sh
      GET /book/3f1b0c80-87fd-44de-8b4b-0dcf37f1091d
      ```

### Membership Management
- **Add a Member:**
  ```sh
  POST /member/add
  ```
    - **Body:**
      ```json
      {
        "firstName": "Manas",
        "lastName": "Chougule",
        "username": "manas",
        "password": "password",
        "role": "LIBRARIAN",
        "mobileNumber": "1234567890",
        "email": "manaschougule2019@gmail.com"
      }
      ```
- **Fetch All Members:**
  ```sh
  GET /member/list
  ```
    - **Headers:** `Authorization: Bearer password...`
- **Fetch a Member by ID:**
  ```sh
  GET /member/?memberId={memberId}
  ```

### Book Issuing
- **Create an Issue Record:**
  ```sh
  POST /issue_data
  ```
    - **Body:**
      ```json
      {
        "memberId": "cbbf8134-231f-4acd-9f26-dbba62edd92f",
        "bookId": "3f1b0c80-87fd-44de-8b4b-0dcf37f1091d"
      }
      ```
- **Fetch Issue Records for a Specific Member:**
  ```sh
  GET /issue_data?memberId={memberId}
  ```
- **Change Issue Status for a Member:**
  ```sh
  PUT /change_issue_status/{memberId}
  ```
    - This endpoint updates the issue status to `false`, indicating that the book is no longer issued to the user after its expiration date.

---

### Steps to Run
1. Clone the repository:
   ```sh
   git clone https://github.com/ManasChougule475/E-Library
   cd e-library
   ```
2. Configure the application:
    - Modify `application.properties` to set up the database and Redis connection.
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. Access the API at: `http://localhost:8080`


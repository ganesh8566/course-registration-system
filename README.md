# 📚 Student Course Registration System

A full-stack web application that allows students to register for academic courses and enables administrators to manage course offerings. Built using Java, Spring Boot, Spring Security, MySQL, and Thymeleaf.

---

## 🚀 Features

### 👨‍🎓 Student Module
- 🔐 Student Registration and Login
- 📋 View available courses
- ✅ Register for desired courses
- ❌ Drop registered courses
- 📄 View registered courses summary

### 🧑‍💼 Admin Module
- 🔐 Admin Login
- ➕ Add, update, or delete courses
- 📊 View list of registered students
- 📈 Manage and monitor course enrollments

### 🔒 Authentication & Authorization
- Spring Security configured with **JWT tokens**
- Role-based access control for `STUDENT` and `ADMIN`
- Encrypted password storage with BCrypt

---

## 📂 Project Structure
com.example.StudentCourseRegistration
├── controller        → REST API endpoints (e.g., StudentController, AdminController)
├── entity            → JPA entity classes (Student, Course, Admin, Registration)
├── repository        → Spring Data JPA interfaces for database access
├── service           → Business logic and service implementations
├── dto               → Data Transfer Objects for requests and responses
├── config            → Spring Security and application configuration
└── utils             → Utility classes (e.g., JWT token provider, exception handlers)

---

## 🛠 Tech Stack

- **Backend:** Java 17+, Spring Boot, Spring Security, Spring Data JPA
- **Database:** MySQL
- **Frontend:** Thymeleaf, HTML5, CSS3, Bootstrap 5
- **Security:** JWT Authentication, BCrypt password hashing
- **Testing & Tools:** Postman, JUnit, Git & GitHub, Maven, Eclipse

---

## 🔧 Setup & Run Locally

1. **Clone the Repository**
   ```bash
   git clone https://github.com/ganesh8566/student-course-registration.git
   cd student-course-registration
   
2.Configure Database
- Create a database named student_course_registration in MySQL.
- Update src/main/resources/application.properties with your DB credentials.
  
3.Run the Application
./mvnw spring-boot:run

4.Test API in Postman
- Register/Login as Student or Admin
- Access secured endpoints using JWT token




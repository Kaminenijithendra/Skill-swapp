# 🚀 Skill Swap App

A full-stack **Skill Exchange Platform** where users can teach and learn skills by connecting with each other.

---

## 📌 Features

* 🔐 User Authentication (Register, Login, JWT)
* 📩 OTP Verification
* 👤 Profile Management
* 🧠 Skill Matching System
* 🔁 Swap Request Workflow
* 💬 Chat System
* ⭐ Review & Rating System

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* Hibernate / JPA
* Oracle Database

### Frontend (Planned / Optional)

* React.js
* Tailwind CSS / Bootstrap

---

## 📂 Project Structure

```text
com.skillswap
├── auth
├── user
├── skill
├── match
├── swap
├── chat
├── review
```

---

## 🔑 API Endpoints

### Auth

* POST /api/auth/register
* POST /api/auth/verify-otp
* POST /api/auth/login

### User

* GET /api/users/me
* PUT /api/users/me

### Skills

* POST /api/skills/offered
* POST /api/skills/wanted

### Match

* GET /api/matches

### Swap

* POST /api/swaps/request
* PUT /api/swaps/{id}/accept
* PUT /api/swaps/{id}/reject

### Chat

* POST /api/chat/send
* GET /api/chat/{userId}

### Review

* POST /api/reviews
* GET /api/reviews/{userId}

---

## ⚙️ Setup Instructions

### 1. Clone repository

```bash
git clone https://github.com/YOUR_USERNAME/skill-swap-app.git
cd skill-swap-app
```

---

### 2. Configure Database (Oracle)

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/ORCL
spring.datasource.username=system
spring.datasource.password=system123
```

---

### 3. Run Application

```bash
mvn spring-boot:run
```

---

## 🔐 Authentication Flow

1. Register user
2. Receive OTP (console)
3. Verify OTP
4. Login → Get JWT token
5. Use token for secured APIs

---

## 🧪 Testing

Use **Postman** to test APIs.

---

## 📌 Future Enhancements

* WebSocket real-time chat
* Email OTP integration
* Profile image upload
* AI-based skill recommendation
* Admin dashboard

---

## 👨‍💻 Author

**Jithendra K**

* GitHub: https://github.com/Kaminenijithendra

---

## ⭐ If you like this project

Give a ⭐ on GitHub

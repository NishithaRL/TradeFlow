# 🚀 TradeFlow – Event-Driven Trade Finance System

TradeFlow is a backend system that simulates real-world **Trade Finance workflows** using an **event-driven microservices architecture**.  
It demonstrates how trades are created, processed, and settled asynchronously using **Apache Kafka**.

---

## 📌 Features

- ✅ Trade creation via REST API
- ✅ Event-driven architecture using Kafka
- ✅ Asynchronous trade settlement simulation
- ✅ MySQL database integration
- ✅ JWT-based authentication & authorization
- ✅ Swagger API documentation
- ✅ Fully containerized using Docker
- ✅ Deployed on AWS EC2

---

## 🏗️ Architecture

```
User → Swagger UI → Trade Service → MySQL
                    ↓
             Kafka Producer → Topic (trade-created)
                    ↓
             Kafka Consumer → Settlement Processing → DB Update
```

---

## 🧠 Tech Stack

- **Backend:** Spring Boot
- **Messaging:** Apache Kafka (KRaft mode)
- **Database:** MySQL
- **Security:** JWT Authentication
- **Containerization:** Docker & Docker Compose
- **API Docs:** Swagger (OpenAPI)
- **Deployment:** AWS EC2

---

## ⚙️ How It Works

1. User creates a trade via API  
2. Trade is stored in MySQL  
3. Event is published to Kafka (`trade-created`)  
4. Kafka consumer listens to the event  
5. Settlement is simulated asynchronously  
6. Trade status is updated to `SETTLED`  

---

## 📂 Project Structure

```
trade-service/
├── src/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
```

---

## 🐳 Run Locally (Docker)

### 1️⃣ Build the project

```bash
mvn clean package
```

### 2️⃣ Run containers

```bash
docker compose up -d --build
```

### 3️⃣ Access services

- Swagger UI:  
  http://localhost:8080/swagger-ui/index.html  

- Kafka UI:  
  http://localhost:8081  

---

## ☁️ Deployment (AWS EC2)

1. Launch EC2 instance (Ubuntu)  
2. Install Docker & Docker Compose  
3. Clone repository:

```bash
git clone https://github.com/YASH-VYAS711/TradeFlow.git
cd TradeFlow
```

4. Run:

```bash
docker compose up -d --build
```

---

## 🔐 Authentication

- JWT-based authentication is implemented  
- Public endpoints:  
  - `/auth/**`  
  - `/swagger-ui/**`  
- Other APIs require token  

---

## 📈 Future Enhancements

- Multi-service architecture (trade-service, settlement-service)  
- Notification service  
- Retry & failure handling  
- Kafka partitions & scaling  
- CI/CD pipeline (GitHub Actions)  

---

## 🎯 Key Highlights

- Real-world **trade finance simulation**  
- Demonstrates **event-driven architecture**  
- Production-like setup with Docker & AWS  
- Strong backend project for interviews  

---

## 👨‍💻 Author

**Nishitha R Lokande**

---

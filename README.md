✅ Stock Portfolio Monitoring App (Spring Boot Backend)
This project is a backend system for managing and monitoring stock investments. It allows users to manage portfolios, track real-time stock prices (from a database), calculate gains/losses, set alerts, and generate reports

🎯 Objective
To build a secure and efficient backend platform where users can:
Upload and manage stock holdings

Receive alerts for custom price thresholds or portfolio losses


View real-time stock prices (via static API)


Track gains/losses for each holding and overall portfolio



📁 Core Modules
✅ 1. User Management Module
User registration
User Login


✅ 2. Portfolio Module
Create and manage multiple portfolios
Add/edit/delete stock holdings (symbol, quantity, buy price)


✅ 3. Real-Time Price Fetcher
Integrates with external static stock API

Scheduled or manual fetching of latest prices


✅ 4. Alerting Module
Users can set alerts for:
Stock price crossing a threshold
Portfolio loss exceeding a set percentage
Modular notification system (database logs)


✅ 5. Gain/Loss Calculator
Calculates:

Per-stock gain/loss (absolute and percentage)
Total portfolio performance



⚙️ Tech Stack
Layer
Technology
Language
Java
Framework
Spring Boot, Spring Data JPA, Spring Security
DB
MySQL
Scheduling
Spring Scheduler / Quartz
REST Client
RestTemplate
JSON
Jackson
Build Tool
Maven
Testing
JUnit, Mockito


🧠 Business Logic Flow
✔ Price Fetch Job
Runs on schedule (every 5 minutes)

Steps:
Retrieve all stock symbols in use
Call third-party API to fetch prices
Save to a db for access


✔ Gain/Loss Calculation
For each holding:

 gain = (currentPrice - buyPrice) * quantity  
percentageGain = (gain / (buyPrice * quantity)) * 100


✔ Alert Evaluation
Compares current stock price against user-defined conditions


If triggered:

Add into DB, and show in console Logs.


🧪 Testing Plan
Type
Tool
Focus Areas
Unit Testing
JUnit, Mockito
Controllers, Services, Utils
Spring Boot
REST APIs, DB interactions
Mock Testing
Mockito
External API (stock price) mocks
Alert Testing



📤 Sample API Endpoints
🔐 AuthController
POST /user/register – User registration
PUT /user/updateProfile/{email} - Update User Profile
POST /user/login – User Login


📦 PortfolioController
GET /portfolios – View all portfolios
POST /portfolios/{userid}  - Create User Portfolios 
GET /portfolios/{userid} – View user portfolio  


📈 HoldingsController
POST /api/holdings – Add new stock
PUT /api/holdings/{id} – Update stock info
DELETE /api/holdings/{id} – Remove a stock
GET /api/holdings - Get all holdings which user bought
GET /api/holdings/transactions - Get all user transactions


🔔 AlertsController
POST /api/alerts – Set up an alert
GET /api/alerts – View all alerts



🗃 Example Entity Overview
User: id, username, email, password

Portfolio: id, userId, name, portfolioName

Holding: id, portfolioId, symbol, quantity, buyPrice

Alert: id, userId, symbol, threshold, isActive

PriceCache: symbol, currentPrice, timestamp



🗂 Project Structure
Stock-Portfolio-Monitoring-App/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── stockPortfolio/
│   │   │               ├── StockPortfolioApplication.java          # Main Spring Boot Application
│   │   │               ├── AlertManagement/                       # Manages stock alerts
│   │   │               ├── ExceptionManagement/                   # Global exception handling
│   │   │               ├── HoldingsManagement/                    # Handles stock holdings and transactions
│   │   │               ├── PortfolioManagement/                   # Portfolio creation and management
│   │   │               └── UserManagement/                        # User registration and authentication
│   │   └── resources/
│   │       └── application.properties                             # Application configuration
│
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── stockPortfolio/
│                       ├── AlertManagementTest/                   # Tests for AlertManagement module
│                       ├── HoldingsManagementTest/                # Tests for HoldingsManagement module
│                       ├── PortfolioManagementTest/               # Tests for PortfolioManagement module
│                       └── UserManagementTest/                    # Tests for UserManagement module




▶️ How to Run the Project
🛠 Prerequisites
Java 17+


Maven

MySQL

Postman or Swagger UI (for API testing)


🚀 Setup Steps
Clone the repo:

git clone https://github.com/123codingmsk/Stock-Portfolio-Monitoring-App.git
cd Stock-Portfolio-Monitoring-App

Create MySQL DB:

 CREATE DATABASE stocksdb;

Edit application.properties:

setup your own local database.

#spring.datasource.url=jdbc:mysql://192.168.110.230/stocksdb    -->mine
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html


Build and run:

 ./mvnw clean install
./mvnw spring-boot:run


Access API Docs:
 Visit: http://localhost:8080/swagger-ui/index.html



👥 Authors
Name
Role & Contributions
Tean Member 1  (A. Manoj Sai Kumar)
Project Lead, Contributed in every module

Team Member 2 (Meenakshi Gayatri)
Contributed in every module

Team Member 3 (Niladri Sen)
Contributed in every module

Team Member 4 (Vibusha)
Contributed in every module

Team Member 4 (Devansh Dubey)
Contributed in every module









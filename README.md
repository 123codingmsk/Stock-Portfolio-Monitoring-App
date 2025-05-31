# ✅ Stock Portfolio Monitoring App (Spring Boot Backend)

This project is a backend system for managing and monitoring stock investments. It allows users to manage portfolios, track real-time stock prices (from a database), calculate gains/losses, set alerts, and generate reports.

---

## 🎯 Objective

To build a secure and efficient backend platform where users can:

- ✅ Upload and manage stock holdings  
- ✅ Receive alerts for custom price thresholds or portfolio losses  
- ✅ View real-time stock prices (via static API)  
- ✅ Track gains/losses for each holding and overall portfolio  

---

## 📁 Core Modules

### ✅ 1. User Management Module
- User registration  
- User login  

### ✅ 2. Portfolio Module
- Create and manage multiple portfolios  
- Add/edit/delete stock holdings (symbol, quantity, buy price)  

### ✅ 3. Real-Time Price Fetcher
- Integrates with external static stock API  
- Scheduled or manual fetching of latest prices  

### ✅ 4. Alerting Module
Users can set alerts for:
- Stock price crossing a threshold  
- Portfolio loss exceeding a set percentage  
- Modular notification system (database logs)  

### ✅ 5. Gain/Loss Calculator
Calculates:
- Per-stock gain/loss (absolute and percentage)  
- Total portfolio performance  

---

## ⚙️ Tech Stack

| Layer         | Technology                        |
|---------------|-----------------------------------|
| Language       | Java                              |
| Framework      | Spring Boot, Spring Data JPA, Spring Security |
| Database       | MySQL                             |
| Scheduling     | Spring Scheduler / Quartz         |
| REST Client    | RestTemplate                      |
| JSON Mapper    | Jackson                           |
| Build Tool     | Maven                             |
| Testing        | JUnit, Mockito                    |

---

## 🧠 Business Logic Flow

### ✔ Price Fetch Job
Runs on schedule (every 5 minutes)

**Steps:**
- Retrieve all stock symbols in use  
- Call third-party API to fetch prices  
- Save to DB for access  

### ✔ Gain/Loss Calculation
For each holding:

```text
gain = (currentPrice - buyPrice) * quantity  
percentageGain = (gain / (buyPrice * quantity)) * 100

## ✔️ Alert Evaluation

Compares current stock price against user-defined conditions.

- ✅ If triggered: Adds into DB and logs alert to console.

---

## 🧪 Testing Plan

| Type          | Tool           | Focus Areas                                 |
|---------------|----------------|---------------------------------------------|
| Unit Testing  | JUnit, Mockito | Controllers, Services, Utils                |
| Spring Boot   | Built-in       | REST APIs, DB interactions                  |
| Mock Testing  | Mockito        | External API (stock price) mocks            |
| Alert Testing | Custom/Unit    | Alert triggers, logging                     |

---

## 📤 Sample API Endpoints

### 🔐 AuthController
- `POST /user/register` – User registration  
- `PUT /user/updateProfile/{email}` – Update user profile  
- `POST /user/login` – User login  

### 📦 PortfolioController
- `GET /portfolios` – View all portfolios  
- `POST /portfolios/{userid}` – Create user portfolios  
- `GET /portfolios/{userid}` – View user portfolio  

### 📈 HoldingsController
- `POST /api/holdings` – Add new stock  
- `PUT /api/holdings/{id}` – Update stock info  
- `DELETE /api/holdings/{id}` – Remove a stock  
- `GET /api/holdings` – Get all holdings bought by user  
- `GET /api/holdings/transactions` – Get all user transactions  

### 🔔 AlertsController
- `POST /api/alerts` – Set up an alert  
- `GET /api/alerts` – View all alerts  

---

## 🗃 Example Entity Overview

| Entity     | Fields                                           |
|------------|--------------------------------------------------|
| **User**   | `id`, `username`, `email`, `password`            |
| **Portfolio** | `id`, `userId`, `portfolioName`              |
| **Holding**   | `id`, `portfolioId`, `symbol`, `quantity`, `buyPrice` |
| **Alert**     | `id`, `userId`, `symbol`, `threshold`, `isActive`     |
| **PriceCache**| `symbol`, `currentPrice`, `timestamp`        |

---

## 🗂 Project Structure
Stock-Portfolio-Monitoring-App/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/stockPortfolio/
│   │   │       ├── StockPortfolioApplication.java       # Main Spring Boot Application
│   │   │       ├── AlertManagement/                    # Manages stock alerts
│   │   │       ├── ExceptionManagement/                # Global exception handling
│   │   │       ├── HoldingsManagement/                 # Stock holdings and transactions
│   │   │       ├── PortfolioManagement/                # Portfolio management
│   │   │       └── UserManagement/                     # User operations (auth, registration)
│   └── resources/
│       └── application.properties                      # App configuration
│
└── test/
    └── java/
        └── com/example/stockPortfolio/
            ├── AlertManagementTest/                   # Alert module tests
            ├── HoldingsManagementTest/                # Holdings module tests
            ├── PortfolioManagementTest/               # Portfolio module tests
            └── UserManagementTest/                    # User module tests

---

## ▶️ How to Run the Project

### 🛠 Prerequisites

- Java 17+  
- Maven  
- MySQL  
- Postman or Swagger UI (for testing)

### 🚀 Setup Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/123codingmsk/Stock-Portfolio-Monitoring-App.git
   cd Stock-Portfolio-Monitoring-App


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

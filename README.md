# 🏛️ JFXGTDS – JavaFX Government Tax Department System

## 📌 Overview

JFXGTDS is a JavaFX-based desktop application that simulates a simplified
Government Tax Department System.

The system supports transaction imports, validation, profit analysis, and tax calculation using a clean MVC architecture and FXML-driven UI.

---

## ✨ Features
### 🔐 Authentication

- Role-based login (Admin / User)
- Invalid login feedback

### 📂 Transaction Management

- CSV import support
- Edit transaction records
- Automatic checksum validation
- Invalid transaction detection

### 📊 Tax & Profit Calculation

- Configurable tax rate
- Per-transaction profit calculation
- Total profit and tax computation

### 🖥️ JavaFX UI

- Multi-screen JavaFX application
- FXML-based layouts
- Controller-driven logic

---

## 🗂️ Project Structure

```text
JFXGTDS/
│
├── src/main/java/com/iit/tutorials/jfxgtds
│   ├── 🚀 MainApplication.java
│   ├── 🔑 LoginController.java
│   ├── 📋 MainController.java
│   ├── ✏️ EditController.java
│   ├── 💰 TaxController.java
│   ├── 📥 CSVImport.java
│   ├── 📦 Transaction.java
│   └── ✅ Validation.java
│
├── src/main/resources/com/iit/tutorials/jfxgtds
│   ├── 🧩 login-view.fxml
│   ├── 🧩 main-view.fxml
│   ├── 🧩 edit-view.fxml
│   └── 🧩 tax-view.fxml
│
├── ⚙️ pom.xml
└── 📄 README.md
```

## 🛠️ Technology Stack

- ☕ Java (JDK 11+)
- 🎨 JavaFX
- 🧱 FXML
- 📦 Maven
- 🧩 MVC Architecture

---

## 🔄 Application Flow

1. ▶️ Launch Application

  - Entry point: MainApplication.java

2. 🔐 Login

  - Credentials:
    - admin / admin
    - user / user

3. 📋 Main Dashboard

  - Import CSV
  - View & edit transactions
  - Navigate to tax calculation

4. ✅ Validation

  - Checksum verification
  - Price & item code validation

5. 💰 Tax Calculation

  - Enter tax rate
  - Compute profit & final tax

---

## 📄 CSV File Format

```text
billNumber,itemCode,internalPrice,discount,salePrice,quantity,checksum
```

**Example:**
```text
B001,ITEM01,100,5,120,2,15
```
>ℹ️ The first row is treated as a header.

---

## ▶️ How to Run
### ✅ Prerequisites

- Java JDK 11+
- Maven
- JavaFX configured

### ▶️ Run via Maven
```bash
git clone https://github.com/Chanthul4054/JFXGTDS.git
cd JFXGTDS
mvn clean javafx:run
```
💡 You can also run it directly from **IntelliJ IDEA** or **Eclipse**.

---

## 🧪 Validation Logic

- **🔢 Checksum**

  - Counts uppercase, lowercase, digits

- **🔤 Item Code**

  - Alphanumeric only

- 💲 Pricing

  - No negative values allowed
 
---

## 🚀 Future Enhancements

- 🗄️ Database integration
- 🔐 Advanced role management
- 📤 Export reports (PDF / Excel)
- 🎨 Improved UI styling
- 📊 Analytics dashboard

---

## 📜 License

**📘 Educational Project**  
Free to use for learning and academic purposes.

---

## 👤 Author

Chanthul4054  
🔗 GitHub: https://github.com/Chanthul4054

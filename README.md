# 📂 NRL Bag Management System (Refactored to Layered Architecture)

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/JavaFX-FF0000?style=for-the-badge&logo=javafx&logoColor=white" alt="JavaFX" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/Architecture-Layered_Strict-blue?style=for-the-badge" alt="Layered Architecture" />
</p>

This repository contains the completely refactored version of the **NRL Bag Management System** (initially developed as a Semester 1 Final Project). The monolithic code structure has been completely migrated into a industry-standard **Multi-Tier Layered Architecture** to achieve strict separation of concerns, high scalability, and clean code principles.

---

### 🏗️ Architectural Transformation

In this version, the tightly coupled legacy architecture was completely decoupled into standard operational tiers. Data mapping rules are enforced across these isolated components:

```text
  🌐 [JavaFX UI View]
          │
          ▼
  🎮 [Controller Layer]   --> Intercepts UI events and manages view states
          │
          ▼
  🧠 [Service Layer]      --> Executes business rules and validation constraints
          │
          ▼
  🗂️ [DAO Layer]          --> Handles raw SQL execution logic and CRUD operations
          │
          ▼
  🛢️ [MySQL Database]     --> Relational data persistence engine
```
* **Stateless Data Transfers (DTO):** All data moving across layers is encapsulated inside Data Transfer Objects (DTOs), ensuring that the database model layer remains completely hidden from the user interface.

---

### 💻 Core Modules Implemented

* **👤 Customer Management:** Layered CRUD pipeline for client registration and profile tracking.
* **📦 Bag Order Tracking:** Monitored workflows for production pipelines and customer assignments.
* **📊 Material Stock Management:** Live inventory metrics to prevent stockouts and raw material waste.
* **💰 Financial & Payment Handling:** Standardized ledger calculations for processing advances and outstanding balances.
* **📈 Report Generation:** Extracted clean structural datasets for automated business reporting.

---

### 💻 Package Workspace Distribution

This workspace alignment shows how the components are strictly decoupled within IntelliJ to maintain single-responsibility principles:

```text
📁 src/main/java/com/nrl/bagmanagement
│
├── 📁 controller   --> JavaFX UI event listeners and view bindings
├── 📁 service      --> Business logic contracts and validation layers
├── 📁 dao          --> Direct SQL execution logic (Data Access Objects)
└── 📁 dto          --> Stateless Data Transfer Objects isolating schema states
```
### 🛠️ Infrastructure Configuration

* **UI Engine:** JavaFX Framework
* **Core Language:** Java (JDK Runtime)
* **Database Engine:** MySQL Server Community Edition
* **Development Environment:** IntelliJ IDEA

---

### 👩‍💻 System Architect

* **Induni Palliyaguru** - Software Engineering Undergraduate
* **LinkedIn:** [Connect on LinkedIn](https://www.linkedin.com/in/induni-palliyaguru-6195a9371)
* **GitHub:** [@InduniPalliyaguru](https://github.com/InduniPalliyaguru)

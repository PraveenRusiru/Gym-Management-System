
🏋️ Gym Management System

📌 Project Description

The Gym Management System is a desktop-based application designed to help small and single-trainer gyms manage daily operations efficiently.
	•	Simplifies client management, workout tracking, nutrition monitoring, memberships, and payments
	•	Reduces administrative workload on gym owners
	•	Built using a layered architecture for clean code, scalability, and maintainability

⸻

🎯 Objectives
	•	Digitize gym operations for small and medium-sized gyms
	•	Reduce manual record keeping
	•	Enable single-trainer gym owners to manage clients efficiently
	•	Accurately track workouts, nutrition, memberships, and payments

⸻

🏗️ System Architecture (Layered Architecture)

The application follows a 4-layer architecture:

1️⃣ Presentation Layer
	•	JavaFX-based user interface
	•	FXML layouts designed using Scene Builder
	•	Handles user interactions and UI logic

2️⃣ Service / Business Logic Layer
	•	Contains business rules and validations
	•	Processes data before passing it to the DAO layer
	•	Acts as a bridge between UI and database layers

3️⃣ Data Access Layer (DAO)
	•	Uses JDBC for database operations
	•	Handles CRUD operations
	•	Separates database logic from business logic

4️⃣ Database Layer
	•	MySQL relational database
	•	Uses constraints, foreign keys, and normalization
	•	Securely stores client, workout, nutrition, and payment data

⸻

🚀 Features
	•	Client registration and profile management
	•	Workout plan creation and progress tracking
	•	Nutrition tracking and daily updates
	•	Membership and payment management
	•	Progress tracking and report generation
	•	Secure login with encrypted passwords
	•	JasperReports-based report generation

⸻

🛠️ Technologies Used
	•	Programming Language: Java
	•	UI Framework: JavaFX
	•	Database: MySQL
	•	Database Connectivity: JDBC
	•	Build Tool: Maven
	•	Architecture Pattern: Layered Architecture
	•	Security: AES password encryption

⸻

📂 Project Structure

Gym-Management-System
│
├── controller      # UI controllers
├── service         # Business logic
├── dao             # Data access layer
├── model           # Entity classes
├── util            # Utility classes
├── view            # FXML UI files
├── resources       # CSS, images, reports
└── AppInitializer  # Application entry point


⸻

⚙️ Installation & Setup

Prerequisites
	•	Java JDK 17 or higher
	•	MySQL Server
	•	Maven
	•	JavaFX properly configured

Setup Steps
	•	Clone the repository
	•	Import the project into IntelliJ IDEA
	•	Configure database connection settings
	•	Run the SQL script to create database tables
	•	Launch the application

⸻

🔐 Security
	•	Passwords are stored using AES encryption
	•	Prevents storage of plain-text credentials
	•	Supports role-based access control (Admin / Trainer)

⸻

📈 Future Enhancements
	•	Cloud-based database support
	•	Mobile application integration
	•	Advanced analytics and dashboards
	•	Biometric authentication support
	•	Notification system for membership expiry

⸻

👨‍💻 Developer
	•	Project Type: Personal / Academic Project
	•	Domain: Fitness & Health Management

⸻

📄 License
	•	Developed for educational and personal use only

⸻

🏋️ Gym Management System

📌 Project Description

The Gym Management System is a desktop-based application designed to help small and single-trainer gyms efficiently manage their daily operations.
It focuses on simplifying client management, workout tracking, nutrition monitoring, memberships, and payments while reducing the administrative workload on gym owners.

The system is built using a layered architecture, ensuring clean code structure, scalability, and easy maintenance.

⸻

🎯 Objectives
	•	Digitize gym operations for small and medium gyms
	•	Reduce manual record keeping
	•	Help single-trainer gym owners manage clients efficiently
	•	Track workouts, nutrition, and membership status accurately

⸻

🏗️ System Architecture (Layered Architecture)

The application follows a 4-layer architecture:

1. Presentation Layer
	•	JavaFX-based user interface
	•	FXML layouts designed with Scene Builder
	•	Handles user interactions and UI logic

2. Service / Business Logic Layer
	•	Contains application rules and validations
	•	Processes data before passing it to the DAO layer
	•	Acts as a bridge between UI and database layers

3. Data Access Layer (DAO)
	•	Uses JDBC for database operations
	•	Handles CRUD operations
	•	Ensures separation between database logic and business logic

4. Database Layer
	•	MySQL relational database
	•	Uses constraints, foreign keys, and normalization
	•	Stores clients, workouts, nutrition data, and payments securely

⸻

🚀 Features
	•	Client registration and management
	•	Workout plan creation and tracking
	•	Nutrition and daily updates
	•	Membership and payment management
	•	Progress tracking and reporting
	•	Secure login with encrypted passwords
	•	Report generation (JasperReports support)

⸻

🛠️ Technologies Used
	•	Programming Language: Java
	•	UI Framework: JavaFX
	•	Database: MySQL
	•	Database Connectivity: JDBC
	•	Build Tool: Maven
	•	Architecture Pattern: Layered Architecture
	•	Security: AES encryption for passwords

⸻

📂 Project Structure

Gym-Management-System
│
├── controller      # UI controllers
├── service         # Business logic
├── dao             # Data access objects
├── model           # Entity classes
├── util            # Utility classes
├── view            # FXML UI files
├── resources       # CSS, images, reports
└── AppInitializer  # Application entry point


⸻

⚙️ Installation & Setup

Prerequisites
	•	Java JDK 17 or above
	•	MySQL Server
	•	Maven
	•	JavaFX configured

Steps
	1.	Clone the repository (https://github.com/PraveenRusiru/Gym-Management-System.git)
	2.	Import the project into IntelliJ IDEA
	3.	Configure database connection details
	4.	Run the SQL script to create tables
	5.	Launch the application

⸻

🔐 Security
	•	Passwords are stored using AES encryption
	•	Prevents storing plain-text credentials
	•	Supports role-based access control (Admin / Trainer)

⸻

📈 Future Enhancements
	•	Cloud-based database support
	•	Mobile app integration
	•	Advanced analytics and dashboards
	•	Biometric login support
	•	Notification system for membership expiry

⸻

👨‍💻 Developer

Project Type: Personal / Academic Project
Domain: Fitness & Health Management

⸻

📄 License

This project is developed for educational purposes and personal use.

⸻

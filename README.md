# 🐾 Vet System

> A veterinary system built with Java and Maven.

## 🚀 How to Run This Project

### 1. Clone the repository
```bash
git clone https://github.com/your-username/vet-system.git
cd vet-system
```

### 2. Install dependnecies
```bash
mvn clean install
```

### 3. Set up enviroment variables
```bash
cp .env.template .env
```
Edit .env and set the following values:
```
GMAIL_USER=your.email@gmail.com
GMAIL_APP_PASS=your_app_password_here
```

### 4. Run the application
```bash
mvn exec:java -Dexec.mainClass="org.app.App"
```
Or package it and run the JAR:
```bash
mvn package
java -jar target/vet-system-1.0-SNAPSHOT.jar
```

### ✅ Requirements
Java 17 or higher.

Maven 3.8 or higher.
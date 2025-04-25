# Todo Console Application

A Java-based console application for managing todo items, built with:
- MVC architecture pattern
- Repository and DAO patterns for data access
- MySQL database backend
- Docker containerization

## Features

- Create, Read, Update, and Delete todo items
- Sort todos by various criteria (ID, title, date, completion status)
- Clean architecture with separation of concerns
- Docker support for easy deployment
- Proper exception handling throughout the application

## Prerequisites

- Java 17 or higher
- Maven 3.8+
- Docker 20.10+ (optional)
- MySQL 8.0+ (if not using Docker)

## Project Structure

```
todo-app/
├── docker/
│   ├── mysql/
│   │   └── init.sql
│   └── Dockerfile
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/
│   │   │   │   └── TodoController.java
│   │   │   ├── dao/
│   │   │   │   └── TodoDao.java
│   │   │   ├── model/
│   │   │   │   └── Todo.java
│   │   │   ├── repository/
│   │   │   │   └── TodoRepository.java
│   │   │   ├── service/
│   │   │   │   └── TodoService.java
│   │   │   ├── util/
│   │   │   │   └── DatabaseConnection.java
│   │   │   ├── view/
│   │   │   │   └── TodoView.java
│   │   │   └── App.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── docker-compose.yml
└── pom.xml
```

## Installation

### Without Docker

1. Create MySQL database:
```sql
CREATE DATABASE todo_db;
CREATE USER 'todo_user'@'localhost' IDENTIFIED BY 'todo_pass';
GRANT ALL PRIVILEGES ON todo_db.* TO 'todo_user'@'localhost';
FLUSH PRIVILEGES;
```

2. Build the application:
```bash
mvn clean package
```

3. Run the application:
```bash
java -jar target/todo-app-1.0-jar-with-dependencies.jar
```

### With Docker

1. Build and start containers:
```bash
docker-compose up --build
```

2. The application will start automatically and connect to the MySQL database.

## Usage

When running the application, you'll see a menu with these options:

```
Todo App Console
1. List all todos
2. Add new todo
3. Update todo
4. Delete todo
5. Exit
Choose an option:
```

When listing todos, you can sort them by:
1. ID (default)
2. Title (A-Z)
3. Title (Z-A)
4. Creation Date (newest first)
5. Creation Date (oldest first)
6. Completion Status (completed first)
7. Completion Status (pending first)

## Configuration

### Environment Variables

The application uses these environment variables:

- `DB_HOST`: Database host (default: localhost)
- `DB_PORT`: Database port (default: 3306)
- `DB_NAME`: Database name (default: todo_db)
- `DB_USER`: Database user (default: todo_user)
- `DB_PASSWORD`: Database password (default: todo_pass)

### Database Schema

The initial database schema is defined in `docker/mysql/init.sql`:

```sql
CREATE TABLE IF NOT EXISTS todos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    completed BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Built With

- Java 17
- Maven - Dependency Management
- MySQL Connector/J - Database driver
- Docker - Containerization

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Clean Architecture principles
- Repository and DAO patterns
- Docker best practices

# Build stage
FROM maven:3.9.9-eclipse-temurin-24-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package

# Runtime stage
FROM eclipse-temurin:21.0.7_6-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/todo-app-1.0-jar-with-dependencies.jar ./todo-app.jar

# Add wait script
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait

CMD /wait && java -jar todo-app.jar
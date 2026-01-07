# Use an official OpenJDK runtime as base image
FROM openjdk:17-jdk-slim

# Set work directory inside container
WORKDIR /app

# Copy Maven/Gradle build files and compiled JAR
COPY target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Use official OpenJDK image
FROM eclipse-temurin:17-jdk

# Create working directory inside container
WORKDIR /app

# Copy jar file into container
COPY target/trade-service-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
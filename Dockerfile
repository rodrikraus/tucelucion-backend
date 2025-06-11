# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and POM file to download dependencies
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the application's source code
COPY src ./src

# Build the application
RUN ./mvnw package -DskipTests

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Define environment variables for database connection (to be set in Render)
ENV DB_URL=your_db_url
ENV DB_USERNAME=your_db_username
ENV DB_PASSWORD=your_db_password

# Run the JAR file 
ENTRYPOINT ["java","-jar","/app/target/ecommerce-template-0.0.1-SNAPSHOT.jar"]

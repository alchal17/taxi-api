# Use the official JDK 11 image as base
FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container at /app
COPY build/libs/*.jar /app/app.jar

# Expose the port your application listens on
EXPOSE 8080

# Command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]
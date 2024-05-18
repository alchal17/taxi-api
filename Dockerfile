# Use the official JDK 11 image as base for build stage
FROM adoptopenjdk/openjdk11:alpine as build

# Set the working directory inside the container for the build stage
WORKDIR /app

# Copy the Gradle wrapper and other required files
COPY gradlew gradlew
COPY gradle gradle

# Copy the project source code
COPY src src
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts

# Grant execute permission to the Gradle wrapper
RUN chmod +x gradlew

# Build the application and skip tests
RUN ./gradlew build -x test

# Use the official JDK 11 image as base for runtime stage
FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory inside the container for the runtime stage
WORKDIR /app

# Copy the compiled JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the port your application listens on

# Command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]
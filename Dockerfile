# Step 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
# Move into the folder where your pom.xml actually is
WORKDIR /inventorywebservice
COPY . .
# Run maven from inside that folder
RUN mvn clean package -DskipTests

# Step 2: Run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy the built jar from the build stage
COPY --from=build /inventorywebservice/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
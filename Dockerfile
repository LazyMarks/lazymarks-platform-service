# Stage 1: Build the application
FROM maven:3.9.6-amazoncorretto-17-debian AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Stage 2: Create the runtime image
FROM bellsoft/liberica-openjdk-alpine:17.0.11-cds
WORKDIR /app
COPY --from=build /build/target/lazymarks-platform-service-1.0.0-SNAPSHOT.jar /app/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/application.jar"]
FROM ubuntu:latest
LABEL authors="leonard"

# Stage 1: Build application
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
RUN mvn -B -ntp dependency:go-offline

COPY src ./src
RUN mvn -B -ntp -Dmaven.test.skip=true package

# Stage 2: Run application
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /workspace/target/*.jar app.jar

USER 10001

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Etapa de Build (Compilação)
FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY .env /app/

COPY pom.xml /app/
COPY src /app/src/

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim AS runtime

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

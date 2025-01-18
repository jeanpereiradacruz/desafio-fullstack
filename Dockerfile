# Etapa de Build (Compilação)
FROM maven:3.8.3-openjdk-17 AS build

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o pom.xml e o código fonte
COPY pom.xml /app/
COPY src /app/src/

# Construir a aplicação Spring Boot com Maven
RUN mvn clean package -DskipTests

# Etapa de Execução (Rodando a aplicação)
FROM openjdk:17-jdk-slim AS runtime

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o artefato gerado pelo Maven (arquivo JAR)
COPY --from=build /app/target/*.jar /app/app.jar

# Expor a porta que o Spring Boot vai usar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

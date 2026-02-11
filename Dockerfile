# Estágio 1: Build da aplicação
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Imagem final leve
FROM eclipse-temurin:21-jdk
WORKDIR /app
# Ajustamos o nome para bater com o seu renasce-0.0.1-SNAPSHOT.jar
COPY --from=build /app/target/renasce-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
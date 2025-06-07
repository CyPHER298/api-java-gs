# Etapa 1: Build com Maven usando o wrapper
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests

# Etapa 2: Imagem final apenas com o JAR
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/*-runner.jar app.jar

EXPOSE 8080
ENV PORT=8080

CMD ["java", "-jar", "app.jar"]

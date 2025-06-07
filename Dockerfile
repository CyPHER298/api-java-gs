# Etapa de build com Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

# Etapa final com imagem leve
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/quarkus-app/lib/ /app/lib/
COPY --from=build /app/target/quarkus-app/*.jar /app/app.jar
COPY --from=build /app/target/quarkus-app/app/ /app/app/
COPY --from=build /app/target/quarkus-app/quarkus/ /app/quarkus/
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar", "-Dquarkus.http.host=0.0.0.0"]

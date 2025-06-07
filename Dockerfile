# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o projeto inteiro para o WORKDIR definido
COPY . .

# Build limpo
RUN ./mvnw clean package -DskipTests

---

# Etapa final - execução da aplicação
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia os artefatos do build stage para o stage final
COPY --from=build /app/target/quarkus-app/lib/ /app/lib/
COPY --from=build /app/target/quarkus-app/*.jar /app/app.jar
COPY --from=build /app/target/quarkus-app/app/ /app/app/
COPY --from=build /app/target/quarkus-app/quarkus/ /app/quarkus/

EXPOSE 8080

# Correção: -D antes do -jar
CMD ["java", "-Dquarkus.http.host=0.0.0.0", "-jar", "/app/app.jar"]
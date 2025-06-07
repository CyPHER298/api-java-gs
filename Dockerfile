# Etapa 1: build da aplicação
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copia tudo para dentro da imagem (inclusive pom.xml e src/)
COPY . .

# Compila o projeto e pula os testes
RUN mvn package -DskipTests

# Etapa 2: imagem final para rodar o .jar gerado
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/*-runner.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

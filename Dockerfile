# Etapa 1: Build da aplicação usando Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copia o projeto completo para o container
COPY . .

# Compila a aplicação e ignora os testes
RUN mvn package -DskipTests

# Etapa 2: Imagem leve para rodar a aplicação
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia o .jar gerado da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Porta da aplicação (Railway injeta via variável PORT)
ENV PORT=8080
EXPOSE 8080

# Executa o JAR
CMD ["java", "-jar", "app.jar"]

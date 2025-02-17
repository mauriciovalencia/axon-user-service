FROM maven:3.8.6-eclipse-temurin-17-alpine AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/axon-user-service-0.0.1-SNAPSHOT.jar app.jar

COPY .env .env

EXPOSE ${USER_SERVICE_PORT} 8443

CMD ["sh", "-c", "export $(grep -v '^#' .env | xargs) && java -jar app.jar"]

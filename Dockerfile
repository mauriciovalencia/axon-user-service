FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/axon-user-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080 8443

ENTRYPOINT ["java", "-jar", "app.jar"]

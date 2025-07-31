FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

COPY target/notification-service-*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]

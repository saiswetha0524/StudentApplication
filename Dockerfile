FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 9090
COPY build/libs/StudentApplication-0.0.1-SNAPSHOT.jar/ app/
CMD ["java", "-jar", "StudentApplication-0.0.1-SNAPSHOT.jar"]
FROM openjdk:17-alpine

ARG JAR_FILE=./build/libs/module-admin-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV PROFILE local

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
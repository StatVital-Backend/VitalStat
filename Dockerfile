#FROM openjdk:17
#WORKDIR /app
#COPY target/StatVital-0.0.1-SNAPSHOT.jar /app
#EXPOSE 8080
#CMD ["java", "-jar", "StatVital-0.0.1-SNAPSHOT.jar"]
# syntax=docker/dockerfile:1


FROM maven:3.8.5-openjdk-17 as build
ARG JAR_FILE=target/*.jar
COPY ./target/StatVital-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080





#FROM maven:3.8.5-openjdk-17 as build
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
#EXPOSE 8080

FROM maven:3.8.5-openjdk-17 AS build
COPY  . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","-Dserver.port=${PORT}", "-Dspring.profiles.active=${PROFILE}", "app.jar"]


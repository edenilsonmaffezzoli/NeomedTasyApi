FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY api.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
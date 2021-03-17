FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} food-order-service-app.jar
ENTRYPOINT ["java","-jar","/food-order-service-app.jar"]
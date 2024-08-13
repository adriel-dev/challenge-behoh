FROM openjdk:21

WORKDIR /app

ADD challenge-adapter/challenge-web/target/*.jar /app/challenge-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/challenge-service.jar"]
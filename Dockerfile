FROM openjdk:17
ARG JAR_FILE=api/build/libs/api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENV SPRING_PROFILES_ACTIVE=deploy

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
FROM openjdk:21
ARG JAR_FILE=presentation/build/libs/presentation-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM openjdk:17
ARG JAR_FILE=api/build/libs/api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

COPY .oci /app/.oci
RUN chmod 600 /app/.oci/private_key.pem

ENV SPRING_PROFILES_ACTIVE=deploy

EXPOSE 443
ENTRYPOINT ["java", "-jar", "app.jar"]
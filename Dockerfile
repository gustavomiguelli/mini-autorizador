FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/mini-autorizador-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
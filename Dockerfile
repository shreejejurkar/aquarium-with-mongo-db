FROM openjdk:17.0.2-slim-buster
RUN addgroup --system spring && useradd --system spring -g spring
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
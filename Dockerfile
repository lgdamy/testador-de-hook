FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
RUN export PORT=8080
ARG JAR_FILE=render/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /robot-qa-back-end
COPY ./robot-qa-back-end/build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
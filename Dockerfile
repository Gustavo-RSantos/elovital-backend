FROM ubuntu:latest AS build
LABEL authors="Gustavo"

RUN apt-get update
RUN apt-get install openjdk-25-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

EXPOSE 8080

COPY --from=build /target/elovital-backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
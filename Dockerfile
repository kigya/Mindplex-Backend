FROM openjdk:17-jdk-slim

WORKDIR /src
COPY . /src

RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix gradlew
RUN chmod +x gradlew

RUN ./gradlew buildFatJar --no-daemon

WORKDIR /run

RUN cp /src/build/libs/*.jar /run/server.jar

EXPOSE 8080

CMD ["java", "-jar", "server.jar"]

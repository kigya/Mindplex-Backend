FROM openjdk:17-jdk-slim

WORKDIR /src
COPY . /src

RUN apt-get update \
 && apt-get install -y dos2unix \
 && dos2unix gradlew \
 && chmod +x gradlew \
 && ./gradlew buildFatJar --no-daemon

WORKDIR /run
COPY /src/build/libs/app-all.jar server.jar

EXPOSE 8080

CMD ["java", "-jar", "server.jar"]
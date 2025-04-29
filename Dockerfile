FROM openjdk:17-jdk-slim

WORKDIR /src

COPY . .

RUN apt-get update \
 && apt-get install -y --no-install-recommends dos2unix \
 && dos2unix gradlew \
 && chmod +x gradlew \
 && ./gradlew buildFatJar --no-daemon \
 && rm -rf /var/lib/apt/lists/*

EXPOSE 8080

CMD ["java","-jar","/src/build/libs/app-all.jar"]
FROM openjdk:17-jdk-slim AS builder
WORKDIR /src
COPY . .
RUN apt-get update \
  && apt-get install -y --no-install-recommends dos2unix \
  && dos2unix gradlew \
  && chmod +x gradlew \
  && ./gradlew buildFatJar --no-daemon \
  && rm -rf /var/lib/apt/lists/*

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /src/build/libs/app-all.jar ./app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

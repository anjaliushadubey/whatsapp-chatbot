FROM gradle:8.14.4-jdk21 AS build
WORKDIR /app

# Cache dependencies first
COPY build.gradle settings.gradle gradlew gradlew.bat ./
COPY gradle ./gradle
RUN ./gradlew --no-daemon dependencies

# Build
COPY src ./src
RUN ./gradlew --no-daemon clean bootJar

FROM eclipse-temurin:21-jre
WORKDIR /app

# Render sets PORT; app reads PORT via application.properties
ENV PORT=8080
EXPOSE 8080

COPY --from=build /app/build/libs/*.jar /app/app.jar
CMD ["sh","-c","java -jar /app/app.jar"]


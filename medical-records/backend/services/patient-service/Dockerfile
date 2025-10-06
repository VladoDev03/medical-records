FROM gradle:8.5-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle build -x test

FROM amazoncorretto:21.0.7-alpine3.20
WORKDIR /app
RUN apk add --no-cache curl
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
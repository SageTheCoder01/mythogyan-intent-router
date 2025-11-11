FROM gradle:8.10.2-jdk17 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/mythogyan-intent-router-0.1.0.jar app.jar
ENV NEO4J_URI=bolt://neo4j:7687 \
    NEO4J_USER=neo4j \
    NEO4J_PASS=neo4jpass
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# Build Frontend
FROM node:18.16.0-bullseye as frontend
WORKDIR /app
COPY src/frontend /app
RUN npm ci
RUN npm run build

# Copy frontend Build to Server Source, Build jar
FROM maven:3.9.2-eclipse-temurin-17-alpine as server
WORKDIR /app
RUN mkdir ./src
RUN mkdir ./src/main
COPY pom.xml ./pom.xml
COPY src/main/ ./src/main
COPY --from=frontend /app/build /app/src/main/resources/static
RUN mvn clean
RUN mvn package

# Copy jar to java runtime environment
FROM eclipse-temurin:17-jre-alpine as app
WORKDIR /app
RUN addgroup -S javauser && adduser -S javauser -G javauser && \
    chown -R javauser:javauser ./
USER javauser
COPY --from=server /app/target/*.jar app.jar
CMD "java" "-jar" "app.jar"

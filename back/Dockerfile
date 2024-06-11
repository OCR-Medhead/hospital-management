# FROM openjdk:23-slim

# WORKDIR /app
# COPY /target/Hopistal-0.0.1-SNAPSHOT.jar /app
# EXPOSE 8888

# CMD ["java", "-jar", "Hopistal-0.0.1-SNAPSHOT.jar"]

FROM 3.9.7-eclipse-temurin-17-alpine  as build
WORKDIR /app
COPY / /app
RUN "mvn package -DskipTests"
# RUN "mvn package"

EXPOSE 8888

CMD ["java", "-jar", "target/Hopistal-0.0.1-SNAPSHOT.jar"]
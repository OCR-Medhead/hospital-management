FROM openjdk:23-slim

WORKDIR /app
COPY /target/Hopistal-0.0.1-SNAPSHOT.jar /app
EXPOSE 8888

CMD ["java", "-jar", "Hopistal-0.0.1-SNAPSHOT.jar"]

# FROM eclipse-temurin:21.0.3_9-jre-ubi9-minimal  as build
# WORKDIR /app
# COPY / /app
# RUN "mvn package -DskipTests"
# # RUN "mvn package"

# EXPOSE 8888

# CMD ["java", "-jar", "target/Hopistal-0.0.1-SNAPSHOT.jar"]
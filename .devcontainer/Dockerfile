FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .


FROM openjdk:17-jdk-slim
WORKDIR /app

# Instala Maven en la etapa final
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 8080
ENTRYPOINT ["java", "mvn"]
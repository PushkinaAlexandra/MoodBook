FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY gradlew .
COPY gradle ./gradle

COPY build.gradle .
COPY src ./src

RUN chmod +x gradlew
RUN ./gradlew bootJar

EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java -jar /app/build/libs/*.jar"]
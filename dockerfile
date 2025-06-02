FROM openjdk:17-jdk-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем только файлы, необходимые для сборки Gradle
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle  ./
# Кеширование зависимостей Gradle:  Это критически важно для ускорения сборки
COPY --chown=gradle:gradle build.gradle settings.gradle ./
COPY --chown=gradle:gradle gradlew ./
RUN ./gradlew --version

# Создаем пользователя gradle (если его нет) и меняем владельца
RUN addgroup -g 1000 gradle && adduser -u 1000 -G gradle -s /bin/sh -D gradle

# Копируем исходники (после кеширования зависимостей)
COPY src ./src

# Создаем директорию для кэша gradle, предоставляем права и переключаемся на пользователя gradle
RUN mkdir -p /app/.gradle && chown -R gradle:gradle /app/.gradle && chown -R gradle:gradle /app

USER gradle

# Собираем приложение (после кеширования)
RUN ./gradlew bootJar --no-daemon

# Проверяем, что JAR-файл существует (для диагностики)
RUN ls -l /app/build/libs/  # <--- Добавлено

# Устанавливаем права доступа на jar файл
RUN chmod +x build/libs/*.jar

# Указываем порт для публикации
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app/build/libs/MoodBook-0.0.1-SNAPSHOT.jar"]
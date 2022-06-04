# currency-rest-service
Артюшкин Александр. Задание для собеседования

**Стек:**

Язык: Java 11
Сборщик: Gradle
Фреймворк: Spring Boot 2
Библиотеки: Jackson, Feign
Тесты: TestNG, Mockito

**Необходимое ПО: JDK11, Docker, git**

**Инструкция по сборке и запуску через Java:**
```
git clone https://github.com/sancho186494/currency-rest-service.git
cd ~/currency-rest-service
./gradlew build
cd build/libs/
java -jar currency-rest-service-1.0-SNAPSHOT.jar
```
***При необходимости использования внешнего файла конфигурации, скопировать файл src/test/resources/application.properties, изменить параметры и по аналогии указать путь до файла:***
```
java -jar currency-rest-service-1.0-SNAPSHOT.jar --spring.config.location=file:/Users/alexanderart/Desktop/application.properties
```

**Инструкция по сборке и запуску через Docker:**
```
git clone https://github.com/sancho186494/currency-rest-service.git
cd ~/currency-rest-service
./gradlew build
docker build --build-arg JAR_FILE=build/libs/\*.jar -t currency-rest-service:latest .
docker run --name currency-rest-service -d -p 8081:8081 currency-rest-service:latest
```

**Сервис будет доступен по адресу http://localhost:8081**


Чтобы открыть SUT
1. Создать репозиторий используя команду git clone
2. Открыть проект с помощью IDEA
3. Ввести в терминале команду docker-compose up -d
4. Ввести в терминале команду java -jar artifacts/aqa-shop.jar
5. Открыть в браузере адрес localhost:8080

### Запуск тестов и отчетов Allure
1. В терминале ввести команду ./gradlew clean test
2. В терминале ввести команду ./gradlew allureReport


### Automated tests
Требуется java 11
<br> и приложение на http://localhost:8080 (настройки в [todo.properties](src%2Ftest%2Fresources%2Ftodo.properties))

Запуск ./gradlew test
<br>
Тест testUpdateTodoDuplicate() падает (имхо это баг)

### Performance test report

**Окружение тестирования**
<br>
CPU - 2,6 GHz 6-Core Intel Core i7
RAM - 8gb

**Тест**
<br>
Запрос
```
POST http://localhost:8080/todos

{
  "id" - случайное значение от 1 до 1000000000
  "text" - случайная строка длинной 100 символов,
  "completed":false
}
```

**Профиль**
<br>
Шаги rps 100 - 200 - 400 - 600

**Результаты**

| RPS | Avg Response Time | CPU Usage  | Error Rate                     |
|-----|-------------------|------------|--------------------------------|
| 100 | <10ms             | <10%       | ~0%                            |
| 200 | 20ms              | 20%        | ~0%                            |
| 400 | 80ms              | 40-50%     | ~0%                            |
| 600 | -                 | up to 100% | Service disruptions after 30s |

В течение 30с на 600RPS начинаются перебои в работе сервиса: потребление cpu достигает 100%, сервис обрывает коннекшены, восстанавливается, опять обрывает и так далее. После прекращения нагрузки сразу восстанавливается.


Потребление памяти во время теста в пределах 40мб


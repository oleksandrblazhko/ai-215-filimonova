### Словник атрибутів об’єктів

| Об’єкт      | Атрибут                 | Короткий опис              | Тип   | Обмеження               |
|-------------|-------------------------|----------------------------|-------|-------------------------|
| Scientist        | name                    | ім'я науковця           | текст | довжина < 25 символів   |
| Scientist        | age                     | вік науковця            | число | мінімум 0, максимум 99    |
| AirSettings    | polutionLevel               | рівень після якого повітря вважається забрудненим | число   | Від 1 до 100 (%)                   |
| AirSettings    | humidityLevel             | рівень після якого повітря вважається не зволоженим        | число | від 1 до 100 (%)     |
| PolutionTracker | airPoluted  | флаг що вказує чи повітря забруднене     | булевий |    |
| PolutionTracker | lowHumidity | флаг що вказує чи повітря мало зволожене   | булеввий |  |
| ClarityIoApiClient | airPolutionLevel                   | значення рівня забруднення повітря        | число |  Від 1 до 100 (%)    |
| ClarityIoApiClient | airHumidityLevel | значення рівня вологості повітря  | число | Від 1 до 100 (%)  |
| NotificationSender | date             | дата та час створення сповіщення | дата та час |   |
| NotificationSender      | message             | текст сповіщення               | текст | довжина < 5000 символів |
| NotificationSender      | type             | тип сповіщення               | перерахування | значення тільки: HIGH, MEDIUM, LOW |
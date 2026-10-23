Виконав: Гріненко Роман ІМ-44

## Опис проєкту

Система обробки сповіщень, реалізована за підходом Data-Oriented Programming (DOP):
дані та алгоритми розділені, дані незмінні.

Усі класи знаходяться в пакеті `ua.kpi.comsys.dop`.

**Дані** — `sealed interface Notification` та три `record`:

| Тип | Поля |
|---|---|
| `TextMsg` | `String sender`, `String text` |
| `ImageMsg` | `String sender`, `String url`, `int sizeKb` |
| `AudioMsg` | `String sender`, `int durationSeconds` |

Валідація виконується в конструкторах records і кидає `IllegalArgumentException`:

- `sender`, `text`, `url` не можуть бути `null`, порожніми або такими, що містять лише пробіли;
- `sizeKb` та `durationSeconds` мають бути `> 0`.

**Логіка** — клас `NotificationProcessor` з методом `String process(Notification)`.
Він використовує `switch` expression з pattern matching (без `default`, оскільки
ієрархія `sealed`) та повертає Text Block:

```
Notification: TEXT
From: <sender>
Message: <text>
```

```
Notification: IMAGE
From: <sender>
URL: <url> (<sizeKb> KB)
```

```
Notification: AUDIO
From: <sender>
Duration: <durationSeconds> seconds
```

Для `null` кидається `IllegalArgumentException("Notification cannot be null")`.

## Розрахунок варіанта

```
N = 5
V = N mod 3 = 5 mod 3 = 2
```

Варіант **V = 2** — система управління замовленнями (ієрархія `Notification`).

## Запуск тестів

Потрібні JDK 21+ та Maven 3.9+.

```
mvn test
```

Тести (JUnit 5) покривають усі гілки `switch`, виняток для `null` та валідацію
кожного record.

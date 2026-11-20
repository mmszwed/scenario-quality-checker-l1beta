# Scenario Quality Checker

Projekt zespołowy — narzędzie do analizy scenariuszy wymagań. Zespół: 4 osoby.

W Sprincie 1 przygotowujemy REST API do liczenia kroków, liczenia kroków ze słowami kluczowymi i wskazywania kroków bez aktora.

[Zadania i kryteria odbioru Sprintu 1](docs/SPRINT-1.md)

[Plan Sprintu 2 — po zakończeniu Sprintu 1](docs/SPRINT-2.md)

[Tablica projektu — backlog i statusy zadań](https://github.com/users/mmszwed/projects/4/views/1)

[Historia zmian](CHANGELOG.md)

## REST API

Uruchom aplikację poleceniem `mvn spring-boot:run` (Java 21 i Maven).

```sh
curl -X POST http://localhost:8080/api/scenarios/analyze \
  -H 'Content-Type: application/json' \
  -d '{
    "title": "Dodanie książki",
    "actors": ["Bibliotekarz"],
    "systemActor": "System",
    "steps": [
      {"text": "IF: Bibliotekarz dodaje książkę", "subSteps": [
        {"text": "Wyświetla się formularz"}
      ]}
    ]
  }'
```

Pola `title`, `systemActor` oraz `text` każdego kroku wymagają niepustego tekstu.
Pominięte lub równe `null` listy `actors`, `steps` i `subSteps` są traktowane jako puste.

Po podłączeniu analiz z Tasków 6–8 oczekiwana odpowiedź dla tego przykładu to HTTP 200:

```json
{
  "stepCount": 2,
  "keywordStepCount": 1,
  "missingActorSteps": [
    {"path": "1.1.", "text": "Wyświetla się formularz"}
  ]
}
```

Niepoprawne dane JSON zwracają HTTP 400. Do czasu podłączenia analiz poprawne żądanie zwraca HTTP 503.

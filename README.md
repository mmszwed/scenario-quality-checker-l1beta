# Scenario Quality Checker

Narzędzie do analizy scenariuszy wymagań funkcjonalnych. Analityk opisuje wymagania scenariuszem, a aplikacja zlicza kroki, wskazuje kroki ze słowami kluczowymi i kroki, w których brakuje aktora. Wyniki są dostępne przez REST API, więc można je zintegrować z innymi narzędziami. Projekt zespołowy, zespół: 4 osoby.

Sprint 1 dostarcza REST API z trzema analizami: liczbą kroków, liczbą kroków ze słowami kluczowymi i wykrywaniem kroków bez aktora.

**Linki:** [Zadania i kryteria odbioru Sprintu 1](docs/SPRINT-1.md) · [Plan Sprintu 2](docs/SPRINT-2.md) · [Tablica projektu](https://github.com/users/mmszwed/projects/4/views/1) · [Historia zmian](CHANGELOG.md) · [GitHub Actions (CI)](https://github.com/mmszwed/scenario-quality-checker-l1beta/actions/workflows/java-ci.yml)

## Wymagania

- Java 21
- Maven 3.9 lub nowszy

## Budowanie i testowanie

```sh
mvn clean verify
```

Polecenie uruchamia testy i buduje wykonywalny JAR w `target/scenario-quality-checker-0.1.0-SNAPSHOT.jar`. Samo uruchomienie testów: `mvn test`.

## Uruchomienie

```sh
mvn spring-boot:run
```

albo zbudowanym JAR-em:

```sh
java -jar target/scenario-quality-checker-0.1.0-SNAPSHOT.jar
```

Aplikacja nasłuchuje na porcie `8080`. Sprawdzenie, czy działa:

```sh
curl http://localhost:8080/api/health
```

Oczekiwana odpowiedź: `{"status":"UP"}`.

## Notacja scenariuszy

Scenariusz składa się z nagłówka i kroków:

- **Nagłówek** zawiera tytuł, aktorów zewnętrznych (`Aktorzy`) i aktora systemowego (`Aktor systemowy`).
- **Kroki** to kolejne punkty scenariusza. Każdy krok zawiera tekst.
- **Podscenariusze:** krok może mieć kroki podrzędne, na dowolnym poziomie zagnieżdżenia.
- **Słowa kluczowe:** krok może zaczynać się od `IF`, `ELSE` albo `FOR EACH`.

Krok bez aktora to krok, który po ewentualnym usunięciu słowa kluczowego nie zaczyna się od nazwy aktora (zewnętrznego lub systemowego).

Przykład zapisu w dokumentacji:

```
Tytuł: Dodanie książki
Aktorzy: Bibliotekarz
Aktor systemowy: System

- Bibliotekarz wybiera opcje dodania nowej pozycji książkowej
- Wyświetla się formularz.
- Bibliotekarz podaje dane książki.
- IF: Bibliotekarz pragnie dodać egzemplarze książki
  - Bibliotekarz wybiera opcję definiowania egzemplarzy
  - System prezentuje zdefiniowane egzemplarze
  - FOR EACH egzemplarz:
    - Bibliotekarz wybiera opcję dodania egzemplarza
    - System prosi o podanie danych egzemplarza
    - Bibliotekarz podaje dane egzemplarza i zatwierdza.
    - System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.
- Bibliotekarz zatwierdza dodanie książki.
- System informuje o poprawnym dodaniu książki.
```

## Przykład: „Dodanie książki”

Powyższy scenariusz w formacie JSON znajduje się w [docs/examples/dodanie-ksiazki.json](docs/examples/dodanie-ksiazki.json). Wywołanie API:

```sh
curl -X POST http://localhost:8080/api/scenarios/analyze \
  -H 'Content-Type: application/json' \
  --data @docs/examples/dodanie-ksiazki.json
```

Oczekiwana odpowiedź HTTP 200:

```json
{
  "stepCount": 13,
  "keywordStepCount": 2,
  "missingActorSteps": [
    {"path": "2.", "text": "Wyświetla się formularz."},
    {"path": "4.3.", "text": "FOR EACH egzemplarz:"}
  ]
}
```

- `stepCount` — 13 kroków: 6 głównych i 7 w podscenariuszach.
- `keywordStepCount` — 2 kroki zaczynające się od słowa kluczowego: `IF` i `FOR EACH`.
- `missingActorSteps` — 2 kroki bez aktora. Krok `4.3.` zawiera tylko słowo kluczowe i `egzemplarz:`, więc po jego usunięciu nie zaczyna się od aktora.

## REST API — krótki przykład

Minimalny scenariusz z jednym krokiem podrzędnym:

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

Odpowiedź HTTP 200:

```json
{
  "stepCount": 2,
  "keywordStepCount": 1,
  "missingActorSteps": [
    {"path": "1.1.", "text": "Wyświetla się formularz"}
  ]
}
```

Zasady wejścia:

- Pola `title`, `systemActor` oraz `text` każdego kroku wymagają niepustego tekstu.
- Pominięte lub równe `null` listy `actors`, `steps` i `subSteps` są traktowane jako puste.
- Niepoprawne dane JSON zwracają HTTP 400. Odpowiedź nie zawiera opisu przyczyny, ta jest widoczna w logach `DEBUG` (zob. sekcja Logowanie).

## Logowanie

Aplikacja loguje przez SLF4J (Logback). Domyślny poziom dla pakietu `pl.sqc` to `INFO`, ustawiony w `src/main/resources/application.properties`.

- `INFO` — start i koniec każdej analizy: tytuł scenariusza, liczba kroków, kroków ze słowem kluczowym i kroków bez aktora.
- `DEBUG` — dodatkowo aktorzy, aktor systemowy, wyniki poszczególnych analiz, kroki bez aktora i powód odrzucenia wejścia.

Aby zobaczyć logi `DEBUG`, uruchom aplikację z parametrem:

```sh
mvn spring-boot:run -Dspring-boot.run.arguments=--logging.level.pl.sqc=DEBUG
```

albo dla gotowego JAR-a:

```sh
java -jar target/scenario-quality-checker-0.1.0-SNAPSHOT.jar --logging.level.pl.sqc=DEBUG
```

## Dokumentacja i CI

- **Javadoc:** generuje się podczas `mvn package` w `target/reports/apidocs` (start: `index.html`). W CI jest dostępny jako artefakt `javadoc`.
- **CI:** workflow [Java CI](https://github.com/mmszwed/scenario-quality-checker-l1beta/actions/workflows/java-ci.yml) uruchamia się po `push` i `pull_request`. Buduje projekt, uruchamia testy i publikuje wykonywalny JAR jako artefakt.
- **Diagram klas UML** przedstawia strukturę klas projektu oraz zastosowanie wzorca Visitor:

![Diagram UML](docs/uml/class-diagram.png)

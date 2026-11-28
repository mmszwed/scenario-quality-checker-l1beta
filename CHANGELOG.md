# Changelog

## 2026-11-28 — Dokumentacja Sprintu 1

- Task 13 — Michał Kaczor uzupełnił `README.md` o wymagania, budowanie, testowanie, uruchomienie, notację scenariuszy, przykład „Dodanie książki” z wynikiem API, opis logowania oraz linki do CI i dokumentacji. Scenariusz z przykładu zapisano w `docs/examples/dodanie-ksiazki.json`. [Issue #14](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/14)

## 2026-11-27 — Dokumentacja Sprintu 1

- Task 12 - Julia Wnukiewicz dodała diagram UML przedstawiający strukturę najważniejszych klas i interfejsów projektu oraz zastosowanie wzorca projektowego Visitor. Diagram zapisano w formatach PNG i Mermaid w `docs/uml/` oraz podlinkowano w `README.md`.

## 2026-11-24 — Implementacja Sprintu 1

- Task 11 — Michał Kaczor dodał logowanie SLF4J: INFO dla startu i wyniku analizy w kontrolerze; DEBUG dla szczegółów scenariusza, wyników `StepCounter`, `KeywordStepCounter` i `MissingActorVisitor`, kroków bez aktora oraz odrzuconych wejść w `ScenarioRequestMapper`. Domyślny poziom `pl.sqc` ustawiono na INFO i opisano zmianę w README. [Issue #12](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/12)

## 2026-11-22 — Implementacja Sprintu 1

- Task 10 - Patryk Czupak dodał generowanie Javadoc podczas `mvn package` i publikowanie dokumentacji jako artefaktu CI. Udokumentowano `AnalysisResult`, `ScenarioAnalyzer`, `MissingActorVisitor`, `ScenarioAnalysisController` oraz brakujące pola i metody w licznikach kroków i `StepKeywords`. [Issue #11](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/11)
- Task 9 — Julia Wnukiewicz skonfigurowała GitHub Actions uruchamiane automatycznie po `push` i `pull_request`. Dodano pipeline Maven obejmujący kompilację, uruchamianie testów, budowanie wykonywalnego JAR oraz publikowanie go jako artefaktu workflow.

## 2026-11-21 — Implementacja Sprintu 1

- Task 8 — Julia Wnukiewicz dodała `MissingActorVisitor` wykrywający kroki bez aktora, uwzględniający aktorów z nagłówka (również systemowego) i zagnieżdżenia. Dodano testy jednostkowe. [Issue #9](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/9)
- Task 5 — Mateusz Szwed podpiął trzy analizy z Tasków 6–8 pod endpoint `POST /api/scenarios/analyze` i sprawdził rzeczywiste wyniki w testach integracyjnych. [Issue #6](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/6)

## 2026-11-20 — Implementacja Sprintu 1

- Task 6 — Michał Kaczor dodał `StepCounter`, wizytatora liczącego wszystkie kroki na każdym poziomie zagnieżdżenia, oraz testy: pusty, płaski, zagnieżdżony scenariusz. [Issue #7](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/7)
- Task 7 - Patryk Czupak dodał `KeywordStepCounter`, wizytatora liczącego kroki zaczynające się od `IF`, `ELSE` i `FOR EACH`, oraz `StepKeywords` do rozpoznawania i usuwania słowa kluczowego z początku kroku. [Issue #8](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/8)

## 2026-11-18 — Implementacja Sprintu 1

- Task 2 — Michał Kaczor przygotował projekt Java/Maven, wykonywalny JAR, endpoint `GET /api/health` i testy uruchomienia. [PR #30](https://github.com/mmszwed/scenario-quality-checker-l1beta/pull/30)
- Task 3 — Julia Wnukiewicz uzupełniła `.gitignore` o reguły ignorowania artefaktów kompilacji, lokalnych plików IDE, `.DS_Store` i lokalnych plików środowiskowych. [PR #31](https://github.com/mmszwed/scenario-quality-checker-l1beta/pull/31)
- Task 4 — Patryk Czupak dodał model scenariusza, wzorzec Visitor i testy przejścia przez zagnieżdżone kroki. [PR #32](https://github.com/mmszwed/scenario-quality-checker-l1beta/pull/32)
- Task 5 — Mateusz Szwed przygotował endpoint `POST /api/scenarios/analyze`, mapowanie JSON, walidację wejścia, testy integracyjne i przykład w README. [Issue #6](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/6)

## 2026-11-17 — Plan Sprintu 2

- Rozpisano dokończenie funkcji produktu oraz GUI na Sprint 2.
- Dodano zadania testów jednostkowych, mocków, Javadoc 7 typów i retrospektywy.
- Sprawdzono pokrycie punktacji dla 4 osób; próg 7 BV wymaga uzgodnienia brakującego BV z prowadzącym.
- Plan nie rozpoczyna Sprintu 2 ani nie zmienia zadań Sprintu 1.

## 2026-11-16 — Przygotowanie Sprintu 1

W ramach prac Scrum Mastera:

- Przygotowano zakres Sprintu 1 dla 4 osób: 4 BV i 14 tasków z estymatami.
- Rozpisano checklisty, kryteria odbioru, zależności i nazwy branchy.
- Dodano backlog pozostałych funkcji z Excela i podsumowanie punktacji.
- Uzupełniono początkowe README projektu.

Pakiet startowy: [PR #1](https://github.com/mmszwed/scenario-quality-checker-l1beta/pull/1), branch `chore/initial-sprint`. Implementacja aplikacji pozostaje w zadaniach sprintu.

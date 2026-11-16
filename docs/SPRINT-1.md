# Sprint 1

**Zespół:** 4 osoby. **Cel:** działające API do podstawowej analizy scenariuszy, z automatycznym budowaniem i dokumentacją.

Każdy task ma osobny plik gotowy do skopiowania jako GitHub issue. Godziny są orientacyjną estymatą pracy. Wymagania i BV pochodzą z Excela, a punktacja z tabeli prowadzącego.

## Product Backlog

| Element | Ważność z Excela | BV | Zakres |
|---|---:|---:|---|
| REST API | 20 | 1 | Sprint 1 |
| Liczenie wszystkich kroków | 15 | 1 | Sprint 1 |
| Liczenie kroków ze słowami kluczowymi | 12 | 1 | Sprint 1 |
| Wykrywanie kroków bez aktora | 10 | 1 | Sprint 1 |
| [Eksport tekstu z numeracją](backlog/15-numbered-export.md) | 10 | 1,5 | Kolejny sprint |
| [Ograniczanie głębokości scenariusza](backlog/16-depth-limit.md) | 10 | 1 | Kolejny sprint |
| [GUI — przygotowanie zakresu](backlog/17-gui-scope.md) | Brak w źródle | Brak w źródle | Później, zakres z prowadzącym |

**Sprint 1: 4 BV.** Zadania techniczne nie dodają BV. Przy równej ważności wybieramy wykrywanie błędów aktora przed eksportem i ograniczaniem głębokości, żeby dostarczyć spójny zestaw analiz.

## Zadania Sprintu 1

14 tasków — szczegóły i checklisty znajdują się w podlinkowanych plikach.

| Task | Zadanie | Branch | Estymata | Issue |
|---|---|---|---:|---|
| 1 | [Ustawić backlog i dostęp prowadzącego](tasks/01-github-setup.md) | `chore/github-setup` | 2 h | [#2](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/2) |
| 2 | [Utworzyć projekt Java/Maven](tasks/02-project-setup.md) | `chore/project-setup` | 3 h | [#3](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/3) |
| 3 | [Zaktualizować .gitignore](tasks/03-gitignore.md) | `chore/gitignore` | 1 h | [#4](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/4) |
| 4 | [Zaimplementować model i Visitor](tasks/04-scenario-model.md) | `feature/scenario-model` | 4 h | [#5](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/5) |
| 5 | [Udostępnić funkcje przez REST API](tasks/05-rest-api.md) | `feature/rest-api` | 4 h | [#6](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/6) |
| 6 | [Policzyć wszystkie kroki](tasks/06-step-count.md) | `feature/step-count` | 2 h | [#7](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/7) |
| 7 | [Policzyć kroki ze słowami kluczowymi](tasks/07-keyword-count.md) | `feature/keyword-count` | 2 h | [#8](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/8) |
| 8 | [Wykryć kroki bez aktora](tasks/08-missing-actors.md) | `feature/missing-actors` | 3 h | [#9](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/9) |
| 9 | [Skonfigurować CI](tasks/09-ci.md) | `chore/ci` | 3 h | [#10](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/10) |
| 10 | [Dodać Javadoc](tasks/10-javadoc.md) | `docs/javadoc` | 2 h | [#11](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/11) |
| 11 | [Dodać logowanie](tasks/11-logging.md) | `feature/logging` | 1 h | [#12](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/12) |
| 12 | [Dodać diagram klas UML](tasks/12-uml.md) | `docs/uml` | 2 h | [#13](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/13) |
| 13 | [Uzupełnić README](tasks/13-readme.md) | `docs/readme` | 2 h | [#14](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/14) |
| 14 | [Przygotować wydanie i demo](tasks/14-sprint-1.md) | `release/sprint-1` | 2 h | [#15](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/15) |

Najpierw konfiguracja projektu i model, potem analizy oraz API. CI można przygotować po utworzeniu projektu. Na końcu dokumentacja, diagram i wydanie. Zależności są zapisane w taskach.

## Praca na GitHubie

Pakiet startowy publikujemy na `chore/initial-sprint`. Każdy task ma osobny branch z aktualnego `main`, PR z linkiem do issue i review drugiej osoby. Kod scalamy po przejściu testów i CI, gdy będzie skonfigurowane. Gotowy opis zadania nie oznacza jego wykonania.

## Pokrycie wymagań Sprintu 1

**Plan pokrywa wszystkie punktowane kryteria dla 4 osób. Repo nie spełnia ich jeszcze jako gotowa aplikacja — trzeba wykonać zadania i potwierdzić wyniki.**

| Kryterium | Task | Możliwe pkt |
|---|---|---:|
| GitHub i dostęp prowadzącego — minimum | 1 | 20 |
| Tag zgodny z pom.xml — minimum | 14 | 20 |
| Automatyczny build i paczka — minimum | 2, 9 | 30 |
| CI reagujące na zmiany i dostęp — minimum | 1, 9 | 30 |
| Automatyczny Javadoc — minimum | 10 | 10 |
| Pełna dokumentacja min. 1 typu — minimum | 10 | 30 |
| Pełna dokumentacja min. 3 typów | 10 | 10 |
| Narzędzie do backlogów i dostęp — minimum | 1 | 20 |
| SLF4J na INFO i DEBUG | 11 | 10 |
| Diagram klas UML | 12 | 40 |
| Wzorzec Visitor — minimum | 4, 6–8 | 40 |
| Product Backlog w narzędziu, uporządkowany | 1 | 20 |
| Elementy wybrane do Sprint Backlog | 1 | 10 |
| Zadania do elementów sprintu | 1, 5–8 | 30 |
| Ukończone 4 BV — progi 2 i 4 BV sumują się | 5–8 | 80 |
| **Razem** | | **400** |

Brak któregokolwiek obowiązkowego minimum DoD oznacza brak punktów za BV. Dla 4 osób dokumentacja 5 typów, diagram sekwencji i progi 5/6 BV nie zwiększają punktacji w przekazanej tabeli.

Utworzono 17 issues oraz [milestone Sprint 1](https://github.com/mmszwed/scenario-quality-checker-l1beta/milestone/1) z 14 zadaniami. Do wykonania pozostają aplikacja, CI, dokumentacja techniczna, diagram i wydanie. Konfiguracja tablicy Project, przypisanie wykonawców i potwierdzenie dostępu prowadzącego należą do Task 1. Samo utworzenie issues nie zalicza pozostałych kryteriów.

Issues poza Sprintem 1: [Task 15 / #16](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/16), [Task 16 / #17](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/17), [Task 17 / #18](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/18).

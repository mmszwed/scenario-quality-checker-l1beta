# Sprint 2 — plan po zakończeniu Sprintu 1

**Zespół:** 4 osoby. **Cel:** dokończyć pozostałe funkcje produktu, udostępnić GUI i oddać przetestowaną wersję `0.2.0`.

**14 tasków, orientacyjnie 44 h.** To plan przyszłego sprintu: zadania pozostają do rozpoczęcia po review Sprintu 1 i przejściu przez Product Backlog. Daty i wykonawców ustalimy wtedy, z uwzględnieniem zaległości i retrospektywy. Milestone Sprint 2 porządkuje plan, nie oznacza rozpoczęcia prac.

## Funkcje i BV

- Ze Sprintu 1 pozostają API i trzy analizy: łącznie 4 BV po ich rzeczywistym odbiorze.
- Sprint 2 dodaje eksport tekstowy (1,5 BV), ograniczanie głębokości (1 BV) oraz GUI (BV do zatwierdzenia przez prowadzącego).
- Znane BV całej aplikacji wynosi **6,5**. Najwyższy próg dla 4 osób w Sprincie 2 to **7 BV**, więc brakuje minimum **0,5 BV**.
- Task 17 i 27 wymagają zapisania decyzji prowadzącego o zakresie i BV GUI albo innej zaakceptowanej pozycji. Task 18 zawiera konkretny zakres prostego GUI, który trzeba dostosować do tej decyzji.
- Nie liczymy tych samych funkcji dwukrotnie: rozwinięcie REST nie daje nowego 1 BV, a testy, dokumentacja i zadania organizacyjne nie dodają BV.

Tabela prowadzącego ocenia cały przyrost produktu: próg 7 BV odnosimy do końcowej aplikacji, włącznie z zachowanymi funkcjami Sprintu 1. GUI nadal ma w Excelu zapis „Do późniejszej negocjacji”. Bez decyzji właściciela produktu nie deklarujemy pokrycia ostatnich 30 pkt za próg 7 BV.

## Zadania

Task 15–17 i ich istniejące issues wykorzystujemy ponownie, bez duplikatów.

| Task | Zadanie | Branch | Estymata | Issue |
|---|---|---|---:|---|
| 15 | [Eksportować scenariusz jako tekst z numeracją](backlog/15-numbered-export.md) | `feature/numbered-export` | 4 h | [#16](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/16) |
| 16 | [Ograniczyć głębokość scenariusza](backlog/16-depth-limit.md) | `feature/depth-limit` | 3 h | [#17](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/17) |
| 17 | [Przygotować zakres interfejsu użytkownika](backlog/17-gui-scope.md) | `docs/gui-scope` | 2 h | [#18](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/18) |
| 18 | [Zaimplementować prosty interfejs użytkownika](tasks-sprint2/18-gui.md) | `feature/gui` | 6 h | [#19](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/19) |
| 19 | [Udostępnić wszystkie funkcje przez REST i połączyć je z GUI](tasks-sprint2/19-rest-integration.md) | `feature/sprint2-rest` | 3 h | [#20](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/20) |
| 20 | [Doprowadzić testy jednostkowe do 20 metod w minimum 2 klasach](tasks-sprint2/20-unit-tests.md) | `test/unit-tests` | 6 h | [#21](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/21) |
| 21 | [Przetestować 10 wywołań zależności za pomocą mocków](tasks-sprint2/21-mock-tests.md) | `test/mock-interactions` | 4 h | [#22](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/22) |
| 22 | [Uzupełnić pełny Javadoc do minimum 7 klas lub interfejsów](tasks-sprint2/22-javadoc.md) | `docs/sprint2-javadoc` | 3 h | [#23](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/23) |
| 23 | [Zaktualizować diagram klas do końcowej aplikacji](tasks-sprint2/23-uml.md) | `docs/sprint2-uml` | 2 h | [#24](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/24) |
| 24 | [Sprawdzić build, CI i regresję po rozszerzeniu aplikacji](tasks-sprint2/24-ci-regression.md) | `chore/sprint2-ci` | 2 h | [#25](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/25) |
| 25 | [Przeprowadzić retrospektywę Sprintu 1 i zapisać akcje naprawcze](tasks-sprint2/25-retrospective.md) | `docs/sprint1-retrospective` | 2 h | [#26](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/26) |
| 26 | [Monitorować wdrożenie akcji z retrospektywy](tasks-sprint2/26-retro-actions.md) | `chore/retro-actions` | 2 h | [#27](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/27) |
| 27 | [Przygotować backlog Sprintu 2 i potwierdzić próg 7 BV](tasks-sprint2/27-sprint-planning.md) | `chore/sprint2-planning` | 2 h | [#28](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/28) |
| 28 | [Uzupełnić README, wydać wersję 0.2.0 i przeprowadzić końcowe demo](tasks-sprint2/28-release.md) | `release/sprint-2` | 3 h | [#29](https://github.com/mmszwed/scenario-quality-checker-l1beta/issues/29) |

## Kolejność

1. Zakończyć i odebrać Sprint 1. Przeprowadzić retrospektywę (25), zapisać konkretne akcje i doprecyzować backlog/BV GUI (17, 27).
2. Zaimplementować eksport i głębokość (15–16), GUI (18) oraz integrację REST (19). Monitorować akcje naprawcze przez cały sprint (26).
3. Uzupełniać testy i dokumentację podczas implementacji; zweryfikować końcowe progi testów, mocków i Javadoc (20–22).
4. Zaktualizować UML (23), sprawdzić CI i regresję (24), uzupełnić README i wydać `0.2.0` (28).

Testy ze Sprintu 1 liczą się do łącznej liczby metod; nie trzeba pisać 20 dodatkowych, jeśli część jest już gotowa. Testy mocków mogą być częścią tych samych 20 metod, ale muszą rzeczywiście weryfikować interakcje. Minimum 2 klasy testujące i 20 metod oraz 10 sprawdzonych wywołań zewnętrznych metod pokrywają wszystkie progi punktowane dla 4 osób.

## Pokrycie punktacji dla 4 osób

To pokrycie planem, nie przyznane punkty. Wiersze sumują się przyrostowo, zgodnie z tabelą prowadzącego.

| Kryterium | Minimum | Task / dowód | Punkty |
|---|---|---|---:|---|
| DoD 1: repo i dostęp prowadzącego | Tak | 27, 28; aktualne repo i dostęp | 10 |
| DoD 2: tag zgodny z pom.xml | Tak | 28; wersja i tag 0.2.0 | 10 |
| DoD 3: automatyczny build i paczka | Tak | 24; JAR i udany build | 5 |
| DoD 4.1: CI reaguje na zmiany i jest dostępne | Tak | 24; rzeczywisty przebieg CI | 5 |
| DoD 5.1: automatyczna dokumentacja | Tak | 22, 24; Javadoc podczas builda | 5 |
| DoD 5.2: pełna dokumentacja minimum 1 typu | Tak | 22; dokumentacja typu, pól i metod poza get/set | 10 |
| DoD 5.3: pełna dokumentacja minimum 5 typów | Nie | 22; lista i strony minimum 5 typów | 10 |
| DoD 5.4: pełna dokumentacja minimum 7 typów | Nie | 22; lista i strony minimum 7 typów | 5 |
| DoD 6: backlog produktu i sprintu, dostęp | Tak | 27; issues, Project i widok sprintu | 10 |
| DoD 7.1: SLF4J INFO i DEBUG | Nie | 24; utrzymanie logowania | 0 |
| DoD 8.1: diagram klas UML | Nie | 23; aktualny diagram w repo/wiki | 10 |
| DoD 9.1: Visitor do analiz scenariuszy | Tak | 15–16, 19, 24; zachowany wzorzec i użycie w analizach | 10 |
| DoD 10.1: minimum 1 klasa testująca i 5 metod | Tak | 20; raport i kod testów | 40 |
| DoD 10.2: minimum 2 klasy testujące i 10 metod | Nie | 20; raport i kod testów | 30 |
| DoD 10.3: minimum 2 klasy testujące i 20 metod | Nie | 20; raport i kod testów | 20 |
| DoD 10.5: minimum 1 wywołanie z mockiem | Tak | 21; test i weryfikacja interakcji | 40 |
| DoD 10.6: minimum 5 wywołań z mockami | Nie | 21; lista sprawdzonych interakcji | 30 |
| DoD 10.7: minimum 10 wywołań z mockami | Nie | 21; lista sprawdzonych interakcji | 20 |
| Prowadzenie 1.1: Product Backlog w narzędziu | Nie | 27; pełny backlog | 10 |
| Prowadzenie 1.2: kolejność według ważności | Nie | 27; uporządkowany widok | 0 |
| Prowadzenie 2.1: wybrane elementy Sprint Backlog | Nie | 27; milestone/widok Sprint 2 | 10 |
| Prowadzenie 2.2: zadania do elementów sprintu | Nie | 15–28, 27; checklisty i estymaty | 10 |
| Prowadzenie 3.1: spisane akcje retrospektywy | Nie | 25; rzeczywiste ustalenia i zadania | 20 |
| Prowadzenie 3.2: monitorowanie akcji | Nie | 26; statusy i wyniki wdrożenia | 10 |
| BV: końcowy produkt minimum 4 BV | — | 28; odebrane funkcje całej aplikacji | 40 |
| BV: końcowy produkt minimum 7 BV | — | 17, 27, 28; zaakceptowane BV i demo | 30 |
| **Razem możliwe** | | **270 DoD + 60 prowadzenie + 70 BV** | **400** |

Brak któregokolwiek obowiązkowego minimum DoD blokuje punkty za BV. Przy znanych obecnie 6,5 BV plan nie potwierdza ostatnich 30 pkt; ich uzyskanie wymaga zaakceptowanego zakresu co najmniej 7 BV i jego wykonania.

Dla 4 osób tabela nie daje dodatkowych punktów za diagram sekwencji (8.2), 25 metod testowych (10.4), 15 wywołań z mockami (10.8) ani próg 9 BV. Te wiersze należą do kolumny 5+. W Sprincie 2 pełna dokumentacja 7 typów daje punkty również zespołowi 4-osobowemu, więc uwzględniamy ją w planie.

## GitHub

[Tablica projektu](https://github.com/users/mmszwed/projects/4/views/1) · [Milestone Sprint 2](https://github.com/mmszwed/scenario-quality-checker-l1beta/milestone/2). Issues Sprintu 2 pozostają nierozpoczęte; nie zmieniamy wykonawców ani zakresu Sprintu 1. Przed rozpoczęciem Sprintu 2 trzeba ponownie przejrzeć backlog, zaległości i dostępność zespołu. Daty przyszłego sprintu nie wynikają z dostarczonych materiałów.

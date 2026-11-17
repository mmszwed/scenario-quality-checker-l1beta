# Task 21 — Przetestować 10 wywołań zależności za pomocą mocków

[Sprint 2 i lista zadań](../SPRINT-2.md)

**Zakres:** plan Sprintu 2 — po zakończeniu Sprintu 1.

Branch: `test/mock-interactions` · Estymata: 4 h.

Dotyczy istniejących granic klas, np. kontroler → usługa. Nie tworzyć sztucznych zależności wyłącznie do punktacji.

- [ ] Wykorzystać Mockito lub równoważne narzędzie do mockowania zależności klasy testowanej.
- [ ] Przetestować co najmniej 10 wywołań metod zewnętrznych klas w sensownych scenariuszach analiz, eksportu i ograniczania głębokości.
- [ ] Dla każdego przypadku ustawić zachowanie mocka, wykonać metodę klasy testowanej, sprawdzić wynik asercją i zweryfikować oczekiwane wywołanie oraz argumenty.
- [ ] Objąć poprawne wyniki, puste wyniki i obsługę błędów zależności; nie powtarzać dziesięć razy identycznego przypadku.
- [ ] Zapisać w issue listę minimum 10 sprawdzonych interakcji z nazwami metod testowych i metod zależności. Same obiekty mock lub stubowanie nie są dowodem sprawdzenia wywołań.
- [ ] Testy mogą jednocześnie liczyć się do progu Task 20, jeśli spełniają jego wymagania.

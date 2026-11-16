# Task 16 — Ograniczyć głębokość scenariusza

**Zakres:** Product Backlog — kolejny sprint. **Ważność z Excela:** 10. **BV:** 1.

Branch: `feature/depth-limit` · Estymata: 3 h · Po modelu i REST API ze Sprintu 1.

- [ ] Przyjmować strukturę scenariusza i maksymalny poziom głębokości.
- [ ] Dla poziomu 1 zwracać tylko kroki najwyższego poziomu.
- [ ] Dla poziomu 2 zachować też bezpośrednie podscenariusze; analogicznie dla dalszych poziomów.
- [ ] Zwracać kopię uproszczonego scenariusza bez modyfikowania wejścia.
- [ ] Udostępnić funkcję przez REST; dla poziomu mniejszego niż 1 zwracać HTTP 400.
- [ ] Przetestować poziomy 1, 2 i 3, poziom większy od głębokości drzewa i błędną wartość.

Odbiór: wynik zawiera wszystkie kroki do wskazanego poziomu włącznie i żadnych poniżej niego.

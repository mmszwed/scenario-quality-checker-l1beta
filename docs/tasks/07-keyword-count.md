# Task 7 — Policzyć kroki ze słowami kluczowymi

[Sprint 1 i lista zadań](../SPRINT-1.md)

**Element produktu:** Policzyć kroki ze słowami kluczowymi · **Ważność z Excela:** 12 · **BV:** 1.

Branch: `feature/keyword-count` · Estymata: 2 h · Po Task 4.

- [ ] Zaimplementować Visitor rozpoznający na początku kroku `IF`, `ELSE` i `FOR EACH`, także w podscenariuszach.
- [ ] Obsłużyć zapis z dwukropkiem, np. `IF:`; nie liczyć słów w środku zdania ani prefiksów typu `IFx`.
- [ ] Przetestować wszystkie słowa kluczowe, zagnieżdżenia i scenariusz bez słów kluczowych, który daje 0.

Odbiór: każdy krok zaczynający się od słowa kluczowego zwiększa wynik o 1. **BV: 1.**

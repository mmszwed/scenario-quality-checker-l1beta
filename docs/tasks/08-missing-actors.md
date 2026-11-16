# Task 8 — Wykryć kroki bez aktora

[Sprint 1 i lista zadań](../SPRINT-1.md)

**Element produktu:** Wykryć kroki bez aktora · **Ważność z Excela:** 10 · **BV:** 1.

Branch: `feature/missing-actors` · Estymata: 3 h · Po Task 4 i parserze prefiksów z Task 7.

- [ ] Zaimplementować Visitor sprawdzający początek każdego kroku względem aktorów z nagłówka, także aktora systemowego.
- [ ] Przed sprawdzeniem aktora pominąć początkowe słowo kluczowe i opcjonalny dwukropek.
- [ ] Zwracać wszystkie błędne kroki ze wszystkich poziomów, z tekstem i ścieżką pozwalającą je znaleźć.
- [ ] Przetestować poprawne i błędne kroki, aktora systemowego i zagnieżdżenia. Dla samych poprawnych kroków zwracać pustą listę.

Odbiór: `IF: Bibliotekarz ...` przechodzi dla aktora Bibliotekarz; `Wyświetla się formularz` nie przechodzi. **BV: 1.**

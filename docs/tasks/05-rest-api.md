# Task 5 — Udostępnić funkcje przez REST API

[Sprint 1 i lista zadań](../SPRINT-1.md)

**Element produktu:** Udostępnić funkcje przez REST API · **Ważność z Excela:** 20 · **BV:** 1.

Branch: `feature/rest-api` · Estymata: 4 h · Model z Task 4; zakończenie po integracji Task 6–8.

- [ ] Dodać `POST /api/scenarios/analyze`, przyjmujący scenariusz jako JSON i mapujący go na model obiektowy.
- [ ] Uruchamiać trzy analizy i zwracać JSON: liczba kroków, liczba kroków ze słowami kluczowymi, lista kroków bez aktora.
- [ ] Dla niepoprawnego wejścia zwracać HTTP 400 zamiast błędu serwera.
- [ ] Dodać test integracyjny żądanie → analiza → odpowiedź oraz przykład wywołania do README.

Odbiór: każda zaimplementowana funkcja jest dostępna przez API. **BV: 1.**

# Task 19 — Udostępnić wszystkie funkcje przez REST i połączyć je z GUI

[Sprint 2 i lista zadań](../SPRINT-2.md)

**Zakres:** plan Sprintu 2 — po zakończeniu Sprintu 1.

Branch: `feature/sprint2-rest` · Estymata: 3 h.

Po Task 15–16 i wspólnie z Task 18. Rozszerzenie API spełnia istniejącą pozycję REST; nie dodaje drugiego BV za to samo.

- [ ] Dodać endpointy eksportu tekstowego i ograniczania głębokości; wejście i wyjście REST mają format JSON.
- [ ] Połączyć GUI ze wszystkimi ukończonymi operacjami; używać tych samych usług co API, bez kopiowania algorytmów do JavaScript.
- [ ] Ujednolicić walidację i odpowiedzi HTTP 400; tekst eksportu zwracać jako pole JSON.
- [ ] Dodać testy integracyjne JSON → model → usługa → JSON dla nowych operacji oraz regresję analiz ze Sprintu 1.

package pl.sqc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Położenie kroku w scenariuszu jako numery kolejne na każdym poziomie.
 *
 * <p>Numeracja zaczyna się od 1. Ścieżka {@code [4, 3]} oznacza trzeci krok
 * podscenariusza czwartego kroku głównego i jest wypisywana jako {@code "4.3."}.
 *
 * @param indices numery kroków od najwyższego poziomu w dół
 */
public record StepPath(List<Integer> indices) {

    /**
     * Tworzy ścieżkę i sprawdza, czy numery są dodatnie.
     *
     * @throws IllegalArgumentException gdy któryś numer jest mniejszy niż 1
     */
    public StepPath {
        indices = List.copyOf(Objects.requireNonNull(indices, "indices"));
        for (int index : indices) {
            if (index < 1) {
                throw new IllegalArgumentException("Numer kroku musi być dodatni: " + index);
            }
        }
    }

    /**
     * Zwraca pustą ścieżkę, czyli poziom samego scenariusza.
     *
     * @return ścieżka bez numerów
     */
    public static StepPath root() {
        return new StepPath(List.of());
    }

    /**
     * Zwraca ścieżkę kroku podrzędnego o podanym numerze.
     *
     * @param index numer kroku podrzędnego, liczony od 1
     * @return nowa ścieżka wydłużona o {@code index}
     */
    public StepPath child(int index) {
        List<Integer> childIndices = new ArrayList<>(indices);
        childIndices.add(index);
        return new StepPath(childIndices);
    }

    /**
     * Zwraca poziom zagnieżdżenia kroku: 1 dla kroków głównych.
     *
     * @return liczba numerów w ścieżce
     */
    public int depth() {
        return indices.size();
    }

    /**
     * Zwraca numerację kroku, np. {@code "4.3."}; dla pustej ścieżki pusty tekst.
     *
     * @return numery oddzielone kropkami, z kropką na końcu
     */
    @Override
    public String toString() {
        return indices.stream().map(index -> index + ".").collect(Collectors.joining());
    }
}

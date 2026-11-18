package pl.sqc.model;

import java.util.List;
import java.util.Objects;

/**
 * Pojedynczy krok scenariusza z tekstem i opcjonalnym podscenariuszem.
 *
 * <p>Podscenariusz to lista kroków podrzędnych, które również mogą mieć
 * własne podscenariusze. Obiekt jest niemodyfikowalny.
 *
 * @param text     treść kroku, np. {@code "IF: Bibliotekarz wybiera opcję"}
 * @param subSteps kroki podscenariusza; pusta lista, gdy krok go nie ma
 */
public record Step(String text, List<Step> subSteps) {

    /**
     * Tworzy krok i zabezpiecza listę kroków podrzędnych przed modyfikacją.
     *
     * @throws NullPointerException gdy brakuje tekstu albo lista zawiera {@code null}
     */
    public Step {
        Objects.requireNonNull(text, "text");
        subSteps = subSteps == null ? List.of() : List.copyOf(subSteps);
    }

    /**
     * Tworzy krok bez podscenariusza.
     *
     * @param text treść kroku
     */
    public Step(String text) {
        this(text, List.of());
    }

    /**
     * Sprawdza, czy krok zawiera podscenariusz.
     *
     * @return {@code true}, gdy krok ma kroki podrzędne
     */
    public boolean hasSubSteps() {
        return !subSteps.isEmpty();
    }

    /**
     * Przekazuje krok do odwiedzającego, a potem jego kroki podrzędne.
     *
     * @param visitor odwiedzający analizujący scenariusz
     * @param path    położenie tego kroku w scenariuszu
     */
    void accept(ScenarioVisitor visitor, StepPath path) {
        visitor.visitStep(this, path);
        for (int i = 0; i < subSteps.size(); i++) {
            subSteps.get(i).accept(visitor, path.child(i + 1));
        }
    }
}

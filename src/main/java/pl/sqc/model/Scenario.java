package pl.sqc.model;

import java.util.List;
import java.util.Objects;

/**
 * Scenariusz wymagań: tytuł, nagłówek z aktorami i lista kroków głównych.
 *
 * <p>Kroki mogą zawierać podscenariusze na dowolnym poziomie zagnieżdżenia.
 * Obiekt jest niemodyfikowalny, a brakujące listy są zamieniane na puste.
 *
 * @param title       tytuł scenariusza
 * @param actors      aktorzy zewnętrzni z nagłówka scenariusza
 * @param systemActor aktor systemowy z nagłówka scenariusza
 * @param steps       kroki najwyższego poziomu
 */
public record Scenario(String title, List<String> actors, String systemActor, List<Step> steps) {

    /**
     * Tworzy scenariusz i zabezpiecza listy przed modyfikacją z zewnątrz.
     *
     * @throws NullPointerException gdy brakuje tytułu, aktora systemowego
     *                              albo lista zawiera {@code null}
     */
    public Scenario {
        Objects.requireNonNull(title, "title");
        Objects.requireNonNull(systemActor, "systemActor");
        actors = actors == null ? List.of() : List.copyOf(actors);
        steps = steps == null ? List.of() : List.copyOf(steps);
    }

    /**
     * Przekazuje scenariusz do odwiedzającego, a następnie wszystkie kroki
     * w kolejności występowania, schodząc w głąb podscenariuszy.
     *
     * @param visitor odwiedzający analizujący scenariusz
     */
    public void accept(ScenarioVisitor visitor) {
        Objects.requireNonNull(visitor, "visitor");
        visitor.visitScenario(this);
        StepPath root = StepPath.root();
        for (int i = 0; i < steps.size(); i++) {
            steps.get(i).accept(visitor, root.child(i + 1));
        }
    }
}

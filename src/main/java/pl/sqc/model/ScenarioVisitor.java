package pl.sqc.model;

/**
 * Odwiedzający strukturę scenariusza (wzorzec Visitor).
 *
 * <p>Przejście uruchamia {@link Scenario#accept(ScenarioVisitor)}. Najpierw
 * wywoływana jest metoda {@link #visitScenario(Scenario)}, potem
 * {@link #visitStep(Step, StepPath)} dla każdego kroku dokładnie raz, w kolejności
 * występowania: krok, jego podscenariusz, następny krok.
 *
 * <p>Każda analiza scenariusza (liczenie kroków, słowa kluczowe, aktorzy)
 * to osobna implementacja tego interfejsu.
 */
public interface ScenarioVisitor {

    /**
     * Odwiedza scenariusz przed jego krokami, np. aby odczytać aktorów z nagłówka.
     * Domyślnie nic nie robi.
     *
     * @param scenario odwiedzany scenariusz
     */
    default void visitScenario(Scenario scenario) {
    }

    /**
     * Odwiedza pojedynczy krok na dowolnym poziomie zagnieżdżenia.
     *
     * @param step odwiedzany krok
     * @param path położenie kroku w scenariuszu
     */
    void visitStep(Step step, StepPath path);
}

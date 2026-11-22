package pl.sqc.analysis;

import pl.sqc.model.Scenario;
import pl.sqc.model.ScenarioVisitor;
import pl.sqc.model.Step;
import pl.sqc.model.StepPath;

/**
 * Liczy wszystkie kroki scenariusza: główne i z podscenariuszy na każdym poziomie.
 */
public final class StepCounter implements ScenarioVisitor {

    /** Liczba odwiedzonych kroków. */
    private int count;

    /**
     * Zwraca liczbę kroków scenariusza.
     *
     * @param scenario analizowany scenariusz
     * @return liczba wszystkich kroków; 0 dla pustego scenariusza
     */
    public static int countSteps(Scenario scenario) {
        StepCounter counter = new StepCounter();
        scenario.accept(counter);
        return counter.count;
    }

    /**
     * Zwiększa licznik o każdy odwiedzony krok, niezależnie od poziomu zagnieżdżenia.
     *
     * @param step odwiedzany krok
     * @param path położenie kroku w scenariuszu
     */
    @Override
    public void visitStep(Step step, StepPath path) {
        count++;
    }
}

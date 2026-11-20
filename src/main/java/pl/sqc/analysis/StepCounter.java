package pl.sqc.analysis;

import pl.sqc.model.Scenario;
import pl.sqc.model.ScenarioVisitor;
import pl.sqc.model.Step;
import pl.sqc.model.StepPath;

/**
 * Liczy wszystkie kroki scenariusza: główne i z podscenariuszy na każdym poziomie.
 */
public final class StepCounter implements ScenarioVisitor {

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

    @Override
    public void visitStep(Step step, StepPath path) {
        count++;
    }
}

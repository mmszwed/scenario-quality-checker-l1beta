package pl.sqc.analysis;

import pl.sqc.model.Scenario;
import pl.sqc.model.ScenarioVisitor;
import pl.sqc.model.Step;
import pl.sqc.model.StepPath;

/**
 * Liczy kroki zaczynające się od słowa kluczowego {@code IF}, {@code ELSE} albo {@code FOR EACH},
 * na wszystkich poziomach zagnieżdżenia.
 */
public final class KeywordStepCounter implements ScenarioVisitor {

    /** Liczba odwiedzonych kroków ze słowem kluczowym na początku. */
    private int count;

    /**
     * Zwraca liczbę kroków ze słowem kluczowym na początku.
     *
     * @param scenario analizowany scenariusz
     * @return liczba kroków ze słowem kluczowym; 0, gdy scenariusz ich nie ma
     */
    public static int countKeywordSteps(Scenario scenario) {
        KeywordStepCounter counter = new KeywordStepCounter();
        scenario.accept(counter);
        return counter.count;
    }

    /**
     * Zwiększa licznik, gdy krok zaczyna się od słowa kluczowego.
     *
     * @param step odwiedzany krok
     * @param path położenie kroku w scenariuszu
     */
    @Override
    public void visitStep(Step step, StepPath path) {
        if (StepKeywords.startsWithKeyword(step.text())) {
            count++;
        }
    }
}
